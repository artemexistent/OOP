import java.util.Vector;

public class Main {

    public static Vector <Note> notes = new Vector<>(0);
    public static Vector <Note> archive = new Vector<>(0);


    public static Menu app;

    public static void main(String []args){
        app = new Menu();
        app.start();
    }

    public static Object[][] getArr(Vector <Note> a){
        Object [][] ans = new Object[a.size()][3];
        for (int i=0;i<a.size();i++){
            ans[i][0]=a.get(i).note.substring(0,Math.min(10,a.get(i).note.length()-1));
            if (a.get(i).note.length()-1>10){ans[i][0] += "...";};
            ans[i][2]=a.get(i).date;
            ans[i][1]=a.get(i).time;
        }
        return ans;
    }

}
