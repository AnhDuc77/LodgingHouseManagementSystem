package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import model.ServiceOfLodgingHouse;
import model.ServiceOfRoom;

/**
 *
 * @author ASUS ZenBook
 */
public class ServiceOfRoomDAO extends DBContext{
    
    public List<ServiceOfRoom> getServiceOfRoom(int lodgingHouseId, String roomId) {
        List<ServiceOfRoom> list = new ArrayList<>();
        String sql = "select * from ServiceOfRoom WHERE RoomId = ?";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, roomId);
            ResultSet rs = st.executeQuery();
            ServiceDAO serviceDAO = new ServiceDAO();
            RoomDAO roomDAO = new RoomDAO();
            ServiceOfLodgingHouseDAO serviceOfLodgingHouseDAO = new ServiceOfLodgingHouseDAO();
            while (rs.next()) {
                ServiceOfLodgingHouse serviceOfLodgingHouse = serviceOfLodgingHouseDAO.getAllServiceOfLodgingHouseUnique(lodgingHouseId, rs.getInt("ServiceId"));
                Room room = roomDAO.getRoomsById(rs.getString("RoomId"));
                ServiceOfRoom serviceOfRoom = new ServiceOfRoom(serviceOfLodgingHouse, room);
                list.add(serviceOfRoom);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public ServiceOfRoom getServiceOfRoomByRoomIdAndServiceId(String roomId, int serviceId){
        ServiceOfRoom serviceOfRoom = null;

        String sql = "SELECT * FROM ServiceOfRoom where RoomId = ? and ServiceId = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, roomId);
            pstm.setInt(2, serviceId);
            try (ResultSet rs = pstm.executeQuery()) {             
                if (rs.next()) {
                    serviceOfRoom = new ServiceOfRoom(rs.getInt("ServiceId"), rs.getInt("RoomId"), rs.getDouble("Price"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        return serviceOfRoom;
    }
    
    public List<ServiceOfRoom> getAllServiceOfRoomByRoomId(String roomId){
        List<ServiceOfRoom> listOfSR = new ArrayList<>();

        String sql = "SELECT * FROM ServiceOfRoom where RoomId = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, roomId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    listOfSR.add(new ServiceOfRoom(
                            rs.getInt("ServiceId"),
                            rs.getInt("RoomId"),                        
                            rs.getDouble("Price")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        
        return listOfSR;
    }
    
    public boolean checkServiceBeingUsedAnyRoom(int lodgingHouseId, int serviceId){
        String sql = "SELECT * FROM ServiceOfRoom sr inner join Rooms r on r.RoomId = sr.RoomId "
                + "where r.LodgingHouseId = ? and sr.ServiceId = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, lodgingHouseId);
            pstm.setInt(2, serviceId);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL getAccountById: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("getAccountById: " + e.getMessage());
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        ServiceOfRoomDAO srd = new ServiceOfRoomDAO();
//        System.out.println(srd.getAllServiceOfRoomByRoomId("1").size());
        System.out.println(srd.checkServiceBeingUsedAnyRoom(1, 4));
    }
}
