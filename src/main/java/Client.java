import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    public Client(String lastName, String firstName) {
        super(lastName, firstName);
    }

    //am parcurs lista de hoteluri din aplicatie
    //pentru fiecare hotel am parcurs lista de camere
    //daca camera are lista de rezervari nula atunci camera este libera
    //daca lista de rezervari a camerei nu este nula atunci parcurgem fiecare rezervare si verificam daca nu exista o rezervare
    //    cu aceleasi date de checkIn si checkOut
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

    public List<Room> getSortedListOfAvailableRoomsByPriceForACertainPeriodAndACertainNumberOfPlaces(LocalDate checkIn, LocalDate checkOut, int numberOfPerson, Booking booking) {
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

    public void bookARoom(Room room, Hotel hotel, LocalDate checkIn, LocalDate checkOut, Client client) {
//clientul (care e un user) are o lista de rezervari iar lista de rezervari are ca atribut un client
//fapt ce il baga in ciclu infinit
//asa ca nu ar trebui sa adaugam rezervarea in lista de rezervari a clientului (nu ar trebui sa avem o lista de rezervari pt client?)...
//TODO: cum putem rezolva problema de mai sus

//      reservationList.add(new Reservation(room.getRoomNumber(), client, checkIn, checkOut));
        hotel.getRoomList().get(hotel.getRoomList().indexOf(room)).getReservationList().add(new Reservation(room.getRoomNumber(), client, checkIn, checkOut));
    }
}
