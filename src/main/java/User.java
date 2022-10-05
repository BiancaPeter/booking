import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String lastName;
    private String firstName;
    List<Reservation> reservationList;


    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.reservationList = new ArrayList<>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", reservationList=" + reservationList +
                '}';
    }

}
