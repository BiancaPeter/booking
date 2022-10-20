import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ClientTest {
    private Client client;
    private Booking booking;

    @BeforeEach
    void createDB() {
        Hotel hotel1 = new Hotel("Continental");
        Hotel hotel2 = new Hotel("Grand");
        Hotel hotel3 = new Hotel("Litoral");
        booking = new Booking();
        client = new Client("Suciu", "Daniel");
        booking.getUserList().add(client);

        booking.getHotelList().add(hotel1);
        booking.getHotelList().add(hotel2);
        booking.getHotelList().add(hotel3);

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

        hotel1.getRoomList().add(room1);
        hotel1.getRoomList().add(room2);
        hotel1.getRoomList().add(room3);
        hotel1.getRoomList().add(room4);
        hotel2.getRoomList().add(room5);
        hotel2.getRoomList().add(room6);
        hotel2.getRoomList().add(room7);
        hotel2.getRoomList().add(room8);
        hotel3.getRoomList().add(room9);
        hotel3.getRoomList().add(room10);
        hotel3.getRoomList().add(room11);
        hotel3.getRoomList().add(room12);

        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Reservation reservation2 = new Reservation(2, LocalDate.of(2022, 8, 28), LocalDate.of(2022, 8, 29));
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation1);
        booking.getHotelList().get(0).getRoomList().get(1).getReservationList().add(reservation2);
    }

    @Test
    void testAvailableRoomsBy_ShouldReturnListWithAvailableRooms() {
        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));

        List<Room> availableRooms = client.getAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testFindAvailableRooms_ShouldReturnListWithAvailableRooms() {
        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));

        List<Room> availableRooms = client.findAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testAvailableRoomsOrderedByPrice_ShouldReturnListWithAvailableRoomsOrderedByPrice() {
        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));

        List<Room> availableRooms = client.getAvailableRoomsOrderedByPriceBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testFindAvailableRoomsOrderedByPrice_ShouldReturnListWithAvailableRoomsOrderedByPrice() {
        List<Room> expectedList = new ArrayList<>();
        expectedList.add(booking.getHotelList().get(1).getRoomList().get(1));
        expectedList.add(booking.getHotelList().get(2).getRoomList().get(3));
        expectedList.add(booking.getHotelList().get(0).getRoomList().get(3));

        List<Room> availableRooms = client.findAvailableRoomsOrderedByPriceBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), 1, booking);
        assertEquals(expectedList, availableRooms);
    }

    @Test
    void testBookARoom_ShouldReturnARoomWithANewReservation() throws RoomNotFoundException {
        Reservation reservation1 = new Reservation(2, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        Room expected = booking.getHotelList().get(0).getRoomList().get(1);
        expected.getReservationList().add(reservation1);

        client.bookARoom(2, booking.getHotelList().get(0), LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
        assertEquals(expected, booking.getHotelList().get(0).getRoomList().get(1));
    }

    @Test
    void testBookARoom_ShouldThrowException() throws RoomNotFoundException {
        try {
            client.bookARoom(100, booking.getHotelList().get(0), LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22));
            fail("Exception not throw");
        } catch (RoomNotFoundException e) {
            assertEquals("The room with numberRoom 100 is not in the hotel Continental", e.getMessage());
        }
    }

}
