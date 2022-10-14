import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminTest {
    DB db = new DB();

    @Mock
    Booking booking;
    @Mock
    Admin admin;
    @Mock
    Hotel hotel;

//    @Test
//    void testAvailableRooms_ShouldReturnNumberOfAvailableRooms() {
//        when(admin.getNumberOfAvailableRoomsBy(LocalDate.of(2022, 8, 20), LocalDate.of(2022, 8, 22), hotel).thenReturn();
//    }
}
