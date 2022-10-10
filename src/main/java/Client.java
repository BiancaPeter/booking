import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    public Client(String lastName, String firstName) {
        super(lastName, firstName);
    }

    public void getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        System.out.println("The list of available rooms during the period " + checkIn + " - " + checkOut + " are: ");
        for (Hotel hotel : booking.getHotelList()) {
            for (Room room : hotel.getRoomList()) {
                boolean isAvailable = true;
                if (room.getNumberOfPerson() == numberOfPerson) {
                    if (room.getReservationList() == null) {
                        System.out.println("Hotel " + hotel.getHotelName() + " room " + room);
                    } else {
                        for (Reservation reservation : room.getReservationList()) {
                            if (reservation.getCheckIn() == checkIn && reservation.getCheckOut() == checkOut) {
                                isAvailable = false;
                            }
                        }
                        if (isAvailable) {
                            System.out.println("Hotel " + hotel.getHotelName() + " room " + room);
                        }
                    }
                }
            }
        }
    }


    public List<Room> getAvailableRoomsOrderedByPriceBy(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        List<Room> sortedListOfAvailableRooms = new ArrayList<>();
        boolean isAvailable = true;
        for (Hotel hotel : booking.getHotelList()) {
            for (Room room : hotel.getRoomList()) {
                if (room.getNumberOfPerson() == numberOfPerson) {
                    if (room.getReservationList() == null) {
                        sortedListOfAvailableRooms.add(room);
                    } else {
                        for (Reservation reservation : room.getReservationList()) {
                            if (reservation.getCheckIn() == checkIn && reservation.getCheckOut() == checkOut) {
                                isAvailable = false;
                            }
                        }
                        if (isAvailable) {
                            sortedListOfAvailableRooms.add(room);
                        }
                    }
                }
            }
        }
        sortedListOfAvailableRooms.sort(new PriceComparator());
        return sortedListOfAvailableRooms;
    }

    public void bookARoom(int roomNumber, Hotel hotel, LocalDate checkIn, LocalDate checkOut) throws RoomNotFoundException {
        int indexOfRoom = hotel.getIndexOfRoomFromHotelBy(roomNumber);
        if (indexOfRoom == -1) {
            throw new RoomNotFoundException("The room with numberRoom " + roomNumber + " is not in the hotel " + hotel.getHotelName());
        }
        Reservation reservation = new Reservation(hotel.getRoomList().get(indexOfRoom).getRoomNumber(), checkIn, checkOut);
        reservationList.add(reservation);
        hotel.getRoomList().get(indexOfRoom).getReservationList().add(reservation);
    }
}
