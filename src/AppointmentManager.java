import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentManager {
    private final List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public boolean removeAppointment(int index) {
        if (index >= 0 && index < appointments.size()) {
            appointments.remove(index);
            return true;
        }
        return false;
    }
}
