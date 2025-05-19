import java.util.*;

class Flight {
String flightNumber, source, destination, date;
int seatsAvailable;
double price;

Flight(String flightNumber, String source, String destination, String date, int seatsAvailable, double price) {
this.flightNumber = flightNumber;
this.source = source;
this.destination = destination;
this.date = date;
this.seatsAvailable = seatsAvailable;
this.price = price;
}

void displayFlight() {
System.out.println("Flight: " + flightNumber + " | " + source + " to " + destination +
" | Date: " + date + " | Seats: " + seatsAvailable + " | Price: " + price);
}
}

class Booking {
String pnr;
String passengerName;
Flight flight;

Booking(String pnr, String passengerName, Flight flight) {
this.pnr = pnr;
this.passengerName = passengerName;
this.flight = flight;
}

void displayTicket() {
System.out.println("\n--- TICKET ---");
System.out.println("PNR: " + pnr);
System.out.println("Name: " + passengerName);
System.out.println("Flight: " + flight.flightNumber);
System.out.println("Route: " + flight.source + " to " + flight.destination);
System.out.println("Date: " + flight.date);
System.out.println("Price: " + flight.price);
System.out.println("Seats Remaining: " + flight.seatsAvailable); // Seat count shown
}
}

public class Main {
static List<Flight> flightList = new ArrayList<>();
static List<Booking> bookingList = new ArrayList<>();
static Scanner sc = new Scanner(System.in);

public static void main(String[] args) {
System.out.println("Welcome to the Airline Reservation System");

flightList.add(new Flight("AI101", "Delhi", "Chennai", "2025-05-15", 10, 4500));
flightList.add(new Flight("AI202", "Bangalore", "Malaysia", "2025-05-16", 5, 5200));
flightList.add(new Flight("AI103", "Dubai", "Oman", "2025-05-18", 3, 7800));
flightList.add(new Flight("AI204", "Saudi Arabia", "Qatar", "2025-05-19", 7, 8000));

while (true) {
System.out.println("\nLogin as:\n1. Admin\n2. Customer\n3. Exit");
System.out.print("Enter your choice: ");
int role = sc.nextInt();
sc.nextLine();

if (role == 1) {
adminPanel();
} else if (role == 2) {
customerPanel();
} else if (role == 3) {
System.out.println("Visit our Airlines Again. Thank you!");
break;
} else {
System.out.println("Invalid option. Try again.");
}
}
}

static void adminPanel() {
System.out.println("\n--- Admin Panel ---");
System.out.println("1. View Flights");
System.out.println("2. Add New Flight");
System.out.print("Enter choice: ");
int choice = sc.nextInt();
sc.nextLine();

if (choice == 1) {
viewFlights();
} else if (choice == 2) {
addFlight();
} else {
System.out.println("Invalid admin option.");
}
}

static void customerPanel() {
System.out.println("\n--- Customer Panel ---");
System.out.println("1. View Flights");
System.out.println("2. Book Flight");
System.out.println("3. Cancel Booking");
System.out.print("Enter choice: ");
int choice = sc.nextInt();
sc.nextLine();

if (choice == 1) {
viewFlights();
} else if (choice == 2) {
bookFlight();
} else if (choice == 3) {
cancelBooking();
} else {
System.out.println("Invalid customer option.");
}
}

static void viewFlights() {
if (flightList.isEmpty()) {
System.out.println("No flights available.");
} else {
System.out.println("\nAvailable Flights:");
for (int i = 0; i < flightList.size(); i++) {
System.out.print((i + 1) + ". ");
flightList.get(i).displayFlight();
}
}
}
static void addFlight() {
System.out.print("Enter Flight Number: ");
String number = sc.nextLine();
System.out.print("Enter Source: ");
String source = sc.nextLine();
System.out.print("Enter Destination: ");
String dest = sc.nextLine();
System.out.print("Enter Date (YYYY-MM-DD): ");
String date = sc.nextLine();
System.out.print("Enter Seats Available: ");
int seats = sc.nextInt();
System.out.print("Enter Price: ");
double price = sc.nextDouble();
sc.nextLine();

flightList.add(new Flight(number, source, dest, date, seats, price));
System.out.println("Flight added successfully!");
}

static void bookFlight() {
viewFlights();

if (flightList.isEmpty())
return;

System.out.print("\nEnter flight number to book (1 to " + flightList.size() + "): ");
int index = sc.nextInt() - 1;
sc.nextLine();

if (index >= 0 && index < flightList.size()) {
Flight flight = flightList.get(index);
if (flight.seatsAvailable <= 0) {
System.out.println("Sorry, no seats available.");
return;
}

System.out.print("Enter your name: ");
String name = sc.nextLine();

String pnr = "PNR" + (int) (Math.random() * 100000);
Booking booking = new Booking(pnr, name, flight);
bookingList.add(booking);
flight.seatsAvailable--;

booking.displayTicket();
} else {
System.out.println("Invalid flight selection.");
}
}

static void cancelBooking() {
System.out.print("Enter your PNR to cancel: ");
String pnrToCancel = sc.nextLine();
boolean found = false;

for (int i = 0; i < bookingList.size(); i++) {
Booking booking = bookingList.get(i);
if (booking.pnr.equalsIgnoreCase(pnrToCancel)) {
booking.flight.seatsAvailable++;
bookingList.remove(i);
System.out.println("Booking with PNR " + pnrToCancel + " has been cancelled.");
found = true;
break;
}
}

if (!found) {
System.out.println("No booking found with PNR: " + pnrToCancel);
}
}
}
