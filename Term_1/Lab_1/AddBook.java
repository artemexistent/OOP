package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AddBook {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton addButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Add");
    public AddBook() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                String write = textField2.getText();
                String data = textField3.getText();
                int page = Integer.parseInt(textField4.getText());
                String summary = textField5.getText();
                Book p = new Book(name,write,new Date(data),page,summary,BookGUI.book.size()+1);
                BookGUI.book.addElement(p);
                frame.dispose();
            }
        });
    }

    public void start(){
        frame.setContentPane(new AddBook().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
