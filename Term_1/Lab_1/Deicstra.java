package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deicstra {
    private JButton exitButton;
    private JTextField textField1;
    private JButton checkButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Distanse");

    public Deicstra() {
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textField1.getText()) - 1;
                String massage = "";
                if (GraphGUI.fa)
                    massage = GraphCreateGUI.graph.distance_2(x);
                else massage = GraphCreateGUI.graphMatrix.distance_2(x);
                JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void start(){
        frame.setContentPane(new Deicstra().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
