package Lab_1;

import java.util.Vector;

public class Series {
    public Vector <Book> book;
    Series(){
        book = new Vector<>(0);
    }
    public void add_book(Book x){
        book.addElement(x);
        int j;
        for (j=book.size()-2;j>=0;j--){
            if (book.get(j).release.compare(x.release)){
                j++;
                break;
            }
        }
        if (j>=0){
            book.set(book.size()-1,book.get(j));
            book.set(j,x);
        }

    }
}
