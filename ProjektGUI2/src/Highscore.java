import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Highscore {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JButton exit = new JButton("Wyjdź");
    ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exit) {
                frame.dispose();
            }
        }
    };
    public Highscore() {
        exit.setBounds(0, 0, 100, 50);
        exit.setFocusable(false);
        exit.addActionListener(action);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        try {
            BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
            String line, text;
            text = "";

            while((line = in.readLine()) != null){
                text += ("\n" + line);
            }
            label.setText(text);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.add(label);
        frame.add(exit);
    }
}
