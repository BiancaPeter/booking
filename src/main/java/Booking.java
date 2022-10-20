import java.util.ArrayList;
import java.util.List;

public class Booking {
    private List<Hotel> hotelList;
    private List<User> userList;

    public Booking() {
        this.hotelList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
