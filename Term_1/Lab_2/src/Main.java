import java.util.Vector;

public class Main {

    public static Vector <Note> notes = new Vector<>(0);
    public static Vector <Note> archive = new Vector<>(0);


    public static Menu app;

    public static void main(String []args){
        app = new Menu();
        app.start();
    }

    public static Object[][] getArr(Vector <Note> a, String s){
        int kol = 0;
        for (Note i : a)
            if (i.type.equals(s))
                kol++;
        Object [][] ans = new Object[kol][3];
        kol = 0;
        for (int i=0;i<a.size();i++){
            if (a.get(i).type.equals(s)) {
                ans[kol][0] = a.get(i).note.substring(0, Math.min(10, a.get(i).note.length() - 1));
                if (a.get(i).note.length() - 1 > 10) {
                    ans[kol][0] += "...";
                }
                ans[kol][2] = a.get(i).date;
                ans[kol][1] = a.get(i).time;
                kol++;
            }
        }
        return ans;
    }

    public static void sort(Vector <Note> a){
        Note p = a.get(a.size()-1);
        int kol = -1;
        for (Note i : a){
            kol++;
            if (i.date.getDayOfYear() < p.date.getDayOfYear()){
                continue;
            }
            if (p.date.getDayOfYear() == i.date.getDayOfYear()){
                if (p.time.getNano() <= i.time.getNano())
                    break;
            }else break;
        }
        for (int j=a.size()-1;j>kol;j--)
            a.set(j,a.get(j-1));
        a.set(kol,p);
    }

}
