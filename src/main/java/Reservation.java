import java.time.LocalDate;

public class Reservation {
    private int roomNumber;
    private Client client;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
