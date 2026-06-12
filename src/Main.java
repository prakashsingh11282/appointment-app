import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AppointmentManager manager = new AppointmentManager();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        System.out.println("Appointment Scheduler");
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createAppointment();
                case "2" -> listAppointments();
                case "3" -> searchByDate();
                case "4" -> removeAppointment();
                case "5" -> {
                    System.out.println("Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter 1-5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Create appointment");
        System.out.println("2. List all appointments");
        System.out.println("3. Search appointments by date");
        System.out.println("4. Remove appointment");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void createAppointment() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        LocalDate date = readDate("Date (yyyy-MM-dd): ");
        LocalTime time = readTime("Time (HH:mm): ");
        System.out.print("Location: ");
        String location = scanner.nextLine().trim();

        Appointment appointment = new Appointment(title, date, time, location);
        manager.addAppointment(appointment);
        System.out.println("Appointment created: " + appointment);
    }

    private static void listAppointments() {
        List<Appointment> appointments = manager.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("Appointments:");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, appointments.get(i));
        }
    }

    private static void searchByDate() {
        LocalDate date = readDate("Search date (yyyy-MM-dd): ");
        List<Appointment> found = manager.findByDate(date);
        if (found.isEmpty()) {
            System.out.println("No appointments on " + date.format(DATE_FORMAT));
            return;
        }

        System.out.println("Appointments on " + date.format(DATE_FORMAT) + ":");
        for (Appointment appointment : found) {
            System.out.println(appointment);
        }
    }

    private static void removeAppointment() {
        listAppointments();
        if (manager.getAllAppointments().isEmpty()) {
            return;
        }

        System.out.print("Enter appointment number to remove: ");
        String input = scanner.nextLine().trim();
        try {
            int index = Integer.parseInt(input) - 1;
            if (manager.removeAppointment(index)) {
                System.out.println("Appointment removed.");
            } else {
                System.out.println("Invalid appointment number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use yyyy-MM-dd.");
            }
        }
    }

    private static LocalTime readTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Use HH:mm.");
            }
        }
    }
}
