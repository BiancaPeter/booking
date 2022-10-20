import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Room implements Comparable<Room> {
    private int roomNumber;
    private int pricePerNight;
    private int numberOfPerson;
    private String hotelName = "";
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public boolean isReservedRoomBetween(LocalDate checkIn, LocalDate checkOut) {
        return reservationList.stream().
                anyMatch(reservation -> reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut));
    }

    @Override
    public int compareTo(Room anotherRoom) {
        return Integer.compare(pricePerNight, anotherRoom.getPricePerNight());
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", numberOfPerson=" + numberOfPerson +
                ", hotelName='" + hotelName + '\'' +
                ", reservationList=" + reservationList +
                '}';
    }
}
