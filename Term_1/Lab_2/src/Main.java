import java.util.Vector;

public class Main {

    public static Vector <Note> notes = new Vector<>(0);
    public static Vector <Note> archive = new Vector<>(0);
    public static Object[][] na;

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

    public static Vector<Note> find(Vector <Note> a,String s){
        Vector<Note> ans = new Vector<>(0);
        for (Note i: a){
            if (i.note.contains(s))
                ans.addElement(i);
        }
        return  ans;
    }

    public static Object[][] getArr(Vector <Note> a, Vector <Note> b, String s) {
        a = find(notes,s);
        b = find(archive,s);
        Object[][] ans = new Object[a.size()+b.size()][5];
        for (int i=0;i<a.size();i++){
            ans[i][0] = a.get(i).note.substring(0, Math.min(10, a.get(i).note.length() - 1));
            if (a.get(i).note.length() - 1 > 10) {
                ans[i][0] += "...";
            }
            ans[i][2] = a.get(i).date;
            ans[i][1] = a.get(i).time;
            ans[i][3] = a.get(i).type;
            ans[i][4] = "base";
        }
        for (int i=0;i<b.size();i++){
            ans[i+a.size()][0] = b.get(i).note.substring(0, Math.min(10, b.get(i).note.length() - 1));
            if (b.get(i).note.length() - 1 > 10) {
                ans[i+a.size()][0] += "...";
            }
            ans[i+a.size()][2] = b.get(i).date;
            ans[i+a.size()][1] = b.get(i).time;
            ans[i+a.size()][3] = b.get(i).type;
            ans[i+a.size()][4] = "archive";
        }
        na = ans;
        return ans;
    }


}
