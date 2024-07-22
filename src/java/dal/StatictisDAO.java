/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.StaticRevenue;

/**
 *
 * @author ASUS
 */
public class StatictisDAO extends DBContext {

    public StaticRevenue getRevenueByYear(int lodgingHouseId, int year) {
        StaticRevenue revenue = new StaticRevenue();
        String sql = "SELECT \n"
                + "    r.LodgingHouseId,\n"
                + "    YEAR(b.MonthYear) AS Year,\n"
                + "    SUM(b.Paid) AS TotalRevenue\n"
                + "FROM \n"
                + "    Bill b\n"
                + "JOIN \n"
                + "    Rooms r ON b.RoomId = r.RoomId\n"
                + "WHERE \n"
                + "    YEAR(b.MonthYear) = ?\n"
                + "	\n"
                + "    AND r.LodgingHouseId = ?\n"
                + "GROUP BY \n"
                + "    r.LodgingHouseId, \n"
                + "    YEAR(b.MonthYear)\n"
                + "ORDER BY \n"
                + "    r.LodgingHouseId, \n"
                + "    Year;";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, year);
            st.setInt(2, lodgingHouseId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                revenue = new StaticRevenue(rs.getInt("LodgingHouseId"), year, rs.getDouble("TotalRevenue"));
                return revenue;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving revenue by year: " + e.getMessage());
        }

        return revenue;
    }

    // Method to get revenue by month for a specific year
    public StaticRevenue getRevenueByMonth(int lodgingHouseId, int year, int month) {
        StaticRevenue revenue = new StaticRevenue();
        String sql = "SELECT \n"
                + "    r.LodgingHouseId,\n"
                + "    YEAR(b.MonthYear) AS Year,\n"
                + "    MONTH(b.MonthYear) AS Month,\n"
                + "    SUM(b.Paid) AS TotalRevenue\n"
                + "FROM \n"
                + "    Bill b\n"
                + "JOIN \n"
                + "    Rooms r ON b.RoomId = r.RoomId\n"
                + "WHERE \n"
                + "    YEAR(b.MonthYear) = ?\n"
                + "    AND MONTH(b.MonthYear) = ?\n"
                + "    AND r.LodgingHouseId = ?\n"
                + "GROUP BY \n"
                + "    r.LodgingHouseId, \n"
                + "    YEAR(b.MonthYear), \n"
                + "    MONTH(b.MonthYear)\n"
                + "ORDER BY \n"
                + "    r.LodgingHouseId, \n"
                + "    Year, \n"
                + "    Month;";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, year);
            st.setInt(2, month);
            st.setInt(3, lodgingHouseId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                revenue = new StaticRevenue(rs.getInt("LodgingHouseId"), year, month, rs.getDouble("TotalRevenue"));
                return revenue;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving revenue by month: " + e.getMessage());
        }

        return revenue;
    }

    
    public List<Integer> listYear(){
        List<Integer> list = new ArrayList<>();
        String sql = "	SELECT DISTINCT YEAR(MonthYear) AS Year\n"
                + "FROM Bill\n"
                + "ORDER BY Year;";
        int a;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a = rs.getInt("Year");
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving revenue by month: " + e.getMessage());
        }
        return list;
    }
    public static void main(String[] args) {
        StatictisDAO s = new StatictisDAO();
        for(Integer st : s.listYear()){
            System.out.println(st);
        }
        System.out.println(s.getRevenueByYear(2, 2024));
    }
}
