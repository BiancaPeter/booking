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
}
