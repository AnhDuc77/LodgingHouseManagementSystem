/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LodgingHousesDAO;
import dal.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.ldap.Rdn;
import model.LodgingHouse;

/**
 *
 * @author admin
 */
@WebServlet(name = "ReportLanlord", urlPatterns = {"/report-lanlord"})
public class ReportLanlord extends HttpServlet {

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
            out.println("<title>Servlet ReportLanlord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportLanlord at " + request.getContextPath() + "</h1>");
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
        ReportDAO reportDao = new ReportDAO();
        LodgingHousesDAO lodDAO = new LodgingHousesDAO();

        // Lấy danh sách các năm từ ReportDAO
        List<Integer> listYear = reportDao.getAllYear();
        int maxYear = Collections.max(listYear); // Tìm năm lớn nhất trong danh sách

        // Lấy danh sách tất cả các nhà trọ
        List<LodgingHouse> listLodging = lodDAO.getAllLodgingHouse();

        int count = 0;
        long chi = 0;

        // Tính tổng count và chi cho từng nhà trọ trong năm lớn nhất
        for (LodgingHouse lodgingHouse : listLodging) {
            count += reportDao.getTotalPriceReciptCostInYear(maxYear, lodgingHouse.getLodgingHouseId());
            chi += reportDao.getTotalPriceInvestmentCostInYear(maxYear, lodgingHouse.getLodgingHouseId());
        }
        if (count > 500000000) {
            count = (int) (count - count * (0.05 + 0.05 + 1000000));

        } else if (count <= 500000000 && count > 300000000) {
            count = (int) (count - count * (0.05 + 0.05 + 500000));
        } else if (count <= 300000000 && count >= 100000000) {
            count = (int) (count - count * (0.05 + 0.05 + 300000));
        }
        // Đặt các thuộc tính để gửi sang JSP
        request.setAttribute("listYear", listYear);
        request.setAttribute("totalPrice", count);
        request.setAttribute("totalPriceCost", chi);

        // Chuyển hướng sang trang JSP để hiển thị kết quả
        //---------------Thống kê theo tháng------------------------------------- 
        HashMap<Integer, Integer> listDay = new HashMap<>();

// Lặp qua từng lodging house trong danh sách listLodging
        for (LodgingHouse lodgingHouse : listLodging) {
            // Lấy danh sách giá trị ngày từ reportDao
            HashMap<Integer, Integer> priceDateMap = reportDao.getPriceDateInYear(lodgingHouse.getLodgingHouseId(), maxYear);

            // Lặp qua từng cặp khóa và giá trị trong priceDateMap
            for (Map.Entry<Integer, Integer> entry : priceDateMap.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                System.out.println("key: "+key);
                
                System.out.println("value: "+value);
                // Kiểm tra xem key đã tồn tại trong listDay chưa
                if (listDay.containsKey(key)) {
               
                    listDay.put(key, listDay.get(key) + value);
                } else {
                    // Nếu chưa tồn tại, thêm mới key với giá trị tương ứng
                    listDay.put(key, value);
                }
            }
        }

        request.setAttribute("profitInYear", listDay);
        
        request.setAttribute("year", maxYear);


        request.getRequestDispatcher("view/landlord/profit-and-loss-statement.jsp").forward(request, response);
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
