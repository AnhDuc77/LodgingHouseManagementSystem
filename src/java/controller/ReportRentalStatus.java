/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dal.ContractDAO;
import dal.LodgingHousesDAO;
import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.HashMap;
import model.LodgingHouse;

/**
 *
 * @author admin
 */
@WebServlet(name = "ReportRentalStatus", urlPatterns = {"/report-rental-status"})
public class ReportRentalStatus extends HttpServlet {

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
            out.println("<title>Servlet ReportRentalStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportRentalStatus at " + request.getContextPath() + "</h1>");
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

        ContractDAO contractDAO = new ContractDAO();
        RoomDAO roomDAO = new RoomDAO();
        LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
        List<Integer> listYear = contractDAO.getYear();
        HashMap<String, Integer> map = roomDAO.countStatus(0);
        List<LodgingHouse> listLodgingHouse = lodgingHousesDAO.getAllLodgingHouseExited();

        List<Object[]> list = roomDAO.countStatusLodgingHouse();
        Gson gson = new Gson();
        String wineProductionJson = gson.toJson(map);
        String listJSON = gson.toJson(list);
        request.setAttribute("listYear", listYear);
        request.setAttribute("wineProductionJson", wineProductionJson);
        request.setAttribute("listJSON", listJSON);
        request.setAttribute("listLodgingHouse", listLodgingHouse);
        request.getRequestDispatcher("view/landlord/report-rental-status.jsp").forward(request, response);
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
        String idLodgingHouse = request.getParameter("lodgingHouse");

        System.out.println("check");
        System.out.println(idLodgingHouse);
        try {
            ContractDAO contractDAO = new ContractDAO();
            RoomDAO roomDAO = new RoomDAO();
            LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();
            List<Integer> listYear = contractDAO.getYear();
            HashMap<String, Integer> map = roomDAO.countStatus(Integer.parseInt(idLodgingHouse));
            List<LodgingHouse> listLodgingHouse = lodgingHousesDAO.getAllLodgingHouseExited();
            
            List<Object[]> list = roomDAO.countStatusLodgingHouse();
            Gson gson = new Gson();
            String lodgingHouseName = gson.toJson(idLodgingHouse);
            String wineProductionJson = gson.toJson(map);
            String listJSON = gson.toJson(list);
            request.setAttribute("listYear", listYear);
            request.setAttribute("wineProductionJson", wineProductionJson);
            request.setAttribute("listJSON", listJSON);
            request.setAttribute("listLodgingHouse", listLodgingHouse);
            request.setAttribute("lodgingHouseName", lodgingHouseName);
            request.getRequestDispatcher("view/landlord/report-rental-status.jsp").forward(request, response);
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
