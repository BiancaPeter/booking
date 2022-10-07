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
        hotel.getRoomList().add(room);
    }

    public void removeRoom(Room room, Hotel hotel) {
        hotel.getRoomList().remove(room);
    }

    public void printAllRooms(Hotel hotel) {
        System.out.println(hotel.getRoomList());
    }

    public void editPriceOfRoom(Hotel hotel, Room room, int newPrice) {
        //setez noul pret la camera (din lista de camere a hotelului)
        //hotel.getRoomList().indexOf(room) ne da indexul camerei in lista de camere a hotelului
        hotel.getRoomList().get(hotel.getRoomList().indexOf(room)).setPricePerNight(newPrice);
    }

    //parcurg list de hoteluri
    //pt fiecare hotel parcurg lista de camere
    //daca lista de rezervari a unei camere e null inseamna ca acea camera e libera
    //daca lista de rezervari nu e null parcurg lista de rezervari pt fiecare camera
    //verific daca datele de checkIn si checkOut pt perioada de referinta sunt egale de cele din lista de rezervari
    //daca se indeplineste conditia inseamna ca acea camera e rezervata pt perioada de referinta
    //daca nu se indeplineste conditia iar variabila isAvailable ramane true inseamna ca acea camera e libera pt
    //    perioada de referinta si vom incrementa variabila care retine nr de camere disponibile

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
