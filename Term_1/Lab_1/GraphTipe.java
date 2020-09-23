package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphTipe {
    private JButton listButton;
    private JButton matrixButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Add Edge");

    public GraphTipe() {
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphGUI app = new GraphGUI();
                app.Start_GUI(true);
                frame.dispose();
            }
        });
        matrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphGUI app = new GraphGUI();
                app.Start_GUI(false);
                frame.dispose();
            }
        });
    }

    public void start(){
        frame.setContentPane(new GraphTipe().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
