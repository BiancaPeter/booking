public class Main {
    public static void main(String[] args) {

        //am creat aplicatia booking sau am instantiat un obiect de tip booking
        Booking booking = new Booking();

        //am instantiat un obiect de tip administrator si l-am adaugat in lista de user a booking-ului
        User admin1 = new Admin("Pop","Eugen");
        booking.getUserList().add(admin1);

        //am instantiat un obiect de tip hotel, iar administratorul a adaugat hotelul in lista de hoteluri a booking-ului
        Hotel hotel1 = new Hotel("Continental");
        booking.getUserList().get(0).

//        Client client1 = new Client("Suciu","Daniel");
//        Client client2 = new Client("Precup","Loredana");
//        hotel1.getUserList().add(admin1);
//        hotel1.getUserList().add(client1);
//        hotel1.getUserList().add(client2);
//
//        Room room1 = new Room(1,250,2);
//        Room room2 = new Room(2,150,1);
//        Room room3 = new Room(3,300,3);
//        Room room4 = new Room(4,250,1);
//        hotel1.getUserList().get(0).addRoom(room1);
//        hotel1.getUserList().get(0).addRoom(room2);
//        hotel1.getUserList().get(0).addRoom(room3);
//        hotel1.getUserList().get(0).addRoom(room4);
//
//        hotel1.getUserList().get(0).removeRoom(room4);



    }
}
