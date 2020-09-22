package Lab_1;

public class Date {
    public int month;
    public int year;
    public int day;

    Date (String s){
        int i=0;
        while (s.charAt(i) != '.'){
            day = (day * 10) + (s.charAt(i) - '0');
            i++;
        }
        i++;
        while (s.charAt(i) != '.'){
            month = (month * 10) + (s.charAt(i) - '0');
            i++;
        }
        i++;
        while (s.length()>i && s.charAt(i) != '.'){
            year = year * 10 + (s.charAt(i)-'0');
            i++;
        }
    }

    public Boolean compare(Date x){
        if (year > x.year)
            return true;
        if (year != x.year)
            return false;
        if (month > x.month)
            return true;
        if (month != x.month)
            return false;
        return day > x.day;
    }

}
