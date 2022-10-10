import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String hotelName;
    private List<Room> roomList;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.roomList = new ArrayList<>();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Room getRoomFromHotelByNumber(int roomNumber) {
        for (Room room : getRoomList()) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public int getIndexOfRoomFromHotelBy(int roomNumber) {
        for (int i = 0; i < roomList.size(); i++) {
            if (getRoomList().get(i).getRoomNumber() == roomNumber) {
                return i;
            }
        }
        return -1;
    }

}

