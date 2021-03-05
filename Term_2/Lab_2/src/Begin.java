import javax.swing.*;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class Begin {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JPanel Matrix;
    private JButton button1;
    private JTextField[][] textFields;
    static JFrame frame;


    Begin() {
        reloadMatrix();
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrix();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrix();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern pattern = Pattern.compile("\\d+.*\\d*");
                Box[][] matrix = new Box[textFields.length][textFields[0].length];
                for (int i = 0; i < textFields.length; i ++) {
                    for (int j = 0; j < textFields[i].length; j++) {
                        try {
                            matrix[i][j] = new Box(Double.parseDouble(textFields[i][j].getText()));
                        } catch(Exception er) {
                            JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n" + textFields[i][j].getText(),"Backup problem",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                Matrix a = new Matrix(matrix);
                switch (comboBox1.getSelectedIndex()) {
                    case 0:
                        a = a.maidDiagonal();
                        break;
                    case 1:
                        a = a.sideDiagonal();
                        break;
                    case 2:
                        a = a.lineVertical();
                        break;
                    case 3:
                        a = a.lineHorizontal();
                }
                JOptionPane.showMessageDialog(frame, a,"Result",
                        JOptionPane.INFORMATION_MESSAGE);
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
