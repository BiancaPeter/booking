import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private int pricePerNight;
    private int numberOfPerson;
    private List<Reservation> reservationList = new ArrayList<>();

    public Room(int roomNumber, int pricePerNight, int numberOfPerson) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.numberOfPerson = numberOfPerson;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numberRoom=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", numberOfPerson=" + numberOfPerson +
                ", reservationList=" + reservationList +
                '}';
    }


}
