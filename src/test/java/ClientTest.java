import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ClientTest {
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
    void testAvailableRoomsBy_ShouldReturnListWithAvailableRooms() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);

        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        //when
        List<Room> availableRooms = client.getAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        //then
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testFindAvailableRooms_ShouldReturnListWithAvailableRooms() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);

        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        //when
        List<Room> availableRooms = client.findAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        //then
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testAvailableRoomsOrderedByPrice_ShouldReturnListWithAvailableRoomsOrderedByPrice() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);

        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        //when
        List<Room> availableRooms = client.getAvailableRoomsOrderedByPriceBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        //then
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testFindAvailableRoomsOrderedByPrice_ShouldReturnListWithAvailableRoomsOrderedByPrice() {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);

        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        //when
        List<Room> availableRooms = client.findAvailableRoomsOrderedByPriceBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        //then
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testBookARoom_ShouldReturnARoomWithANewReservation() throws RoomNotFoundException {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Room expected = booking.getHotelList().get(0).getRoomList().get(1);
        expected.getReservationList().add(reservation1);
        //when
        client.bookARoom(2, booking.getHotelList().get(0), LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        //then
        assertEquals(expected, booking.getHotelList().get(0).getRoomList().get(1));
    }

    @Test
    void testBookARoom_ShouldThrowException() throws RoomNotFoundException {
        //given
        Booking booking = new Booking();
        createDB(booking);
        Client client = new Client("", "");

        //when
        try {
            client.bookARoom(100, booking.getHotelList().get(0), LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
            fail("Exception not throw");
        } catch (RoomNotFoundException e) {
            assertEquals("The room with numberRoom 100 is not in the hotel Continental", e.getMessage());
        }
    }

}
