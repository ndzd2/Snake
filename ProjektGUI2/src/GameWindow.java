import javax.swing.*;
import java.awt.*;

public class GameWindow {
    Game game = new Game();
    Board board = new Board();
    Snake snake = new Snake();
    Food food = new Food();
    Controller controller = new Controller(snake, food, board, game);
    JButton button = new JButton();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    public GameWindow() {
        game.setSize(Variables.width * Variables.cellSize, Variables.height * Variables.cellSize);
        game.addKeyListener(controller);

        button.setBounds(150, 575, 80, 30);

        panel.setSize(Variables.width * Variables.cellSize + 15,80);
        panel.setBackground(Color.BLACK);
        panel.add(button);
        panel.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Variables.width * Variables.cellSize + 15, Variables.height * Variables.cellSize + 35);
        frame.add(game, BorderLayout.CENTER);
        frame.addKeyListener(controller);
        frame.setVisible(true);

        label.setText(String.valueOf(snake.zjedzone));

        snake.addSnakeListener(controller);
        controller.newGame();
    }
}
