package Lab_1;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String []args) {
        Scanner in = new Scanner(System.in);
        /*System.out.print("Введите количество вершин: ");
        int n = in.nextInt();
        System.out.print("Введите количество ребер: ");
        int m = in.nextInt();
        System.out.println("Введите все ребра:");
        GraphMatrix graph = new GraphMatrix(n);
        for (int i=0;i<m;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            Pair <Integer, Integer> t = new Pair<>();
            t.make_pair(y-1,z);
            graph.push_edge(x-1,y-1, z);
            graph.push_edge(y-1,x-1, z);
        }
        graph.write();
        System.out.println(graph.connect());
        System.out.println(graph.distance(0,2));
        graph.distance_2(0);
        graph.delete_vertex(2);
        graph.write();
        */
         System.out.print("Введите количество книг: ");
         int n = Integer.parseInt(in.nextLine());
         Vector <Book> books = new Vector<>(0);
         for (int i=0;i<n;i++) {
             System.out.println("Название: ");
             String name = in.nextLine();
             System.out.println("Автор: ");
             String write = in.nextLine();
             System.out.println("Дата выхода: ");
             String data = in.nextLine();
             System.out.println("Количесво страниц: ");
             int kol = Integer.parseInt(in.nextLine());
             System.out.println("Короткая аннотация: ");
             String an = in.nextLine();
             Date t = new Date(data);
             Book p = new Book(name, write,t,kol,an,i);
             books.addElement(p);
         }
        System.out.print("Введите количество персонажей: ");
        int m = Integer.parseInt(in.nextLine());
        Vector <Person> persons = new Vector<>(0);
        for (int i=0;i<m;i++) {
            Person p = new Person();
            System.out.println("Сколько имен: ");
            int kol = Integer.parseInt(in.nextLine());
            System.out.println("Введите все имена: ");
            for (int j=0;j<kol;j++) {
                String name = in.nextLine();
                p.name.addElement(name);
            }
            for (int j=0;j<n;j++){
                books.get(j).write_book();
            }
            System.out.println("\nВ скольких книгах он упоминается: ");
            kol = Integer.parseInt(in.nextLine());
            for (int j=0;j<kol;j++){
                System.out.print("ID книги: ");
                int book = Integer.parseInt(in.nextLine());
                System.out.print("Роль: ");
                String role = in.nextLine();
                Pair <Book, String > t = new Pair<>();
                t.make_pair(books.get(book),role);
                p.book.addElement(t);
            }
            persons.addElement(p);
        }
        Vector <Series> series = new Vector<>(0);
        for (int i=0;i<m;i++){
            Series p = new Series();
            for (int j=0;j<persons.get(i).book.size();j++)
                p.add_book(persons.get(i).book.get(j).first);
            series.addElement(p);
        }

    }
}

