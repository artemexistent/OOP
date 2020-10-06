import java.time.LocalDate;
import java.time.LocalTime;

public class Note {
    String note;
    LocalDate date;
    LocalTime time;

    Note(String s, LocalDate d, LocalTime t){
        note = s;
        date = d;
        time = t;
    }

}
