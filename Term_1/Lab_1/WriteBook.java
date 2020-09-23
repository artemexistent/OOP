package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class WriteBook {
    private JTextField textField1;
    private JButton writeButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Write");

    public WriteBook() {
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if (id >= BookGUI.book.size()+1 || id ==0){
                    String massage = "This ID is not exist";
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                }else {
                    String massage = "";
                    massage = BookGUI.book.get(id-1).write_book();
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }

    public void start(){
        frame.setContentPane(new WriteBook().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
