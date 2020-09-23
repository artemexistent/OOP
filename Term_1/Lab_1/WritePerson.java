package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WritePerson {
    private JTextField textField1;
    private JButton writeButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Write");

    public WritePerson() {
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if (id>= BookGUI.book.size()+1 || id == 0) {
                    String massage = "This ID is not exist";
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                }else{
                    String massage = "";
                    massage = BookGUI.people.get(id-1).write();
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }
    public void start(){
        frame.setContentPane(new WritePerson().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
