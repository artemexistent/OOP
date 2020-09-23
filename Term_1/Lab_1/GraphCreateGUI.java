package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GraphCreateGUI{
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JButton createButton;
    public static JFrame frame = new JFrame("Create");
    public static int n,m;
    public static Graph graph;
    public static GraphMatrix graphMatrix;
    public static Boolean fa;

    public GraphCreateGUI() {
        createButton.addActionListener(new ButtonListener());
    }

    public void get(boolean f){
        frame.setContentPane(new GraphCreateGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        fa = f;
        frame.setVisible(true);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            n = Integer.parseInt(textField1.getText());
            m = Integer.parseInt(textField2.getText());
            if (fa)
                graph = new Graph(n);
            else graphMatrix = new GraphMatrix(n);
            String s = textField3.getText();
            int i=0;
            Vector <Integer> a = new Vector<>(0);
            while (s.length()>i){
                int num=0;
                while (s.length()>i && s.charAt(i)>='0' && s.charAt(i)<='9'){
                    num = num * 10 + s.charAt(i)-'0';
                    i++;
                }
                if (num!=0)
                    a.addElement(num);
                if (a.size()==3){
                    Pair <Integer, Integer> t = new Pair<Integer, Integer>();
                    a.set(0,a.get(0)-1);
                    a.set(1,a.get(1)-1);
                    t.make_pair(a.get(1), a.get(2));
                    if (fa)
                        graph.push_edge(a.get(0),t);
                    else graphMatrix.push_edge(a.get(0),a.get(1),a.get(2));
                    a.clear();
                }
                i++;
            }
            frame.dispose();
        }
    }

}
