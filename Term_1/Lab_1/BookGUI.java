package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BookGUI {
    private JButton addBookButton;
    private JPanel panel1;
    private JButton addPersonButton;
    private JButton writeSeriesButton;
    private JButton writeInfoBookButton;
    private JButton writeInfoPersonButton;
    public static Vector<Book> book;
    public static Vector <Person> people;
    public static Series series;

    public BookGUI() {
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook app = new AddBook();
                app.start();
            }
        });
        writeInfoBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteBook app = new WriteBook();
                app.start();
            }
        });
        addPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPerson app = new AddPerson();
                app.start();
            }
        });
        writeInfoPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WritePerson app = new WritePerson();
                app.start();
            }
        });
        writeSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteSeries app = new WriteSeries();
                app.start();
            }
        });
    }

    public void start(){
        series = new Series();
        book = new Vector<>();
        people =  new Vector<>();
        JFrame frame = new JFrame("Book");
        frame.setContentPane(new BookGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
