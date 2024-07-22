/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dal.ReceiptDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import dal.LodgingHousesDAO;
import model.LodgingHouse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dal.BillDAO;
import java.util.HashMap;

/**
 *
 * @author admin
 */
@WebServlet(name = "ReportOnDebt", urlPatterns = {"/report-on-debt"})
public class ReportOnDebt extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReportOnDebt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportOnDebt at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        ReceiptDAO receiptDAO = new ReceiptDAO();
        BillDAO billDAO = new BillDAO();
        LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
        List<Integer> listYear = receiptDAO.getYearDebt();
        List<LodgingHouse> listLodgingHouse = lodgingHousesDAO.getAllLodgingHouseExited();

        Gson gson = new Gson();

        HashMap<String, Integer> debtAllLodgingHouse = new HashMap<>();
        for (LodgingHouse lodgingHouse : listLodgingHouse) {
            debtAllLodgingHouse.put(lodgingHouse.getNameLodgingHouse(),
                    receiptDAO.sumDebt(listYear.get(0), 1, lodgingHouse.getLodgingHouseId()) + billDAO.sumDebt(listYear.get(0), 1, lodgingHouse.getLodgingHouseId()));
        }
        String debtAllLodgingHouseJson = gson.toJson(debtAllLodgingHouse);
        String lable = "Biểu đồ báo cáo công nợ tháng 1 năm " + listYear.get(0) + " tất cả các nhà trọ";
        String lableJson = gson.toJson(lable);

        request.setAttribute("listYear", listYear);
        request.setAttribute("listLodgingHouse", listLodgingHouse);
        request.setAttribute("debtAllLodgingHouseJson", debtAllLodgingHouseJson);
        request.setAttribute("lableJson", lableJson);
        request.getRequestDispatcher("view/landlord/report-on-debt.jsp").forward(request, response);
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
        ReceiptDAO receiptDAO = new ReceiptDAO();
        BillDAO billDAO = new BillDAO();
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String lodgingHouse = request.getParameter("lodgingHouse");

        LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
        List<Integer> listYear = receiptDAO.getYearDebt();

        Gson gson = new Gson();
        try {
            List<LodgingHouse> listLodgingHouse = lodgingHousesDAO.getAllLodgingHouseExited();
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int lodgingHouseInt = Integer.parseInt(lodgingHouse);

            System.out.println("");
            HashMap<String, Integer> debtAllLodgingHouse = new HashMap<>();

            for (LodgingHouse lodgingHouse1 : listLodgingHouse) {
                debtAllLodgingHouse.put(lodgingHouse1.getNameLodgingHouse(),
                        receiptDAO.sumDebt(yearInt, monthInt, lodgingHouse1.getLodgingHouseId()) + billDAO.sumDebt(yearInt, monthInt, lodgingHouse1.getLodgingHouseId()));
            }
            String debtAllLodgingHouseJson = gson.toJson(debtAllLodgingHouse);
            String lable = "Biểu đồ báo cáo công nợ tháng" + monthInt + " năm " + yearInt + " tất cả các nhà trọ";
            String lableJson = gson.toJson(lable);
            String yearJson = gson.toJson(year);
            String monthJSon = gson.toJson(month);
            String lodgingHouseName = gson.toJson(lodgingHouse);

            request.setAttribute("listYear", listYear);
            request.setAttribute("listLodgingHouse", listLodgingHouse);
            request.setAttribute("debtAllLodgingHouseJson", debtAllLodgingHouseJson);
            request.setAttribute("lableJson", lableJson);
            request.setAttribute("yearJson", yearJson);
            request.setAttribute("monthJSon", monthJSon);
            request.setAttribute("lodgingHouseName", lodgingHouseName);

            if (lodgingHouseInt != 0) {
                String lable1 = "Biểu đồ báo cáo công các tháng trong năm của trọ ";
                String lable1Json = gson.toJson(lable);
                HashMap<String, Integer> debtAllLodgingHouse1 = new HashMap<>();
                for (int i = 1; i <= 12; i++) {
                    debtAllLodgingHouse1.put("Tháng " + i, receiptDAO.sumDebt(yearInt, i, lodgingHouseInt) + billDAO.sumDebt(yearInt, i, lodgingHouseInt) );
                }
                String debtAllLodgingHouseJSon1 = gson.toJson(debtAllLodgingHouse1);
                request.setAttribute("label1", lable1Json);
                request.setAttribute("debt1", debtAllLodgingHouseJSon1);
                
            }

            request.getRequestDispatcher("view/landlord/report-on-debt.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
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
