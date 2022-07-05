import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Controller extends KeyAdapter implements ISnakeMove {

	private Snake snake;
	private Food food;
	private Board board;
	private Game game;


	
	public Controller(Snake snake, Food food, Board board, Game game) {
		super();
		this.snake = snake;
		this.food = food;
		this.board = board;
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			snake.changeDirection(Snake.up);
			break;
		case KeyEvent.VK_S:
			snake.changeDirection(Snake.down);
			break;
		case KeyEvent.VK_A:
			snake.changeDirection(Snake.left);
			break;
		case KeyEvent.VK_D:
			snake.changeDirection(Snake.right);
			break;
		case KeyEvent.VK_SPACE:
			snake.pause();
			break;
		}
}
	@Override
	public void snakeMoved(Snake snake) {
		
		if(snake.zjadlSamSiebie()){
			snake.die();
		}
		if(board.zjadlKamien(snake)){
			snake.die();
		}
		if(food.zjadlOwoc(snake)){
			snake.zjedz();
			snake.zjedzone = snake.zjedzone + 1;
			System.out.println(snake.zjedzone);
			
			Variables.speed -= Variables.speedAdd;
			board.newGround(board.getPoint());
			food.newFood(board.getPoint());
		}
		game.display(snake, food, board);
	}
	
	public void newGame(){
		snake.start();
		food.newFood(board.getPoint());
	}
}
