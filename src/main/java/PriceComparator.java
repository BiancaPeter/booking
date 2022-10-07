import java.util.Comparator;

public class PriceComparator implements Comparator<Room> {
    @Override
    public int compare(Room room1, Room room2) {
        return Integer.compare(room1.getPricePerNight(),room2.getPricePerNight());
    }



}
