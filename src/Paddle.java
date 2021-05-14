import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	int x,y,size,speed,width;
	String side;
	
	public Paddle(int size, int speed, String side){
		this.size = size;
		this.y = 200;
		this.side = side;
		this.speed = speed;
		this.width = 20;
		
		if(this.side.equals("left")){
			this.x = 40;
		}
		if(this.side.equals("right")){
			this.x = Main.screenSize-40-width;
		}
	}
	
	public boolean shouldMove(Ball ball){
		if(((y-speed<0 && ball.yDirection.equals("up")) || 
				(y+size+speed+30 > Main.p && ball.yDirection.equals("down"))) && Math.abs(this.y+this.size/2 - ball.y+ball.size/2) < this.size/2 ){
			return false;
		}
		if(side.equals(ball.xDirection)){
				return true;
		}
		return false;
	}
	
	public void move(Ball ball){
		if(shouldMove(ball)){
			if(this.y+this.size/2 < ball.y+ball.size/2 && 
					(ball.yDirection.equals("down") || Math.abs(this.y+this.size/2 - ball.y+ball.size/2) > this.size/2)){
				y += speed;
			}
			else if (this.y+this.size/2 > ball.y+ball.size/2 && 
					ball.yDirection.equals("up") || Math.abs(this.y+this.size/2 - ball.y+ball.size/2) > this.size/2){
				y -= speed;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, size);
	}
	
}
