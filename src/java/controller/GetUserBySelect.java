/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import utils.Pagination;

/**
 *
 * @author admin
 */
@WebServlet(name = "GetUserBySelect", urlPatterns = {"/get-user-by-select-sort"})
public class GetUserBySelect extends HttpServlet {

    String selected;

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
            out.println("<title>Servlet GetUserBySelect</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetUserBySelect at " + request.getContextPath() + "</h1>");
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
        String select = request.getParameter(" selected");
        System.out.println(select + " sort by ");
        if (select != null) {
            selected = select;

        } else {
            select = selected;
        }
        AccountDAO ac = new AccountDAO();
        List<Account> list = new ArrayList<>();

        if (select.equalsIgnoreCase("Lowest")) {
            list = ac.getAccountBySortNameAsc();
        } else if (select.equalsIgnoreCase("Highest")) {
            list = ac.getAccountBySortNameDesc();
        } else if (select.equalsIgnoreCase("DateLowest")) {
            list = ac.getAccountBySortDateAsc();
        } else if (select.equalsIgnoreCase("DaTeHighest")) {
            list = ac.getAccountBySortDateDesc();
        }

        String curentPageRaw = request.getParameter("curentPage");
        int numberPerPage = 5;
        Pagination<Account> pagination = new Pagination<>(curentPageRaw, numberPerPage, list);

        PrintWriter out = response.getWriter();
        out.println("""
                 <table class="table table-hover table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th width="10"><input type="checkbox" id="selectAll"></th>
                                                                    <th>ID</th>
                                                                    <th>Email</th>
                                                                    <th>T\u00ean \u0111\u1ea7y \u0111\u1ee7</th>
                                                                    <th>Qu\u00ea qu\u00e1n</th>
                                                                    <th>Ng\u00e0y sinh</th>
                                                                    <th>S\u1ed1 \u0111i\u1ec7n tho\u1ea1i</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                """);
        for (Account c : pagination.getListObject()) {
            out.println("""
                        <tr>
                                                                              <td><input type="checkbox" class="rowCheckbox"></td>
                                                                              <td>""" + c.getAccountID() + "</td>\n"
                    + "                                                        <td>" + c.getEmail() + "</td>\n"
                    + "                                                        <td>" + c.getFullName() + "</td>   \n"
                    + "                                                        <td>" + c.getProvince() + "-" + c.getDistrict() + "-" + c.getWard() + "</td>\n"
                    + "                                                        <td>" + c.getDob() + "</td>\n"
                    + "                                                        <td>" + c.getPhoneNumber() + "</td>\n"
                    + "                                                    </tr>\n");
        }
        out.println("""
                                                                </tbody>
                                                            </table>""");
        out.print("<div class=\"pagination\">");
        if (pagination.getCurentPage() > 1) {
            out.print("<a class=\"pagination-btn\" data-page=\"" + (pagination.getCurentPage() - 1) + "\" value=\"" + (pagination.getCurentPage() - 1) + "\">&laquo;</a>");
        }
        for (int num = pagination.getStart(); num <= pagination.getEnd(); num++) {
            if (num != 0) {
                if (num == pagination.getCurentPage()) {
                    out.print("<a class=\"pagination-btn active\" data-page=\"" + num + "\" value=\"" + num + "\">" + num + "</a>");
                } else {
                    out.print("<a class=\"pagination-btn\" data-page=\"" + num + "\" value=\"" + num + "\">" + num + "</a>");
                }
            }
        }
        if (pagination.getNumberOfPage() > pagination.getCurentPage()) {
            out.print("<a class=\"pagination-btn\" data-page=\"" + (pagination.getCurentPage() + 1) + "\" value=\"" + (pagination.getCurentPage() + 1) + "\">&raquo;</a>");
        }
        out.print("</div>");
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
