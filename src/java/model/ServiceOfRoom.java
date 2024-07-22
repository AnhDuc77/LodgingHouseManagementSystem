package model;

/**
 *
 * @author ASUS ZenBook
 */
public class ServiceOfRoom {
    private ServiceOfLodgingHouse serviceOfLodgingHouse;
    private Room room;

    public ServiceOfRoom(ServiceOfLodgingHouse serviceOfLodgingHouse, Room room) {
        this.serviceOfLodgingHouse = serviceOfLodgingHouse;
        this.room = room;
    }

    public ServiceOfRoom(int aInt, int aInt0, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ServiceOfLodgingHouse getServiceOfLodgingHouse() {
        return serviceOfLodgingHouse;
    }

    public void setServiceOfLodgingHouse(ServiceOfLodgingHouse serviceOfLodgingHouse) {
        this.serviceOfLodgingHouse = serviceOfLodgingHouse;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getServiceId() {
        return serviceOfLodgingHouse.getServiceId();
    }

    public double getPrice() {
        return serviceOfLodgingHouse.getPrice();
    }
    
    
}
