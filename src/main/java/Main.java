import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Booking booking = new Booking();

        Admin admin1 = new Admin("Pop", "Eugen");
        booking.getUserList().add(admin1);

        Client client1 = new Client("Suciu", "Daniel");
        booking.getUserList().add(client1);

        Client client2 = new Client("Natea", "Valentina");
        booking.getUserList().add(client2);

        //am instantiat obiecte de tip hotel, iar administratorul le-a adaugat in lista de hoteluri a booking-ului
        //TODO: ar trebui sa avem un anumit administrator de booking care sa introduca fiecare hotel??? sau un adm din lista userList sa adauge fiecare hotel
        //TODO: lista userList este atribut al clasei booking, e ok?? sau ar trebui sa fie atribut in clasa hotel?


        Hotel hotel1 = new Hotel("Continental");
        Hotel hotel2 = new Hotel("Grand");
        Hotel hotel3 = new Hotel("Litoral");
        admin1.addHotel(hotel1, booking);
        admin1.addHotel(hotel2, booking);
        admin1.addHotel(hotel3, booking);

        //TODO: avand o lista de user in booking cum fac sa apelez metodele de la admin si client pe lista de user din booking??
//      as putea sa fac asa:
//      Admin admin = (Admin) booking.getUserList().get(0);
//      sau sa apelez metodele pe obiectul admin1 (neintrodus in lista de user)

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
        admin1.addRoom(room1, hotel1);
        admin1.addRoom(room2, hotel1);
        admin1.addRoom(room3, hotel1);
        admin1.addRoom(room4, hotel1);
        admin1.addRoom(room5, hotel2);
        admin1.addRoom(room6, hotel2);
        admin1.addRoom(room7, hotel2);
        admin1.addRoom(room8, hotel2);
        admin1.addRoom(room9, hotel3);
        admin1.addRoom(room10, hotel3);
        admin1.addRoom(room11, hotel3);
        admin1.addRoom(room12, hotel3);

//        admin1.printAllRooms(hotel1);
//        System.out.println();
//
//        admin1.removeRoom(room4, hotel1);
//        admin1.printAllRooms(hotel1);
//        System.out.println();
//
//        admin1.editPriceOfRoom(hotel1, room3, 200);
//        admin1.printAllRooms(hotel1);


//        client1.getAvailableRooms(LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 9), 2, booking);

//        System.out.println(admin1.getNumberOfAvailableRooms(LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 9), hotel1));


//        System.out.println("The price obtained from all reservations from a certain period " + admin1.getPriceObtainedFromAllReservationsFromACertainPeriod(LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 9), hotel1));
//
//        System.out.println("The sorted list with the available rooms by price for a certain period and a certain number of places is: " + client1.getSortedListOfAvailableRoomsByPriceForACertainPeriodAndACertainNumberOfPlaces(LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 9), 2, booking));

//        System.out.println("By booking");
//        client1.bookARoom(room1, hotel1, LocalDate.of(2022, 10, 5), LocalDate.of(2022, 10, 9), client1);
//        admin1.printAllRooms(hotel1);

    }
}
