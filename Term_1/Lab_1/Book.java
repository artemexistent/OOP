package Lab_1;

public class Book {
    public String name;
    public String writer;
    public Date release;
    public int sides;
    public String summary;
    public int series;
    public int ID;

    Book(String s, String s1, Date data, int kol, String s2,int i){
        name = s;
        writer = s1;
        release = data;
        sides = kol;
        summary = s2;
        series = -1;
        ID = i;
    }

    public void write_book(){
        System.out.print("\nID: " + ID + "\tНазвание: " + name + "\tАвтор: " + writer);
    }

}
