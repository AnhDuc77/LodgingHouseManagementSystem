/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.AssetDAO;
import dal.FeedbackDAO;
import dal.InformationOfUserDAO;
import dal.LodgingHousesDAO;
import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Asset;
import model.InformationOfUser;
import model.Room;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AssetController", urlPatterns = {"/asset"})
public class AssetController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        //create Daos object.
        AccountDAO accountDao = new AccountDAO();
        InformationOfUserDAO userDao = new InformationOfUserDAO();
        RoomDAO roomDao = new RoomDAO();
        FeedbackDAO feedDao = new FeedbackDAO();
        LodgingHousesDAO lodDao = new LodgingHousesDAO();
        AssetDAO assetDao = new AssetDAO();

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
        }
        int id = account.getAccountID();
        Account showAcc = accountDao.getAccountById(id);
        InformationOfUser userInfor = userDao.getInformationByAccountID(id);
        String service = request.getParameter("service");
        if (service == null) {
            service = "";
        }
        if (service.equals("manageAsset")) {
            String assetSearch = request.getParameter("assetSearch");
            if (assetSearch == null) {
                assetSearch = "";
            }
            int lodgingHouseId = lodDao.getLodgingHouseByManageID(id);
            List<Asset> listAsset = assetDao.getAllAssetsByLodgingHouseId(lodgingHouseId, assetSearch);
            int total = listAsset.size();
            String indexS = request.getParameter("index");
            if (indexS == null) {
                indexS = "1";
            }
            List<Asset> listAssetIndex;
            int index = Integer.parseInt(indexS);
            if (total == 0) {
                listAssetIndex = listAsset;
            } else {
                listAssetIndex = paginate(listAsset, index - 1);
            }
            int endPage = total / 5;
            if (total % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
            request.setAttribute("listAsset", listAssetIndex);
            request.setAttribute("account", showAcc);
            request.setAttribute("userInfor", userInfor);
            request.setAttribute("lodgingHouseId", lodgingHouseId);
            request.getRequestDispatcher("view/manager/manage-asset.jsp").forward(request, response);
        }
        if (service.equals("ShowAssetIntoRoom")) {
            int lodgingHouseId = lodDao.getLodgingHouseByManageID(id);
            List<Room> listRoomHaveAsset = assetDao.getRoomsHaveAsset(lodgingHouseId);
            List<Room> listRoomHaveNotAsset = assetDao.getRoomsHaveNotAsset(lodgingHouseId);
            String assetId = request.getParameter("assetId");
            request.setAttribute("roomsWithAsset", listRoomHaveAsset);
            request.setAttribute("roomsWithoutAsset", listRoomHaveNotAsset);
            request.setAttribute("assetId", assetId);
            request.getRequestDispatcher("view/manager/add-asset-intoroom.jsp").forward(request, response);
        }
    }

    public static List<Asset> paginate(List<Asset> list, int index) {
        List<List<Asset>> paginatedList = new ArrayList<>();
        int pageSize = 5;
        int totalSize = list.size();
        for (int i = 0; i < totalSize; i += pageSize) {
            paginatedList.add(new ArrayList<>(list.subList(i, Math.min(totalSize, i + pageSize))));
        }

        if (index < 0 || index >= paginatedList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return paginatedList.get(index);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String service = request.getParameter("service");
        AssetDAO assetDao = new AssetDAO();
        if (service == null) {
            service = "";
        }
        if (service.equals("addAssetIntoRoom")) {
            String roomId = request.getParameter("roomId");
            int assetId = Integer.parseInt(request.getParameter("assetId"));
            int quantity = 1; // Assuming you have a quantity parameter

            if (assetDao.insertRoomAsset(roomId, assetId, quantity)) {
                response.sendRedirect("asset?service=ShowAssetIntoRoom&assetId=" + assetId);
            }
        }
        if (service.equals("removeAssetFromRoom")) {
            String roomId = request.getParameter("roomId");
            int assetId = Integer.parseInt(request.getParameter("assetId"));

            boolean isRemoved = assetDao.removeRoomAsset(roomId, assetId);
            if (isRemoved) {
                response.sendRedirect("asset?service=ShowAssetIntoRoom&assetId=" + assetId);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String service = request.getParameter("service");
        AssetDAO assetDao = new AssetDAO();
        if (service == null) {
            service = "";
        }
        if (service.equals("addAsset")) {
            String assetName = request.getParameter("assetName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String lodgingHouseIdStr = request.getParameter("lodgingHouseId");

            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            int lodgingHouseId = Integer.parseInt(lodgingHouseIdStr);

            Asset asset = new Asset(assetName, description, status, price, quantity, lodgingHouseId);
            if (assetDao.insertAsset(asset)) {
                response.sendRedirect("asset?service=manageAsset");
            }
        }

        if (service.equals("updateAsset")) {
            String assetIdStr = request.getParameter("assetId");
            String assetName = request.getParameter("assetName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String lodgingHouseIdStr = request.getParameter("lodgingHouseId");

            int assetId = Integer.parseInt(assetIdStr);
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            int lodgingHouseId = Integer.parseInt(lodgingHouseIdStr);

            Asset asset = new Asset(assetId, assetName, description, status, price, quantity, lodgingHouseId);
            if (assetDao.updateAsset(asset)) {
                response.sendRedirect("asset?service=manageAsset");
            }
        }

        if (service.equals("deleteAsset")) {
            String assetIdStr = request.getParameter("assetId");
            int assetId = Integer.parseInt(assetIdStr);

            if (assetDao.deleteAsset(assetId)) {
                response.sendRedirect("asset?service=manageAsset");
            }
        }
        
    }

    private void addAssetIntoRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        AssetDAO assetDao = new AssetDAO();
        int assetId = Integer.parseInt(request.getParameter("assetId"));
        int quantity = 1; // Assuming you have a quantity parameter

        if (assetDao.insertRoomAsset(roomId, assetId, quantity)) {
            response.sendRedirect("asset?service=ShowAssetIntoRoom&assetId=" + assetId);
        }
    }

    private void removeAssetFromRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        AssetDAO assetDao = new AssetDAO();
        int assetId = Integer.parseInt(request.getParameter("assetId"));

        boolean isRemoved = assetDao.removeRoomAsset(roomId, assetId);
        if (isRemoved) {
            response.sendRedirect("asset?service=ShowAssetIntoRoom&assetId=" + assetId);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
