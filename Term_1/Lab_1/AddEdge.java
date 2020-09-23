package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEdge {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addButton;
    public static JFrame frame = new JFrame("Add Edge");

    public AddEdge() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textField1.getText()) - 1;
                int y = Integer.parseInt(textField2.getText()) - 1;
                int z =Integer.parseInt(textField3.getText()) - 1;
                Pair<Integer,Integer > t = new Pair<Integer, Integer>();
                t.make_pair(y,z);
                if (GraphGUI.fa)
                    GraphCreateGUI.graph.push_edge(x,t);
                else GraphCreateGUI.graphMatrix.push_edge(x,y,z);
                frame.dispose();
            }
        });
    }

    public void start(){
        frame.setContentPane(new AddEdge().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
