package Lab_1;

import java.util.Vector;

public class Person{
    public Vector<String> name;
    public Vector< Pair<Book,String> > book;

    Person(){
        name = new Vector<>(0);
        book = new Vector<>(0);
    }

    public String write(){
        String ans = "";
        for (int i=0;i<name.size();i++){
            ans += "Name Book: " + book.get(i).first.name + "\nName: " + name.get(i) + "\nRole" + book.get(i).second + "\n\n\n";
        }
        return ans;
    }
}
