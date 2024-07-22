/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.LodgingHouse;

/**
 *
 * @author Admin
 */
public class ReportDAO extends DBContext {

    public int getTotalPriceInvestmentCostInYear(int year, int lodgingHouseId) {
        String sql = "SELECT SUM(Price) \n"
                + "FROM InvestmentCosts \n"
                + "WHERE YEAR([DateTime]) = ? AND LodgingHouseId = ?;";
        int totalPrice = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, year);
            stm.setInt(2, lodgingHouseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt(1); // Lấy tổng giá trị
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public int getTotalPriceInvestmentCostInMonth(int month, int year, int lodgingHouseId) {
        String sql = "SELECT Price \n"
                + "FROM InvestmentCosts \n"
                + "WHERE MONTH([DateTime]) = ? AND YEAR([DateTime]) = ? AND LodgingHouseId = ?;";
        int totalPrice = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, year);
            stm.setInt(3, lodgingHouseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalPrice += rs.getInt("Price");
            }

        } catch (Exception e) {
        }
        return totalPrice;
    }

    public int getTotalPriceReciptCostInYear(int year, int lodgingHouseId) {
        String sql = "SELECT SUM(Price) \n"
                + "FROM Receipt \n"
                + "WHERE YEAR([SubmitionDateTime]) = ? AND LodgingHouseId = ?;";
        int totalPrice = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, year);
            stm.setInt(2, lodgingHouseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt(1); // Lấy tổng giá trị
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public int getTotalPriceReceiptInQuarter(int quarter, int year, int lodgingHouseId) {
        String sql = "SELECT SUM(Price) \n"
                + "FROM Receipt \n"
                + "WHERE YEAR([SubmitionDateTime]) = ? AND "
                + "(MONTH([SubmitionDateTime]) BETWEEN ? AND ? ) AND LodgingHouseId = ?;";
        int totalPrice = 0;
        int startMonth = (quarter - 1) * 3 + 1; // Tháng bắt đầu của quý
        int endMonth = startMonth + 2;          // Tháng kết thúc của quý

        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, year);
            stm.setInt(2, startMonth);
            stm.setInt(3, endMonth);
            stm.setInt(4, lodgingHouseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt(1); // Lấy tổng giá trị
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public int getTotalPriceReceiptInMonth(int month, int year, int lodgingHouseId) {
        String sql = "SELECT SUM(Price) FROM Receipt WHERE MONTH([SubmitionDateTime]) = ? AND YEAR([SubmitionDateTime]) = ? AND LodgingHouseId = ?;";
        int totalPrice = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, year);
            stm.setInt(3, lodgingHouseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalPrice = rs.getInt(1);
            }

        } catch (SQLException e) {
        }
        return totalPrice;
    }

    public List<Integer> getAllYear() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR([DateTime]) AS Year FROM InvestmentCosts "
                + "UNION "
                + "SELECT DISTINCT YEAR([DateTime]) AS Year FROM Receipt";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int year = rs.getInt("Year");
                list.add(year);
            }
            rs.close();
            stm.close();

        } catch (SQLException e) {
        }
        return list;
    }

    public HashMap<Integer, Integer> getPriceDateInYear(int lodginHouseId, int year) {
        String sql = """
                     SELECT DISTINCT MONTH(SubmitionDateTime) as month, SUM(Price) as price FROM Receipt WHERE LodgingHouseId = ?  
                     AND YEAR(SubmitionDateTime) = ? GROUP BY  MONTH(SubmitionDateTime)""";
        HashMap<Integer, Integer> listDay = new HashMap<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lodginHouseId);

            stm.setInt(2, year);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listDay.put(rs.getInt("month"), rs.getInt("price"));
            }
        } catch (SQLException e) {
        }
        return listDay;
    }

    public static void main(String[] args) {
        ReportDAO dao = new ReportDAO();
        LodgingHousesDAO lodDAO = new LodgingHousesDAO();
        int count = 0;

        List<LodgingHouse> listLodging = lodDAO.getAllLodgingHouse();
        for (LodgingHouse lodgingHouse : listLodging) {
            System.out.println(lodgingHouse);
        }
        long chi = 0;
        for (LodgingHouse lodgingHouse : listLodging) {
            count += dao.getTotalPriceReciptCostInYear(2024, lodgingHouse.getLodgingHouseId());
            chi += dao.getTotalPriceInvestmentCostInYear(2024, lodgingHouse.getLodgingHouseId());
        }
        int thue = 0;
        System.out.println(count);

        System.out.println("Chi" + chi);
        if (count > 500000000) {
            count = (int) (count - count * (0.05 + 0.05 + 1000000));

        } else if (count <= 500000000 && count > 300000000) {
            count = (int) (count - count * (0.05 + 0.05 + 500000));
        } else if (count <= 300000000 && count >= 100000000) {
            count = (int) (count - count * (0.05 + 0.05 + 300000));

        }
        System.out.println(count);
    }
}
