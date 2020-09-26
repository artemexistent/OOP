package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab_1 {
    private static JFrame frame = new JFrame("Lab1");
    private JButton graph17Button;
    private JPanel panel1;
    private JButton book210Button;



    public Lab_1() {
        graph17Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphTipe app = new GraphTipe();
                frame.dispose();
                app.start();
            }
        });
        book210Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookGUI app = new BookGUI();
                app.start();
                frame.dispose();
            }
        });
    }

    public static void main(String []args){
        frame.setContentPane(new Lab_1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
