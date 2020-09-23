package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AddPerson {
    private JTextField textField1;
    private JButton addInBookButton;
    private JButton addInThisPersonButton;
    private JButton exitButton;
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField3;
    public static JFrame frame = new JFrame("Add");
    public static Person person;

    public AddPerson() {
        addInThisPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                int id = Integer.parseInt(textField2.getText());
                if (id>= BookGUI.book.size()+1 || id == 0) {
                    String massage = "This ID is not exist";
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                }else {
                    String role = textField3.getText();
                    Pair<Book, String> t = new Pair<Book, String>();
                    t.make_pair(BookGUI.book.get(id - 1), role);
                    person.name.addElement(name);
                    person.book.addElement(t);
                    String massage = "Added";
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookGUI.people.addElement(person);
                BookGUI.series.add_series(BookGUI.people.size()-1);
                frame.dispose();
            }
        });
    }
    public void start(){
        person =  new Person();
        frame.setContentPane(new AddPerson().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
