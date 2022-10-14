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
        room.setHotelName(hotel.getHotelName()); //setez numele hotelului pentru obiectul camera
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
        System.out.println("The number of available rooms during the period " + checkIn + " - " + checkOut + " are: ");
        int numberOfAvailableRooms = 0;
        boolean isAvailable;
        for (Room room : hotel.getRoomList()) {
            isAvailable = true;
            for (Reservation reservation : room.getReservationList()) {
                if (reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)) {
                    isAvailable = false;
                }
            }
            if (isAvailable) {
                numberOfAvailableRooms++;
            }
        }
        return numberOfAvailableRooms;
    }

    public long findNumberOfAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        return hotel.getRoomList().stream()
                .filter(room -> !room.areReservationsAfterCheckinAndBeforeCheckout(checkIn, checkOut))
                .count();
    }

    public long getPriceForAllReservationsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        long totalPrice = 0;
        long numberOfDaysReservedForTheRoom;
        for (Room room : hotel.getRoomList()) {
            for (Reservation reservation : room.getReservationList()) {
                numberOfDaysReservedForTheRoom = ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
                if (reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)) {
                    totalPrice += room.getPricePerNight() * numberOfDaysReservedForTheRoom;
                }
            }
        }
        return totalPrice;
    }

    public Integer findPriceForAllReservationsBy(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        return hotel.getRoomList().stream()
                .filter(room -> room.areReservationsAfterCheckinAndBeforeCheckout(checkIn, checkOut))
                .reduce((0), (sum, room) -> sum + (room.getPricePerNight() * (int) (ChronoUnit.DAYS.between(checkIn, checkOut))), Integer::sum);

    }
}
