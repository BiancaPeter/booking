import java.time.LocalDate;

public class Reservation {
    private int roomNumber;
    private Client client;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(int roomNumber, Client client, LocalDate checkIn, LocalDate checkOut) {
        this.roomNumber = roomNumber;
        this.client = client;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", client=" + client +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
