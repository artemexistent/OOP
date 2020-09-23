package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEdge {
    private JTextField textField1;
    private JTextField textField2;
    private JButton deleteButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Distanse");
    public DeleteEdge() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textField1.getText()) - 1;
                int y = Integer.parseInt(textField2.getText()) - 1;
                if (GraphGUI.fa) {
                    GraphCreateGUI.graph.delete_edge(x, y);
                    GraphCreateGUI.graph.delete_edge(y, x);
                }else {
                    GraphCreateGUI.graphMatrix.delete_edge(x,y);
                    GraphCreateGUI.graphMatrix.delete_edge(y,x);
                }
                frame.dispose();
            }
        });
    }
    public void start(){
        frame.setContentPane(new DeleteEdge().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
