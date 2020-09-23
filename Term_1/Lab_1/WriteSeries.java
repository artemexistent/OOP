package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteSeries {
    private JButton writeButton;
    private JPanel panel1;
    private JTextField textField1;
    public static JFrame frame = new JFrame("Write");

    public WriteSeries() {
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                if (id>= Series.book.size()+1 || id == 0) {
                    String massage = "This ID is not exist";
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                }else{
                    String massage = "";
                    massage = BookGUI.series.write(id);
                    JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }
    public void start(){
        frame.setContentPane(new WriteSeries().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
