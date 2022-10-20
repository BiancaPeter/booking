import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class AdminTest {
    private Admin admin;
    private Booking booking;

    @BeforeEach
    void createDB() {
        Hotel hotel1 = new Hotel("Continental");
        Hotel hotel2 = new Hotel("Grand");
        Hotel hotel3 = new Hotel("Litoral");
        booking = new Booking();
        admin = new Admin("Pop", "Eugen");
        booking.getUserList().add(admin);

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
    void testAddHotel_ShouldReturnHotelListWithAddedHotel() {
        Booking expectedBooking = booking;
        Hotel newHotel = new Hotel("Imperial");
        expectedBooking.getHotelList().add(newHotel);
        admin.addHotel(newHotel, booking);
        assertEquals(expectedBooking.getHotelList(), booking.getHotelList());
    }

    @Test
    void testAddRoom_ShouldReturnRoomListWithAddedRoom() {
        Hotel expectedHotel = booking.getHotelList().get(0);
        Room newRoom = new Room(50, 450, 3);
        expectedHotel.getRoomList().add(newRoom);
        admin.addRoom(newRoom, booking.getHotelList().get(0));
        assertEquals(expectedHotel.getRoomList(), booking.getHotelList().get(0).getRoomList());
    }

    //prin debug am constatat ca atunci cand stergem o camera din expectedHotel se sterge aceasi camera
    //si din hotelul aflat in booking... iar cand stergem o camera din hotelul aflat in lista de hoteluri a
    //booking-ului stergerea se face si in expectedHotel
    //CIUDAT - NU INTELEG DE CE!!!

//    @Test
//    void testRemoveRoom_ShouldReturnRoomListWithoutRoomToRemove() throws RoomNotFoundException {
//        Hotel expectedHotel = booking.getHotelList().get(0);
//        Room deletedRoom = booking.getHotelList().get(0).getRoomList().get(0);
//        expectedHotel.getRoomList().remove(deletedRoom);
//        admin.removeRoom(1, booking.getHotelList().get(0));
//        assertEquals(expectedHotel.getRoomList(), booking.getHotelList().get(0).getRoomList());
//    }

    @Test
    void testRemoveARoom_ShouldThrowException() throws RoomNotFoundException {
        try {
            admin.removeRoom(100, booking.getHotelList().get(0));
            fail("Exception not throw");
        } catch (RoomNotFoundException e) {
            assertEquals("The room with numberRoom 100 is not in the hotel Continental", e.getMessage());
        }
    }

    @Test
    void testAvailableRooms_ShouldReturnNumberOfAvailableRooms() {
        int numberOfAvailableRooms = admin.getNumberOfAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        assertEquals(3, numberOfAvailableRooms);
    }

    @Test
    void testFindAvailableRooms_ShouldReturnNumberOfAvailableRooms() {
        long numberOfAvailableRooms = admin.findNumberOfAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        assertEquals(3, numberOfAvailableRooms);
    }

    @Test
    void testPriceForAllReservationsBy_ShouldReturnSumForAllReservationsBy() {
        long sumForAllReservations = admin.getPriceForAllReservationsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        assertEquals(300, sumForAllReservations);
    }

    @Test
    void testFindPriceForAllReservationsBy_ShouldReturnSumForAllReservationsBy() {
        long sumForAllReservations = admin.findPriceForAllReservationsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), booking.getHotelList().get(0));
        assertEquals(300, sumForAllReservations);
    }

}
