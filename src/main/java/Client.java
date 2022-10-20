import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Client extends User {
    public Client(String lastName, String firstName) {
        super(lastName, firstName);
    }

    public List<Room> getAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        List<Room> availableRooms = new ArrayList<>();
        for (Hotel hotel : booking.getHotelList()) {
            for (Room room : hotel.getRoomList()) {
                if (room.getNumberOfPerson() == numberOfPerson) {
                    if (room.getReservationList().isEmpty()) {
                        availableRooms.add(room);
                    } else {
                        for (Reservation reservation : room.getReservationList()) {
                            if (reservation.getCheckOut().isBefore(checkIn) && reservation.getCheckIn().isAfter(checkOut)) {
                                availableRooms.add(room);
                            }
                        }
                    }
                }
            }
        }
        return availableRooms;
    }

    public List<Room> findAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        return booking.getHotelList().stream()
                .flatMap(hotel -> hotel.getRoomList().stream())
                .filter(room -> !room.isReservedRoomBetween(checkIn, checkOut) && room.getNumberOfPerson() == numberOfPerson)
                .collect(Collectors.toList());
    }

    public List<Room> getAvailableRoomsOrderedByPriceBy(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        List<Room> sortedListOfAvailableRooms = getAvailableRoomsBy(checkIn, checkOut, numberOfPerson, booking);
        Collections.sort(sortedListOfAvailableRooms);
        return sortedListOfAvailableRooms;
    }

    public List<Room> findAvailableRoomsOrderedByPriceBy(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
        return booking.getHotelList().stream()
                .flatMap(hotel -> hotel.getRoomList().stream())
                .filter(room -> !room.isReservedRoomBetween(checkIn, checkOut) && room.getNumberOfPerson() == numberOfPerson)
                .sorted(Comparator.comparingInt(Room::getPricePerNight))
                .collect(Collectors.toList());
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
