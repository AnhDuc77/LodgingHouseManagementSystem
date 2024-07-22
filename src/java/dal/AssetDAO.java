/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Asset;
import model.Room;
import model.RoomAsset;

/**
 *
 * @author ASUS
 */
public class AssetDAO extends DBContext {

    public List<Asset> getAllAssetsByLodgingHouseId(int lodginghouseId, String assetSearch) {
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT * FROM Asset WHERE LodgingHouseId = ? and assetName like '%" + assetSearch + "%'";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, lodginghouseId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Asset asset = new Asset(
                            rs.getInt("AssetId"),
                            rs.getString("AssetName"),
                            rs.getString("Description"),
                            rs.getString("Status"),
                            rs.getDouble("Price"),
                            rs.getInt("Quantity"),
                            rs.getInt("LodgingHouseId")
                    );
                    assets.add(asset);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAllAssetsByLodgingHouseId: " + e.getMessage());
        }
        return assets;
    }

    public List<RoomAsset> getAssetByRoom(String roomId) {
        List<RoomAsset> assets = new ArrayList<>();
        String sql = "SELECT * FROM dbo.RoomAssets\n"
                + "WHERE RoomId=?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, roomId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    RoomAsset a = new RoomAsset(rs.getString("RoomId"), rs.getInt("assetId"), rs.getInt("quantity"));
                    assets.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAllAssetsByLodgingHouseId: " + e.getMessage());
        }
        return assets;
    }

    public boolean insertAsset(Asset asset) {
        String sql = "INSERT INTO Asset (AssetName, Description, Status, Price, Quantity, LodgingHouseId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, asset.getAssetName());
            pstm.setString(2, asset.getDescription());
            pstm.setString(3, asset.getStatus());
            pstm.setDouble(4, asset.getPrice());
            pstm.setInt(5, asset.getQuantity());
            pstm.setInt(6, asset.getLodginghouseId());
            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL insertAsset: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật thông tin tài sản
    public boolean updateAsset(Asset asset) {
        String sql = "UPDATE Asset SET AssetName = ?, Description = ?, Status = ?, Price = ?, Quantity = ?, LodgingHouseId = ? WHERE AssetId = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, asset.getAssetName());
            pstm.setString(2, asset.getDescription());
            pstm.setString(3, asset.getStatus());
            pstm.setDouble(4, asset.getPrice());
            pstm.setInt(5, asset.getQuantity());
            pstm.setInt(6, asset.getLodginghouseId());
            pstm.setInt(7, asset.getAssetId());
            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL updateAsset: " + e.getMessage());
        }
        return false;
    }

    public List<Room> getRoomsHaveAsset(int lodgingHouseId) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT r.*\n"
                + "FROM Rooms r\n"
                + "JOIN RoomAssets ra ON r.roomId = ra.roomId\n"
                + "JOIN Asset a ON ra.assetId = a.assetId\n"
                + "WHERE a.lodginghouseId = ?;";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lodgingHouseId);

            ResultSet rs = st.executeQuery();

            LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();

            while (rs.next()) {
                Room room = new Room(rs.getString("RoomId"),
                        rs.getInt("Price"),
                        rs.getInt("MaxOfQuantity"),
                        rs.getInt("Status"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        lodgingHousesDAO.getLodgingHouseById(rs.getInt("LodgingHouseId")),
                        rs.getInt("StatusDelete"),
                        rs.getString("RoomName"));

                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Room> getRoomsHaveNotAsset(int lodgingHouseId) {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT r.*\n"
                + "FROM Rooms r\n"
                + "WHERE r.roomId NOT IN (\n"
                + "    SELECT ra.roomId\n"
                + "    FROM dbo.RoomAssets ra\n"
                + "    JOIN Asset a ON ra.assetId = a.assetId\n"
                + "    WHERE a.lodginghouseId = ?\n"
                + ");";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, lodgingHouseId);

            ResultSet rs = st.executeQuery();

            LodgingHousesDAO lodgingHousesDAO = new LodgingHousesDAO();

            while (rs.next()) {
                Room room = new Room(rs.getString("RoomId"),
                        rs.getInt("Price"),
                        rs.getInt("MaxOfQuantity"),
                        rs.getInt("Status"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        lodgingHousesDAO.getLodgingHouseById(rs.getInt("LodgingHouseId")),
                        rs.getInt("StatusDelete"),
                        rs.getString("RoomName"));

                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean insertRoomAsset(String roomId, int assetId, int quantity) {
        String sql = "INSERT INTO RoomAssets (RoomId, AssetId, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            stmt.setInt(2, assetId);
            stmt.setInt(3, quantity);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeRoomAsset(String roomId, int assetId) {
        String sql = "DELETE FROM RoomAssets WHERE RoomId = ? AND AssetId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            stmt.setInt(2, assetId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa tài sản
    public boolean deleteAsset(int assetId) {
        String sql = "DELETE FROM Asset WHERE AssetId = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, assetId);
            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL deleteAsset: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        AssetDAO a = new AssetDAO();
        List<Room> st = a.getRoomsHaveNotAsset(2);
//        for (Room s : st) {
//            System.out.println(s);
//        }
        System.out.println(a.insertRoomAsset("2", 1, 1));
    }

}
