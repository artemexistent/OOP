import javax.swing.*;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.regex.Pattern;

import static javax.swing.GroupLayout.Alignment.LEADING;

/**
 * GUI
 */

public class Begin {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JPanel Matrix;
    private JButton button1;
    private JComboBox comboBox4;
    private JButton button2;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JPanel Matrix1;
    private JPanel Matrix2;
    private JLabel operation;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JButton resultButton;
    private JPanel System;
    private JTextField[][] textFields;
    private JTextField[][] textFields1;
    private JTextField[][] textFields2;
    private JTextField[][] system;
    static JFrame frame;

    /**
     * constructor + listener
     */
    Begin() {
        reloadMatrix();
        reloadMatrixPage2();
        reloadMatrixPage2_1();
        reloadSystem();

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
        comboBox5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrixPage2();
            }
        });
        comboBox6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrixPage2();
            }
        });
        comboBox7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrixPage2_1();
            }
        });
        comboBox8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMatrixPage2_1();
            }
        });
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox4.getSelectedIndex() == 0) {
                    operation.setText("+");
                }
                if (comboBox4.getSelectedIndex() == 1) {
                    operation.setText("*");
                }
            }
        });
        comboBox9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadSystem();
            }
        });
        comboBox10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadSystem();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Box[][] matrix = new Box[textFields.length][textFields[0].length];
                if (!checkAndGetDates(matrix)) {
                    return;
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
                        break;
                    case 4:
                        a = new Matrix(a.determinate());
                }
                JOptionPane.showMessageDialog(frame, a,"Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Box[][] matrix1 = new Box[textFields1.length][textFields1[0].length];
                Box[][] matrix2 = new Box[textFields2.length][textFields2[0].length];
                if (!checkAndGetDates1(matrix1)) {
                    return;
                }
                if (!checkAndGetDates2(matrix2)) {
                    return;
                }
                if (!checkMulty()) {
                    return;
                }
                Matrix a = new Matrix(matrix1);
                Matrix b = new Matrix(matrix2);

                switch (comboBox4.getSelectedIndex()) {
                    case 0:
                        a = a.amount(b);
                        break;
                    case 1:
                        a = a.multiply(b);
                        break;
                }
                JOptionPane.showMessageDialog(frame, a,"Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Box[][] matrix = new Box[system.length][system[0].length];
                if (!checkAndGetDates3(matrix)) {
                    return;
                }
                Matrix a = new Matrix(matrix);
                Vector<Box> answer = new Vector<>();
                int res = a.gauss(answer);
                if (res == Integer.MAX_VALUE) {
                    JOptionPane.showMessageDialog(frame, "This system cannot have a solution ","Result",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < answer.size(); i ++) {
                    s.append("x").append(i + 1).append(" = ").append(answer.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(frame, s.toString(),"Result",
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

    /**
     * reading matrix from text field of user
     */

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

    /**
     * reading matrix from text field of user
     */
    public void reloadMatrixPage2() {
        int n = comboBox6.getSelectedIndex() + 1;
        int m = comboBox5.getSelectedIndex() + 1;
        textFields1 = new JTextField[n][m];
        Matrix1.removeAll();
        //Matrix.setPreferredSize(new Dimension(15 * n + 10 * (n - 1), 15 * m + 10 * (m - 1)));
        Matrix1.setLayout(new GridBagLayout());
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
                textFields1[i][j] = new JTextField("0", 1);
                Matrix1.add(textFields1[i][j], gridBagConstraints);
            }
        }
    }

    /**
     * reading matrix from text field of user
     */
    public void reloadMatrixPage2_1() {
        int n = comboBox8.getSelectedIndex() + 1;
        int m = comboBox7.getSelectedIndex() + 1;
        textFields2 = new JTextField[n][m];
        Matrix2.removeAll();
        //Matrix.setPreferredSize(new Dimension(15 * n + 10 * (n - 1), 15 * m + 10 * (m - 1)));
        Matrix2.setLayout(new GridBagLayout());
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
                textFields2[i][j] = new JTextField("0", 1);
                Matrix2.add(textFields2[i][j], gridBagConstraints);
            }
        }
    }

    /**
     * reading matrix from text field of user
     */
    public void reloadSystem() {
        int n = comboBox9.getSelectedIndex() + 1;
        int m = comboBox10.getSelectedIndex() + 2;
        system = new JTextField[n][m];
        System.removeAll();
        //Matrix.setPreferredSize(new Dimension(15 * n + 10 * (n - 1), 15 * m + 10 * (m - 1)));
        System.setLayout(new GridBagLayout());
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
                gridBagConstraints.gridx = 2 * j;
                system[i][j] = new JTextField("0", 1);
                System.add(system[i][j], gridBagConstraints);
                gridBagConstraints.gridx = 2 * j + 1;
                System.add(new JLabel((j < m - 1 ? "x" + (j + 1) + ( j == m - 2 ? " = " : " + ") : "")), gridBagConstraints);
            }
        }
    }
    /**
     * checking dates which user have written
     */

    public boolean checkAndGetDates(Box[][] matrix) {
        for (int i = 0; i < textFields.length; i ++) {
            for (int j = 0; j < textFields[i].length; j++) {
                try {
                    matrix[i][j] = new Box(Double.parseDouble(textFields[i][j].getText()));
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n" + textFields[i][j].getText(),"Backup problem",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }

        if (comboBox1.getSelectedIndex() == 4) {
            if (comboBox2.getSelectedIndex() != comboBox3.getSelectedIndex()) {
                JOptionPane.showMessageDialog(frame, "To find the determinant, the matrix must be in the form NxN (N > 1)","Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }


    /**
     * checking dates which user have written
     */
    public boolean checkAndGetDates1(Box[][] matrix) {
        for (int i = 0; i < textFields1.length; i ++) {
            for (int j = 0; j < textFields1[i].length; j++) {
                try {
                    matrix[i][j] = new Box(Double.parseDouble(textFields1[i][j].getText()));
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n" + textFields1[i][j].getText(),"Backup problem",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }

        if (comboBox1.getSelectedIndex() == 4) {
            if (comboBox2.getSelectedIndex() != comboBox3.getSelectedIndex()) {
                JOptionPane.showMessageDialog(frame, "To find the determinant, the matrix must be in the form NxN (N > 1)","Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }


    /**
     * checking dates which user have written
     */
    public boolean checkAndGetDates2(Box[][] matrix) {
        for (int i = 0; i < textFields2.length; i ++) {
            for (int j = 0; j < textFields2[i].length; j++) {
                try {
                    matrix[i][j] = new Box(Double.parseDouble(textFields2[i][j].getText()));
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n" + textFields2[i][j].getText(),"Backup problem",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }

        if (comboBox1.getSelectedIndex() == 4) {
            if (comboBox2.getSelectedIndex() != comboBox3.getSelectedIndex()) {
                JOptionPane.showMessageDialog(frame, "To find the determinant, the matrix must be in the form NxN (N > 1)","Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }

    /**
     * checking dates which user have written
     */
    public boolean checkAndGetDates3(Box[][] matrix) {
        for (int i = 0; i < system.length; i ++) {
            for (int j = 0; j < system[i].length; j++) {
                try {
                    matrix[i][j] = new Box(Double.parseDouble(system[i][j].getText()));
                } catch(Exception er) {
                    JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n" + system[i][j].getText(),"Backup problem",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * checking dates which user have written
     */
    public boolean checkMulty() {
        if (comboBox4.getSelectedIndex() == 1) {
            if (comboBox5.getSelectedIndex() != comboBox8.getSelectedIndex()) {
                JOptionPane.showMessageDialog(frame, "For matrix multiplications the condition that m2 = n1 is not satisfied","Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            return true;
        }
        if (comboBox6.getSelectedIndex() != comboBox8.getSelectedIndex() || comboBox5.getSelectedIndex() != comboBox7.getSelectedIndex()) {
            JOptionPane.showMessageDialog(frame, "The condition that n1 = n2 and m1 = m2 is not satisfied for matrix addition","Backup problem",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }



}
