import java.awt.Graphics;
import java.awt.Point;


public class Food extends Point{
	
	public void newFood(Point p){
		this.setLocation(p);
	}
	
	public boolean zjadlOwoc(Snake snake){
		return this.equals(snake.getFirst());
	}
	public void draw(Graphics g){
		g.setColor(Variables.foodColor);
		g.fill3DRect(x * Variables.cellSize, y * Variables.cellSize,
				Variables.cellSize, Variables.cellSize, true);
	}
}