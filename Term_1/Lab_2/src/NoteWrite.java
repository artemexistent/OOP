import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteWrite {
    private JPanel panel1;
    private JButton deletingButton;
    private JButton archivingButton;
    private JButton reestablishButton;
    private JButton editButton;
    private JTextArea textArea1;
    private JLabel label;
    public static final JFrame frame = new JFrame();


    public NoteWrite(int i, boolean f) {

        if (f) {
            label.setText(Main.notes.get(i).write());
            textArea1.setText(Main.notes.get(i).note);
        }else{
            label.setText(Main.archive.get(i).write());
            textArea1.setText(Main.archive.get(i).note);
        }



        archivingButton.setVisible(f);
        reestablishButton.setVisible(!f);



        deletingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (f)
                    Main.notes.remove(i);
                else Main.archive.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Deleted","System alert",JOptionPane.PLAIN_MESSAGE);
            }
        });
        archivingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.archive.addElement(Main.notes.get(i));
                Main.sort(Main.archive);
                Main.notes.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Archived","System alert",JOptionPane.PLAIN_MESSAGE);
            }
        });
        reestablishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.notes.addElement(Main.archive.get(i));
                Main.sort(Main.notes);
                Main.archive.remove(i);
                frame.dispose();
                JOptionPane.showMessageDialog(null,"Reestablished","System alert",JOptionPane.PLAIN_MESSAGE);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note p ;
                if (f)
                    p = Main.notes.get(i);
                else p = Main.archive.get(i);
                p.note = textArea1.getText();
                if (f)
                    Main.notes.set(i,p);
                else Main.archive.set(i,p);
                JOptionPane.showMessageDialog(null,"Edited","System alert",JOptionPane.PLAIN_MESSAGE);
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
