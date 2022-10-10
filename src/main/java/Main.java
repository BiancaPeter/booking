import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //TODO
        //la pornirea aplicatiei, putem incarca dintr-un fisier camerele dintr-un hotel
        // (sau sa avem mai multe fisiere cu camerele pentru mai multe hotele - un fisier per hotel)
        //cu ce citim, bagam in lista de camere din lista de hoteluri

        //TODO
        //teste pentru metode

        //TODO
        //lambda

        Scanner console = new Scanner(System.in);

        Booking booking = new Booking();

        Admin admin1 = new Admin("Pop", "Eugen");
        booking.getUserList().add(admin1);

        Client client1 = new Client("Suciu", "Daniel");
        booking.getUserList().add(client1);

        Client client2 = new Client("Natea", "Valentina");
        booking.getUserList().add(client2);

        DB db = new DB();
        db.createDataBase(booking);

        Admin admin = (Admin) booking.getUserList().get(0);
        Client client = (Client) booking.getUserList().get(1);

        accessedAdminMenuInLoop(admin, booking.getHotelList().get(0), console);

        accessedClientMenuInLoop(client, booking, booking.getHotelList().get(0), console);

    }

    public static void accessedAdminMenuInLoop(Admin admin, Hotel hotel, Scanner console) {
        int option;
        do {
            printAdminMenu();
            option = console.nextInt();
            performSelectedActionForAdmin(admin, hotel, option, console);
        } while (option != 7);
    }

    public static void accessedClientMenuInLoop(Client client, Booking booking, Hotel hotel, Scanner console) {
        int option;
        do {
            printClientMenu();
            option = console.nextInt();
            performSelectedActionForClient(client, booking, hotel, option, console);
        } while (option != 4);
    }

    public static void performSelectedActionForAdmin(Admin admin, Hotel hotel, int option, Scanner console) {
        switch (option) {
            case 1:
                System.out.println("Add a new hotel room ");
                System.out.println("Enter the room number");
                int roomNumber = console.nextInt();
                System.out.println("Enter the price per night of the room");
                int pricePerNight = console.nextInt();
                System.out.println("Enter the number of people who can stay in the room");
                int numberOfPerson = console.nextInt();
                Room room = new Room(roomNumber, pricePerNight, numberOfPerson);
                admin.addRoom(room, hotel);
                break;
            case 2:
                System.out.println("Enter the number of the room you want to delete ");
                roomNumber = console.nextInt();
                try {
                    admin.removeRoom(roomNumber, hotel);
                } catch (RoomNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                admin.printAllRooms(hotel);
                break;
            case 4:
                System.out.println("Enter the number of the room you want to edit price ");
                roomNumber = console.nextInt();
                System.out.println("Enter the new price for the room ");
                int newPrice = console.nextInt();
                try {
                    admin.editPriceOfRoom(hotel, roomNumber, newPrice);
                } catch (RoomNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                System.out.println("Enter the check-in date");
                String stringCheckIn = console.next();
                LocalDate checkIn = LocalDate.parse(stringCheckIn);
                System.out.println("Enter the check-out date");
                String stringCheckout = console.next();
                LocalDate checkOut = LocalDate.parse(stringCheckout);
                System.out.println(admin.getNumberOfAvailableRooms(checkIn, checkOut, hotel));
                break;
            case 6:
                System.out.println("Enter the check-in date");
                stringCheckIn = console.next();
                checkIn = LocalDate.parse(stringCheckIn);
                System.out.println("Enter the check-out date");
                stringCheckout = console.next();
                checkOut = LocalDate.parse(stringCheckout);
                System.out.println("The price obtained from all reservations for the entered period: " + admin.getPriceObtainedFromAllReservationsFromACertainPeriod(checkIn, checkOut, hotel));
                break;
            case 7:
                System.out.println("You have exited the admin menu");
                break;
            default:
                System.out.println("The entered option is invalid, try again!");
        }
    }

    public static void performSelectedActionForClient(Client client, Booking booking, Hotel hotel, int option, Scanner console) {
        switch (option) {
            case 1:
                System.out.println("Enter the check-in date");
                String stringCheckIn = console.next();
                LocalDate checkIn = LocalDate.parse(stringCheckIn);
                System.out.println("Enter the check-out date");
                String stringCheckout = console.next();
                LocalDate checkOut = LocalDate.parse(stringCheckout);
                System.out.println("Enter the number of people who can stay in the room");
                int numberOfPerson = console.nextInt();
                client.getAvailableRooms(checkIn, checkOut, numberOfPerson, booking);
                break;
            case 2:
                System.out.println("Enter the check-in date");
                stringCheckIn = console.next();
                checkIn = LocalDate.parse(stringCheckIn);
                System.out.println("Enter the check-out date");
                stringCheckout = console.next();
                checkOut = LocalDate.parse(stringCheckout);
                System.out.println("Enter the number of people who can stay in the room");
                numberOfPerson = console.nextInt();
                System.out.println("The sorted list with the available rooms by price for a certain period and a certain number of places is: ");
                List<Room> availableRoomsOrderedByPrice = client.getAvailableRoomsOrderedByPriceBy(checkIn, checkOut, numberOfPerson, booking);
                for (Room room : availableRoomsOrderedByPrice) {
                    System.out.println(room.getHotelName() + ": " + room);
                }
                break;
            case 3:
                System.out.println("Enter the check-in date");
                stringCheckIn = console.next();
                checkIn = LocalDate.parse(stringCheckIn);
                System.out.println("Enter the check-out date");
                stringCheckout = console.next();
                checkOut = LocalDate.parse(stringCheckout);
                System.out.println("Enter the number of the room you want to book ");
                int roomNumber = console.nextInt();
                try {
                    client.bookARoom(roomNumber, hotel, checkIn, checkOut);
                } catch (RoomNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println("You have exited the client menu");
                break;
            default:
                System.out.println("The entered option is invalid, try again!");
        }
    }

    public static void printAdminMenu() {
        System.out.println("ADMIN MENU:");
        System.out.println("1. Add a new hotel room ");
        System.out.println("2. Delete a room from the hotel ");
        System.out.println("3. Show all hotel rooms ");
        System.out.println("4. Edit the price of a hotel room ");
        System.out.println("5. Shows how many rooms are free/occupied for a certain period ");
        System.out.println("6. Shows the price obtained from all the reservations from a certain period ");
        System.out.println("7. Exit the admin menu");
        System.out.println("_________________________________________");
        System.out.println("Choose the action with the number: ");
    }

    public static void printClientMenu() {
        System.out.println("CLIENT MENU:");
        System.out.println("1. Show the rooms that are available in a certain period");
        System.out.println("2. Show the available rooms sorted by price for a certain period and a certain number of places");
        System.out.println("3. Make a reservation for a specific room");
        System.out.println("4. Exit the client menu");
        System.out.println("_________________________________________");
        System.out.println("Choose the action with the number: ");
    }
}
