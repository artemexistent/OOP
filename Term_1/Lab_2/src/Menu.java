import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private final JFrame frame = new JFrame();
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextPane textPane1;
    private JButton createButton;


    public Menu() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noties =  textPane1.getText();
                textPane1.setText(null);
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
