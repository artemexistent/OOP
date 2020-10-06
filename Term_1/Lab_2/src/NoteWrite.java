import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteWrite {
    private JPanel panel1;
    private JButton deletingButton;
    private JButton archivingButton;
    private JLabel lable;
    public static final JFrame frame = new JFrame();


    public NoteWrite(int i) {

        lable.setText(Main.notes.get(i).write());

        deletingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.notes.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Deleted","Output",JOptionPane.PLAIN_MESSAGE);
                Main.app.table1.setModel(new DefaultTableModel(Main.getNotesArr(), new String[]{"Note","Time","Date"}));
            }
        });
        archivingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void start(int i){
        frame.setContentPane(new NoteWrite(i).panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
