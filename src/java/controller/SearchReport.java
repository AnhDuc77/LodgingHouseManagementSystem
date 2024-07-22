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
import model.LodgingHouse;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchReport", urlPatterns = {"/search-report"})
public class SearchReport extends HttpServlet {

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
        String year_raw = request.getParameter("year");
        System.out.println(year_raw);
        int year = Integer.parseInt(year_raw);
        ReportDAO reportDao = new ReportDAO();
        LodgingHousesDAO lodDAO = new LodgingHousesDAO();
        List<LodgingHouse> listLodging = lodDAO.getAllLodgingHouse();
        List<Integer> listYear = reportDao.getAllYear();
        double count = 0;
        long chi = 0;

        // Tính tổng count và chi cho từng nhà trọ trong năm lớn nhất
        for (LodgingHouse lodgingHouse : listLodging) {
            count += reportDao.getTotalPriceReciptCostInYear(year, lodgingHouse.getLodgingHouseId());
            chi += reportDao.getTotalPriceInvestmentCostInYear(year, lodgingHouse.getLodgingHouseId());
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
        request.getRequestDispatcher("view/landlord/profit-and-loss-statement.jsp").forward(request, response);
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

        String year_raw = request.getParameter("year");
        System.out.println("Selected year: " + year_raw);

        if (year_raw == null || year_raw.isEmpty()) {
            // Xử lý khi không có năm được chọn
            // Chẳng hạn có thể gửi thông báo lỗi về client hoặc xử lý mặc định
            response.getWriter().println("Please select a valid year.");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(year_raw);
        } catch (NumberFormatException e) {
            // Xử lý khi không thể chuyển đổi năm sang số nguyên
            response.getWriter().println("Invalid year format.");
            return;
        }

        ReportDAO reportDao = new ReportDAO();
        LodgingHousesDAO lodDAO = new LodgingHousesDAO();
        List<LodgingHouse> listLodging = lodDAO.getAllLodgingHouse();
        List<Integer> listYear = reportDao.getAllYear();
        double count = 0;
        long chi = 0;

        // Tính tổng count và chi cho từng nhà trọ trong năm lớn nhất
        for (LodgingHouse lodgingHouse : listLodging) {
            count += reportDao.getTotalPriceReciptCostInYear(year, lodgingHouse.getLodgingHouseId());
            chi += reportDao.getTotalPriceInvestmentCostInYear(year, lodgingHouse.getLodgingHouseId());
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
        
        HashMap<Integer, Integer> listDay = new HashMap<>();

// Lặp qua từng lodging house trong danh sách listLodging
        for (LodgingHouse lodgingHouse : listLodging) {
            // Lấy danh sách giá trị ngày từ reportDao
            HashMap<Integer, Integer> priceDateMap = reportDao.getPriceDateInYear(lodgingHouse.getLodgingHouseId(), year);

            // Lặp qua từng cặp khóa và giá trị trong priceDateMap
            for (Map.Entry<Integer, Integer> entry : priceDateMap.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                System.out.println("key: "+key);
                
                System.out.println("value: "+value);
                // Kiểm tra xem key đã tồn tại trong listDay chưa
                if (listDay.containsKey(key)) {
                    // Nếu đã tồn tại, cộng dồn giá trị hiện tại với giá trị mới
                    listDay.put(key, listDay.get(key) + value);
                } else {
                    // Nếu chưa tồn tại, thêm mới key với giá trị tương ứng
                    listDay.put(key, value);
                }
            }
        }

        request.setAttribute("profitInYear", listDay);
        
        request.setAttribute("year", year);

        
        
        
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
