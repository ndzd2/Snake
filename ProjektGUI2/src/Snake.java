import java.awt.*;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.*;


public class Snake {
	
	public static final int up = 1;
	public static final int down = -1;
	public static final int left = 2;
	public static final int right = -2;
	public int zjedzone = 0;
	public int punkty = 0;
	public int counter = 2;
	private int oldDir, newDir;
	boolean pauza = false;
	public boolean life;;
	private Point oldTail;
	private LinkedList <Point> body = new LinkedList <Point>();
	private Set<ISnakeMove> listeners = new HashSet<ISnakeMove>();

	public Snake(){
		Variables.speed = 150;
		body.clear();
		zjedzone = 0;
		int x = Variables.width / 2;
		int y = Variables.height / 2;
		for(int i = 0; i < 3; i++){
			body.addFirst(new Point(x--, y));
		}
		oldDir = newDir = left;
		life = true;
	}
	
	public void move(){
		if(!(oldDir + newDir == 0)){
			oldDir = newDir;
		}
		oldTail = body.removeLast();

		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(oldDir){
		case up:
			y--;
			if(y < 0){
				y = Variables.height - 1;
			}
			break;
		case down:
			y++;
			if(y > Variables.height - 1){
				y = 0;
			}
			break;
		case left:
			x--;
			if(x < 0){
				x = Variables.width - 1;
			}
			break;
		case right:
			x++;
			if(x > Variables.width - 1){
				x = 0;
			}
			break;
		}
		Point newHead = new Point(x, y);
		body.addFirst(newHead);
	}
	public void die(){
		life = false;
		JFrame finish = new JFrame();
		punkty = zjedzone * counter;
		String owoc = "";
		if (zjedzone == 1)
			owoc = "owoc";
		else if (zjedzone > 1 && zjedzone <= 4)
			owoc = "owoce";
		else
			owoc = "owoców";
		JOptionPane.showMessageDialog(finish, "Zebrałeś " + zjedzone + " " + owoc + "\nPunkty: " + punkty);
		finish.setVisible(false);
		finish.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			PrintWriter writer = new PrintWriter("highscores.txt", "UTF-8");
			writer.println(punkty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finish.dispose();
		System.gc();
		Window win[] = Window.getWindows();
		for(int i=0;i<win.length;i++){
			win[i].dispose();
			win[i]=null;
		}
		new NewWindow();
	}
	public Point getFirst(){
		return body.getFirst();
	}
	public void changeDirection(int direction){
		newDir = direction;
	}
	public void zjedz(){
		body.addLast(oldTail);
		counter *= 1.5;
	}
	public boolean zjadlSamSiebie(){
		
		for(int i = 1; i < body.size(); i++){
			if(body.get(i).equals(this.body.getFirst())){
				return true;
			}
		}
		return false;
	}
	public void draw(Graphics g){
		g.setColor(Variables.snakeColor);
		for(Point p : body){
			g.fill3DRect((p.x) * Variables.cellSize, p.y * Variables.cellSize,
					Variables.cellSize, Variables.cellSize, true);
		}
	}
	private class SnakeDiver implements Runnable{
		public void run(){
			while(life){
				int cnt = 0;
				move();
				for(ISnakeMove l : listeners){
					l.snakeMoved(Snake.this);
				}
				try{
					Thread.sleep(Variables.speed);
					cnt = cnt + 1;
					 synchronized(this) {
		                    while(pauza){
		                        Thread.sleep(100);
		                    }
		                }
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	public void pause(){
		pauza = !pauza;
	}
	public void start(){
		new Thread(new SnakeDiver()).start();
	}
	public void addSnakeListener(ISnakeMove l){
		if(l != null){
			this.listeners.add(l);
		}
	}
}
