import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI {
    private JTextField textField1;
    private JPanel panel;
    private static JFrame frame;

    public StartGUI() {
        frame = new JFrame("Histogram Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.number = (Integer.parseInt(textField1.getText()));
                    HistogramPanel.start();
                    frame.setVisible(false);
                } catch (Exception r) {
                    textField1.setText("");
                    JOptionPane.showMessageDialog(frame, "The data is not entered correctly.\n" +
                                    "The data must contain ONLY numbers.\n","Backup problem",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }



    public static void main(String[] args) {
        StartGUI startGUI = new StartGUI();
    }
}
