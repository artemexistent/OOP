import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Menu {
    private final JFrame frame = new JFrame();
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextPane textPane1;
    private JButton createButton;
    private JTable table1;


    public Menu() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noties =  textPane1.getText();
                textPane1.setText(null);
                Main.notes.addElement(new Note(noties, LocalDate.now(), LocalTime.now()));
                String massage = "This note was added!";
                JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                table1.setModel(new DefaultTableModel(Main.getNotesArr(), new String[]{"Time","Date"}));
            }
        });

    }

    public void start(){
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//    private void createTable(){
//        table1.setModel(new DefaultTableModel(
//                null,
//                new String[]{"Time","Date"}
//        ));
//    }

}
