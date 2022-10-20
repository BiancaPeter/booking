import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Admin extends User {
    public Admin(String lastName, String firstName) {
        super(lastName, firstName);
    }

    public void addHotel(Hotel hotel, Booking booking) {
        booking.getHotelList().add(hotel);
    }


    public void addRoom(Room room, Hotel hotel) {
        room.setHotelName(hotel.getHotelName());
        hotel.getRoomList().add(room);
    }

    public void removeRoom(int numberRoom, Hotel hotel) throws RoomNotFoundException {
        Room room = hotel.getRoomFromHotelByNumber(numberRoom);
        if (room == null) {
            throw new RoomNotFoundException("The room with numberRoom " + numberRoom + " is not in the hotel " + hotel.getHotelName());
        }
        hotel.getRoomList().remove(room);
    }

    public void printAllRooms(Hotel hotel) {
        System.out.println(hotel.getRoomList());
    }

    public void editPriceOfRoom(Hotel hotel, int numberRoom, int newPrice) throws RoomNotFoundException {
        Room room = hotel.getRoomFromHotelByNumber(numberRoom);
        if (room == null) {
            throw new RoomNotFoundException("The room with numberRoom " + numberRoom + " is not in the hotel " + hotel.getHotelName());
        }
        hotel.getRoomList().get(hotel.getRoomList().indexOf(room)).setPricePerNight(newPrice);
    }

    public int getNumberOfAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        int numberOfAvailableRooms = 0;
        for (Room room : hotel.getRoomList()) {
            if (room.getReservationList().isEmpty()) {
                numberOfAvailableRooms++;
            } else {
                for (Reservation reservation : room.getReservationList()) {
                    if ((reservation.getCheckOut().isBefore(checkIn) && reservation.getCheckIn().isAfter(checkOut))) {
                        numberOfAvailableRooms++;
                    }
                }
            }
        }
        return numberOfAvailableRooms;
    }

    public long findNumberOfAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        return hotel.getRoomList().stream()
                .filter(room -> !room.isReservedRoomBetween(checkIn, checkOut))
                .count();
    }

    public long getPriceForAllReservationsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        long totalPrice = 0;
        long numberOfDaysReservedForTheRoom;
        for (Room room : hotel.getRoomList()) {
            for (Reservation reservation : room.getReservationList()) {
                if (reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)) {
                    numberOfDaysReservedForTheRoom = ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
                    totalPrice += room.getPricePerNight() * numberOfDaysReservedForTheRoom;
                }
            }
        }
        return totalPrice;
    }

    public Integer findPriceForAllReservationsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        return hotel.getRoomList().stream()
                .filter(room -> room.isReservedRoomBetween(checkIn, checkOut))
                .reduce((0), (sum, room) -> sum + (room.getPricePerNight() * (int) (ChronoUnit.DAYS.between(checkIn, checkOut))), Integer::sum);

    }
}
