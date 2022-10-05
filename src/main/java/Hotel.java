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

}

