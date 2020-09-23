package Lab_1;

import java.util.Vector;

public class Series {
    public static Vector <Vector <Book> > book;
    Series(){
        book = new Vector<>(0);
    }

    public void add_series(int id){
        Vector <Book> p = new Vector<>(0);
        for (int i=0;i<BookGUI.people.get(id).book.size();i++){
            p.addElement(BookGUI.people.get(id).book.get(i).first);
        }
        book.addElement(p);
    }

    public String write(int id){
        String ans = "ID: " + (id) + "\n";
        for (int i=0;i<book.get(id-1).size();i++)
            ans += book.get(id-1).get(i).name + ", ";
        return ans;
    }
}
