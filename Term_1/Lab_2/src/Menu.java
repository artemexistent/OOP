import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Menu {
    public static JFrame frame = new JFrame("Notes");
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton createButton;
    public JTable table1;
    private JTextArea textArea1;
    private JButton reloadButton;
    private JTable table2;
    private JButton reloadButton1;
    public JComboBox<String> comboBox1;
    private JButton reloadButton2;
    public static Vector<String> box;
    private static Boolean f = true;

    public Menu() {
        box = new Vector<>(0);
        box.addElement("All");
        box.addElement("Add new...");
        comboBox1.setEditable(false);
        comboBox1.setSelectedIndex(0);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noties =  textArea1.getText();
                textArea1.setText(null);
                Main.notes.addElement(new Note(noties, LocalDate.now(), LocalTime.now()));
                String massage = "This note was added!";
                JOptionPane.showMessageDialog(null, massage, "System alert",JOptionPane.PLAIN_MESSAGE);
                table1.setModel(new DefaultTableModel(Main.getArr(Main.notes), new String[]{"Note","Time","Date"}));
            }
        });

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table1.getSelectedRow()!=-1) {
                    NoteWrite app = new NoteWrite(e.getFirstIndex(),true);
                    app.start(e.getFirstIndex(),true);
                }
            }
        });


        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new DefaultTableModel(Main.getArr(Main.notes), new String[]{"Note","Time","Date"}));
            }
        });

        reloadButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table2.setModel(new DefaultTableModel(Main.getArr(Main.archive), new String[]{"Note","Time","Date"}));
            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table2.getSelectedRow()!=-1) {
                    NoteWrite app = new NoteWrite(e.getFirstIndex(),false);
                    app.start(e.getFirstIndex(),false);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() == comboBox1.getItemCount() - 1 && f){
                    ComboBox app = new ComboBox();
                    app.start();
                    if (comboBox1.getItemCount()!=0) comboBox1.setSelectedIndex(0);
                }
            }
        });
        reloadButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f=false;
                comboBox1.removeAllItems();
                for (String i : box)
                    comboBox1.addItem(i);
                f=true;
            }
        });
    }

    public void start(){
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
