import java.util.Vector;

public class Main {

    public static Vector <Note> notes = new Vector<>(0);

    public static void main(String []args){
        Menu app = new Menu();
        app.start();
    }

    public static Object[][] getNotesArr(){
        Object [][] ans = new Object[notes.size()][3];
        for (int i=0;i<notes.size();i++){
            ans[i][0]=notes.get(i).note.substring(0,10) + "...";
            ans[i][2]=notes.get(i).date;
            ans[i][1]=notes.get(i).time;
        }
        return ans;
    }
}
