package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Distanse {
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField1;
    private JButton findButton;
    private JButton exitButton;
    public static JFrame frame = new JFrame("Distanse");


    public Distanse() {
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textField1.getText()) - 1;
                int y = Integer.parseInt(textField2.getText()) - 1;
                int ans;
                if (GraphGUI.fa)
                    ans = GraphCreateGUI.graph.distance(x,y);
                else ans = GraphCreateGUI.graphMatrix.distance(x,y);
                String massage = "Distanse (" + x + ", " + y +") = " + ans ;
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
        frame.setContentPane(new Distanse().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
