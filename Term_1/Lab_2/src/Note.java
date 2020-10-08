import java.time.LocalDate;
import java.time.LocalTime;

public class Note {
    String note;
    LocalDate date;
    LocalTime time;
    String type;

    Note(String s, LocalDate d, LocalTime t, String s2){
        note = s;
        date = d;
        time = t;
        type = s2;
    }

    public String write(){
        String ans = "<html>";
        ans += note + "<br/>";
        ans += time.toString() + "<br/>";
        ans += date.toString() + "</html>";
        return ans;
    }



}
