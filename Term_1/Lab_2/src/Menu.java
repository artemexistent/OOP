import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Menu {
    private final JFrame frame = new JFrame();
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton createButton;
    private JTable table1;
    private JTextArea textArea1;


    public Menu() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noties =  textArea1.getText();
                textArea1.setText(null);
                Main.notes.addElement(new Note(noties, LocalDate.now(), LocalTime.now()));
                String massage = "This note was added!";
                JOptionPane.showMessageDialog(null, massage, "Output",JOptionPane.PLAIN_MESSAGE);
                table1.setModel(new DefaultTableModel(Main.getNotesArr(), new String[]{"Note","Time","Date"}));
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
