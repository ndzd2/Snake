import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Board extends Point{
	
	private int[][] rocks = new int[Variables.width][Variables.height];
	
	public Board(){
		for(int x = 0; x < Variables.width; x++){
			rocks[x][0] = 1;
			rocks[x][Variables.height - 1] = 1;
		}
		for(int y = 0; y < Variables.height; y++){
			rocks[0][y] = 1;
			rocks[Variables.width - 1][y] = 1;
		}
	}
	public void newGround(Point p){
		rocks[p.x][p.y] = 1;
	}
	
	public boolean zjadlKamien(Snake snake){
		for(int x = 0; x < Variables.width; x++){
			for(int y = 0; y < Variables.height; y++){
				if(rocks[x][y] == 1 &&
						(x == snake.getFirst().x && y == snake.getFirst().y) ){
					return true;
				}
			}
		}
		return false;
	}
	public Point getPoint(){
		Random random = new Random();
		int x = 0, y = 0;
		do{
		x = random.nextInt(Variables.width);
		y = random.nextInt(Variables.height);
		}while(rocks[x][y] == 1);
		return new Point(x, y);
	}
	public void draw(Graphics graph){
		graph.setColor(Variables.rockColor);
		for(int x = 0; x < Variables.width; x++){
			for(int y = 0; y < Variables.height; y++){
				if(rocks[x][y] == 1){
					graph.fill3DRect(x * Variables.cellSize, y * Variables.cellSize,
							Variables.cellSize, Variables.cellSize, true);
				}
			}
		}
	}
}