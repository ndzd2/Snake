import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow implements ActionListener {

    JFrame frame = new JFrame();
    JButton newGame = new JButton("Nowa gra");
    JButton highScores = new JButton("Tabela wyników");
    JButton exit = new JButton("Wyjdź");

    public NewWindow() {
        newGame.setBounds(110, 125, 200, 50);
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        highScores.setBounds(135, 200, 150, 40);
        highScores.setFocusable(false);
        highScores.addActionListener(this);
        exit.setBounds(135, 265, 150, 40);
        exit.setFocusable(false);
        exit.addActionListener(this);

        frame.add(newGame);
        frame.add(highScores);
        frame.add(exit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            new GameWindow();
        } else if (e.getSource() == highScores) {
            new Highscore();
        } else if (e.getSource() == exit) {
            frame.setVisible(false);
            frame.dispose();
        }
    }
}