package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteVertex {
    private JTextField textField1;
    private JButton deleteButton;
    private JPanel panel1;
    public static JFrame frame = new JFrame("Delete");

    public DeleteVertex() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textField1.getText()) - 1;
                if (GraphGUI.fa)
                    GraphCreateGUI.graph.delete_vertex(x);
                else GraphCreateGUI.graphMatrix.delete_vertex(x);
                frame.dispose();
            }
        });
    }
    public void start(){
        frame.setContentPane(new DeleteVertex().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
