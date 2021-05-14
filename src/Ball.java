import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	int x,y,size,xSpeed,ySpeed;
	String xDirection, yDirection;
	boolean passed, toBeKilled;
	
	public Ball(int size, int speed){
		x = Main.screenSize/2;
		y = Main.p/2;
		this.size = size;
		this.xSpeed = speed;
		this.ySpeed = 8;
		this.passed = false;
		this.xDirection = "right";
		this.yDirection = "down";
		this.toBeKilled = false;
	}
	
	public void collision(Paddle[] paddles){
		if(!passed){
			for(Paddle p: paddles){
				if(p.side.equals("left")){
					// if hit, change direction
					if(this.x < p.x+p.width){
						if(this.y+this.size > p.y && this.y < p.y+p.size){
							xDirection = "right";
							int num = (int) Math.floor(Math.random()*2);
							if(num == 1){
								if(yDirection.equals("up")){
									yDirection = "down";
								}
								if(yDirection.equals("down")){
									yDirection = "up";
								}
							}
						}
						else{
							passed = true;
						}
					}
				}
				if(p.side.equals("right")){
					if(this.x+this.size > p.x){
						if(this.y+this.size > p.y && this.y < p.y+p.size){
							xDirection = "left";
						}
						else{
							passed = true;
						}
					}
				}
			}
		}
		if(y < 0){
			yDirection = "down";
		}
		if(y+size+50 > Main.p){
			yDirection = "up";
		}
	}
	
	public void move(){
		if(xDirection.equals("left")){
			x -=xSpeed;
		}
		if(xDirection.equals("right")){
			x += xSpeed;
		}
		if(yDirection.equals("down")){
			y += ySpeed;
		}
		if(yDirection.equals("up")){
			y -= ySpeed;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}
	
}
