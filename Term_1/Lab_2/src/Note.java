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

    public String write(){
        String ans = "<html>";
        ans += note + "<br/>";
        ans += time.toString() + "<br/>";
        ans += date.toString() + "</html>";
        return ans;
    }

}
