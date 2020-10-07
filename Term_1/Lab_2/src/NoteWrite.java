import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteWrite {
    private JPanel panel1;
    private JButton deletingButton;
    private JButton archivingButton;
    private JLabel lable;
    private JButton reestablishButton;
    public static final JFrame frame = new JFrame();


    public NoteWrite(int i, boolean f) {

        if (f)
            lable.setText(Main.notes.get(i).write());
        else lable.setText(Main.archive.get(i).write());

        archivingButton.setVisible(f);
        reestablishButton.setVisible(!f);



        deletingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (f)
                    Main.notes.remove(i);
                else Main.archive.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Deleted","Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
        archivingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.archive.addElement(Main.notes.get(i));
                Main.sort(Main.archive);
                Main.notes.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Archived","Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
        reestablishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.notes.addElement(Main.archive.get(i));
                Main.sort(Main.notes);
                Main.archive.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Reestablished","Output",JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void start(int i, boolean f){
        frame.setContentPane(new NoteWrite(i,f).panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
