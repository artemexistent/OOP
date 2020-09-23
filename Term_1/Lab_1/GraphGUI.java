package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphGUI {
    private JButton createGraphButton;
    private JPanel panel1;
    private JButton writeGraphButton;
    private JButton distanseButton;
    private JButton connectButton;
    private JButton deicstraButton;
    private JButton addVertexButton;
    private JButton addEdgeButton;
    private JButton deleteVertexButton;
    private JButton deleteEdgeButton;
    public static Boolean fa;
    public GraphGUI() {
        createGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphCreateGUI app = new GraphCreateGUI();
                app.get(fa);
            }
        });
        writeGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String massage = "";
                if (fa)
                    massage = GraphCreateGUI.graph.write();
                else massage = GraphCreateGUI.graphMatrix.write();
                JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
        distanseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Distanse app = new Distanse();
                app.start();
            }
        });
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String massage = "Kol Connected = ";
                if (fa)
                    massage += GraphCreateGUI.graph.connect();
                else massage += GraphCreateGUI.graphMatrix.connect();
                JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
        deicstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deicstra app = new Deicstra();
                app.start();
            }
        });
        addVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fa)
                    GraphCreateGUI.graph.push_vertex();
                else GraphCreateGUI.graphMatrix.push_vertex();
            }
        });
        addEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEdge app = new AddEdge();
                app.start();
            }
        });
        deleteVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteVertex app = new DeleteVertex();
                app.start();
            }
        });
        deleteEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteEdge app = new DeleteEdge();
                app.start();
            }
        });
    }

    public void Start_GUI(Boolean f){
        JFrame frame = new JFrame("Graph");
        frame.setContentPane(new GraphGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        fa = f;
    }
}
