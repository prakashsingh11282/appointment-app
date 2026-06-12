import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private final String title;
    private final LocalDate date;
    private final LocalTime time;
    private final String location;

    public Appointment(String title, LocalDate date, LocalTime time, String location) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s | %s %s | %s", title, date.format(dateFormatter), time.format(timeFormatter), location);
    }
}
