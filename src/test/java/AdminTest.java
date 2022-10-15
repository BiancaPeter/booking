import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminTest {
    @BeforeAll
    public void createDB() {
        Booking booking = new Booking();

        Hotel hotel1 = new Hotel("Continental");
        Hotel hotel2 = new Hotel("Grand");
        Hotel hotel3 = new Hotel("Litoral");

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

        //when
        int numberOfAvailableRooms = admin.getNumberOfAvailableRoomsBy();
        //then
        assertEquals(4, numberOfAvailableRooms);
    }


}
