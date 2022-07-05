import java.awt.Graphics;

import javax.swing.JPanel;


public class Game extends JPanel{
	
	private Snake snake;
	private Food food;
	private Board board;
	public void display(Snake s, Food f, Board g){
		this.snake = s;
		this.food = f;
		this.board = g;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Variables.gameColor);
		g.fillRect(0, 0, Variables.width * Variables.cellSize,(Variables.height * Variables.cellSize + 50));
		
		if(board != null && snake != null && food != null){
			this.board.draw(g);
			this.snake.draw(g);
			this.food.draw(g);
		}
	}
}