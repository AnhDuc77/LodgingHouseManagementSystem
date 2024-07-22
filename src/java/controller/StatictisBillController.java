/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.FeedbackDAO;
import dal.InformationOfUserDAO;
import dal.LodgingHousesDAO;
import dal.RoomDAO;
import dal.StatictisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Account;
import model.LodgingHouse;
import model.StaticRevenue;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "StatictisBillController", urlPatterns = {"/statictis-bill"})
public class StatictisBillController extends HttpServlet {

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
        HttpSession session = request.getSession();

        //create Daos object.
        AccountDAO accountDao = new AccountDAO();
        InformationOfUserDAO userDao = new InformationOfUserDAO();
        RoomDAO roomDao = new RoomDAO();
        FeedbackDAO feedDao = new FeedbackDAO();
        LodgingHousesDAO lodDao = new LodgingHousesDAO();
        StatictisDAO staDao = new StatictisDAO();

        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
        }
        int id = account.getAccountID();
        String service = request.getParameter("service");
        if (service == null) {
            service = "";
        }
        if (service.equals("showStatisticRevenue")) {
            Date now = new Date();
            LodgingHouse lodging = lodDao.getLodgingHouseById(2);
            // Tạo một đối tượng Calendar và đặt thời gian hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);

            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            int currentYear = calendar.get(Calendar.YEAR);
            String yearR = request.getParameter("year");
            List<StaticRevenue> listRe = new ArrayList<>();
            if (yearR == null) {
                List<Integer> listYear = staDao.listYear();
                for (Integer year : listYear) {
                    listRe.add(staDao.getRevenueByYear(lodging.getLodgingHouseId(), year));
                }
            }else{
                int year = Integer.parseInt(yearR);
                for(int i = 1;i <= 12;i++){
                    listRe.add(staDao.getRevenueByMonth(lodging.getLodgingHouseId(), year,i));
                }
            }
            

            request.setAttribute("listRe", "listRe");
            request.getRequestDispatcher("view/landlord/statictis-revenue-landlord.jsp").forward(request, response);
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
