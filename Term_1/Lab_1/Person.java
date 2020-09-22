package Lab_1;

import java.util.Vector;

public class Person{
    public Vector<String> name;
    public Vector< Pair<Book,String> > book;

    Person(){
        name = new Vector<>(0);
        book = new Vector<>(0);
    }

}
