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
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JTable table3;
    private JTextField textField1;
    private JButton findButton;
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
                String noties = textArea1.getText();
                textArea1.setText(null);
                Main.notes.addElement(new Note(noties, LocalDate.now(), LocalTime.now(),box.get(comboBox1.getSelectedIndex())));
                String massage = "This note was added!";
                JOptionPane.showMessageDialog(null, massage, "System alert",JOptionPane.PLAIN_MESSAGE);
//                table1.setModel(new DefaultTableModel(Main.getArr(Main.notes), new String[]{"Note","Time","Date"}));
            }
        });

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table1.getSelectedRow()!=-1) {
                    int kol = -1;
                    for (int i=0;i<Main.notes.size();i++)
                        if (Main.notes.get(i).type.equals(comboBox3.getItemAt(comboBox3.getSelectedIndex()))) {
                            kol++;
                            if (kol == e.getFirstIndex()) {
                                NoteWrite app = new NoteWrite(i,true);
                                app.start(i, true);
                                break;
                            }
                        }
                }
            }
        });


        table3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table3.getSelectedRow()!=-1) {

                    for (int i=0;i<Main.notes.size() && Main.na[e.getFirstIndex()][4].equals("base") ;i++)
                        if (Main.na[e.getFirstIndex()][1] == Main.notes.get(i).time) {
                            NoteWrite app = new NoteWrite(i,true);
                            app.start(i, true);
                            break;
                        }

                    for (int i=0;i<Main.archive.size() && Main.na[e.getFirstIndex()][4].equals("archive") ;i++)
                        if (Main.na[e.getFirstIndex()][1] == Main.archive.get(i).time) {
                            NoteWrite app = new NoteWrite(i,false);
                            app.start(i, false);
                            break;
                        }
                }
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f=false;
                if (comboBox3.getItemCount() + 1 != box.size()) {
                    comboBox3.removeAllItems();
                    for (String i : box)
                        comboBox3.addItem(i);
                    comboBox3.removeItemAt(box.size() - 1);
                }
                f=true;
                table1.setModel(new DefaultTableModel(Main.getArr(Main.notes,box.get(comboBox3.getSelectedIndex())), new String[]{"Note","Time","Date"}));
            }
        });

        reloadButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f=false;
                if (comboBox2.getItemCount() + 1 != box.size()) {
                    comboBox2.removeAllItems();
                    for (String i : box)
                        comboBox2.addItem(i);
                    comboBox2.removeItemAt(box.size() - 1);
                }
                f=true;
                table2.setModel(new DefaultTableModel(Main.getArr(Main.archive,box.get(comboBox2.getSelectedIndex())), new String[]{"Note","Time","Date"}));
            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table2.getSelectedRow()!=-1) {
                    int kol = -1;
                    for (int i=0;i<Main.archive.size();i++)
                        if (Main.archive.get(i).type.equals(comboBox2.getItemAt(comboBox2.getSelectedIndex()))) {
                            kol++;
                            if (kol == e.getFirstIndex()) {
                                NoteWrite app = new NoteWrite(i,false);
                                app.start(i, false);
                                break;
                            }
                        }
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
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table3.setModel(new DefaultTableModel(Main.getArr(new Vector<>(0),new Vector<>(0),textField1.getText()),
                        new String[]{"Note","Time","Date","Type","Folder"}));
            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f=false;
                if (comboBox3.getItemCount() + 1 != box.size()) {
                    comboBox3.removeAllItems();
                    for (String i : box)
                        comboBox3.addItem(i);
                    comboBox3.removeItemAt(box.size() - 1);
                }
                f=true;
                table1.setModel(new DefaultTableModel(Main.getArr(Main.notes,box.get(comboBox3.getSelectedIndex())), new String[]{"Note","Time","Date"}));
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f=false;
                if (comboBox2.getItemCount() + 1 != box.size()) {
                    comboBox2.removeAllItems();
                    for (String i : box)
                        comboBox2.addItem(i);
                    comboBox2.removeItemAt(box.size() - 1);
                }
                f=true;
                table2.setModel(new DefaultTableModel(Main.getArr(Main.archive,box.get(comboBox2.getSelectedIndex())), new String[]{"Note","Time","Date"}));
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
