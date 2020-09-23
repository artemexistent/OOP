package Lab_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GraphCreateMatrixGUI{
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JButton createButton;
    public static JFrame frame = new JFrame("Create");
    public static int n,m;
    public static GraphMatrix graph;

    public GraphCreateMatrixGUI() {
        createButton.addActionListener(new ButtonListener());
    }

    public void get(boolean f){
        frame.setContentPane(new GraphCreateMatrixGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(f);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            n = Integer.parseInt(textField1.getText());
            m = Integer.parseInt(textField2.getText());
            graph = new GraphMatrix(n);
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
                    a.set(0,a.get(0)-1);
                    a.set(1,a.get(1)-1);
                    graph.push_edge(a.get(0),a.get(1),a.get(2));
                    a.clear();
                }
                i++;
            }
            frame.dispose();
        }
    }

}
