import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBox {
    private JPanel panel1;
    private JTextField textField1;
    private JButton enterButton;
    private static final JFrame frame = new JFrame();

    public ComboBox() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField1.getText();
                frame.dispose();
                Menu.box.addElement("Add new...");
                Menu.box.set(Menu.box.size()-2,s);
            }
        });
    }

    public void start(){
        frame.setContentPane(new ComboBox().panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
