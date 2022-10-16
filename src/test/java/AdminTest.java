import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class AdminTest {

    //    @BeforeAll
    static void createDB(Booking booking) {
        Hotel hotel1 = new Hotel("Continental");
        Hotel hotel2 = new Hotel("Grand");
        Hotel hotel3 = new Hotel("Litoral");

        Admin admin1 = new Admin("Pop", "Eugen");
        booking.getUserList().add(admin1);

        Client client1 = new Client("Suciu", "Daniel");
        booking.getUserList().add(client1);

        Client client2 = new Client("Natea", "Valentina");
        booking.getUserList().add(client2);

        Admin admin = (Admin) booking.getUserList().get(0);
        admin.addHotel(hotel1, booking);
        admin.addHotel(hotel2, booking);
        admin.addHotel(hotel3, booking);

        Room room1 = new Room(1, 250, 2);
        Room room2 = new Room(2, 150, 1);
        Room room3 = new Room(3, 300, 3);
        Room room4 = new Room(4, 250, 1);
        Room room5 = new Room(1, 450, 3);
        Room room6 = new Room(2, 150, 1);
        Room room7 = new Room(3, 250, 3);
        Room room8 = new Room(4, 350, 2);
        Room room9 = new Room(1, 250, 2);
        Room room10 = new Room(2, 350, 2);
        Room room11 = new Room(3, 370, 3);
        Room room12 = new Room(4, 230, 1);
        admin.addRoom(room1, hotel1);
        admin.addRoom(room2, hotel1);
        admin.addRoom(room3, hotel1);
        admin.addRoom(room4, hotel1);
        admin.addRoom(room5, hotel2);
        admin.addRoom(room6, hotel2);
        admin.addRoom(room7, hotel2);
        admin.addRoom(room8, hotel2);
        admin.addRoom(room9, hotel3);
        admin.addRoom(room10, hotel3);
        admin.addRoom(room11, hotel3);
        admin.addRoom(room12, hotel3);
    }

    @Test
    void testAvailableRooms_ShouldReturnNumberOfAvailableRooms() {
        //given
//TODO: daca ma folosesc de adnotarea @BeforeAll, mut new Booking la inceput si elimin apelul de metoda createDB,
// se genereaza o eroare:"Index 0 out of bounds for length 0"...
        Booking booking = new Booking();
        createDB(booking);
        Admin admin = new Admin("", "");
//TODO: @Mock' not applicable to local variable --->
// asa ca daca adnotez obiectul admin cu mock in afara metodei de test, codul trece de compilare dar genereaza eroare....
        //when
        int numberOfAvailableRooms = admin.getNumberOfAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        //then
        assertEquals(4, numberOfAvailableRooms);
    }

    @Test
    void testFindAvailableRooms_ShouldReturnNumberOfAvailableRooms() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Admin admin = new Admin("", "");
        //when
        long numberOfAvailableRooms = admin.findNumberOfAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        //then
        assertEquals(4, numberOfAvailableRooms);
    }

    @Test
    void testPriceForAllReservationsBy_ShouldReturnSumForAllReservationsBy() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Admin admin = new Admin("", "");
        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);
        //when
        long sumForAllReservations = admin.getPriceForAllReservationsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        //then
        assertEquals(300, sumForAllReservations);
    }

    @Test
    void testFindPriceForAllReservationsBy_ShouldReturnSumForAllReservationsBy() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Admin admin = new Admin("", "");
        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);
        //when
        long sumForAllReservations = admin.findPriceForAllReservationsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        //then
        assertEquals(300, sumForAllReservations);
    }

}
