import javax.swing.*;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class Begin {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JPanel Matrix;
    private JTextField[][] textFields;
    static JFrame frame;


    Begin() {



        reloadMatrix();
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrix();
            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrix();
            }
        });
    }



    public static void main(String[] args) {
        frame = new JFrame("Matrix");

        Begin begin = new Begin();
        frame.setContentPane(begin.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }

    public void reloadMatrix() {
        int n = comboBox2.getSelectedIndex() + 1;
        int m = comboBox3.getSelectedIndex() + 1;
        System.out.println(n + " " + m);

        textFields = new JTextField[n][m];

        Matrix.removeAll();

        //Matrix.setPreferredSize(new Dimension(15 * n + 10 * (n - 1), 15 * m + 10 * (m - 1)));

        Matrix.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;

        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 10;

        gridBagConstraints.ipady = 15;
        gridBagConstraints.ipadx = 15;


        for (int i = 0; i < n; i ++) {
            gridBagConstraints.gridy = i;
            for (int j = 0; j < m; j++) {
                gridBagConstraints.gridx = j;
                textFields[i][j] = new JTextField("0", 1);
                Matrix.add(textFields[i][j], gridBagConstraints);
            }
        }
    }

}
