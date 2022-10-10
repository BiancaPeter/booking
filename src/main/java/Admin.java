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

    public int getNumberOfAvailableRooms(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        System.out.println("The number of available rooms during the period " + checkIn + " - " + checkOut + " are: ");
        int numberOfAvailableRooms = 0;
        boolean isAvailable = true;
        for (Room room : hotel.getRoomList()) {
            if (room.getReservationList() == null) {
                numberOfAvailableRooms++;
            } else {
                for (Reservation reservation : room.getReservationList()) {
                    if (reservation.getCheckIn() == checkIn && reservation.getCheckOut() == checkOut) {
                        isAvailable = false;
                    }
                }
                if (isAvailable) {
                    numberOfAvailableRooms++;
                }
            }
        }
        return numberOfAvailableRooms;
    }

//    public int findNumberOfAvailableRooms(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
//        return hotel.getRoomList().stream()
//                .filter(room -> room.getReservationList().stream().anyMatch(reservation -> (reservation.getCheckIn().isEqual(checkIn)|| (reservation.getCheckIn().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)))&&(reservation.getCheckOut() == checkOut || (reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckOut().isBefore(checkOut))))
//
//    }

    public long getPriceObtainedFromAllReservationsFromACertainPeriod(LocalDate checkIn, LocalDate checkOut, Hotel hotel) {
        long totalPrice = 0;
        long numberOfDaysReservedForTheRoom;
        for (Room room : hotel.getRoomList()) {
            if (room.getReservationList() != null) { //am pus conditia aceasta pt a elimina eroarea null pointer exception, trebuie tratata exceptia
                for (Reservation reservation : room.getReservationList()) {
                    numberOfDaysReservedForTheRoom = ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut()); //calculez numarul de zile rezervate
                    if (reservation.getCheckIn() == checkIn || (reservation.getCheckIn().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut))) {
                        if (reservation.getCheckOut() == checkOut || (reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckOut().isBefore(checkOut))) {
                            totalPrice += room.getPricePerNight() * numberOfDaysReservedForTheRoom;  //la pretul total adaug pretul camerei per noapte * numarul de zile aferente rezervarii
                        }
                    }
                }
            }
        }
        return totalPrice;
    }
}
