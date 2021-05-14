import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Loop extends JPanel implements ActionListener, KeyListener {
	//variables
	Timer timer;
	Paddle[] paddles;
	int ballSize;
	int ballSpeed;
	int leftScore;
	int rightScore;
	int rightWins;
	int leftWins;
	int frames;
	int up;
	boolean left;
	ArrayList<Ball> balls;
	
	public Loop(){
		//run code once
		int paddleSpeed = 15;
		ballSpeed = 10;
		ballSize = 25;
		Ball ball = new Ball(ballSize, ballSpeed);
		balls = new ArrayList<Ball>();
		balls.add(ball);
		paddles = new Paddle[2];
		paddles[1] = new Paddle(100, paddleSpeed, "left");
		paddles[0] = new Paddle(100, paddleSpeed, "right");
		leftScore = 0;
		rightScore = 0;
		frames = 0;
		left = true;
		up = 1;
		//addKeyListener(this);
		timer = new Timer(16, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//run code each frame
		for(Ball ball: balls){
			if(ball.x < 0-ball.xSpeed || ball.x+ball.size > Main.screenSize+ball.xSpeed){
				if(ball.xDirection.equals("left")){
					rightScore++;
				}
				if(ball.xDirection.equals("right")){
					leftScore++;
				}
				ball.toBeKilled = true;
			}
			ball.collision(paddles);
			ball.move();
		}
		
		for(int i = 0; i < balls.size(); i++){
			if(balls.get(i).toBeKilled){
				balls.remove(i);
				i--;
			}
		}
		for(Paddle p: paddles){
			Ball closeBall = null;
			for(Ball b: balls){
				if(closeBall == null){
					closeBall = b;
				}
				if(p.side.equals("left") && b.xDirection == "left" && b.x < closeBall.x){
					closeBall = b;
				}
				if(p.side.equals("right") && b.xDirection == "right" && b.x > closeBall.x){
					closeBall = b;
				}
			}
			p.move(closeBall);
		}
		frames++;
		if(frames%90 == 0){
//			for(Ball b: balls){
//				b.xSpeed++;
//			}
			Ball ball = new Ball(ballSize, ballSpeed);
			if(left){
				ball.xDirection = "left";
			}
			left = !left;
			if(up == 2 || up == 3){
				ball.yDirection = "up";
			}
			up = (up+1)%4;
			balls.add(ball);
			
		}
		repaint();
	}
	
	public void paintComponent(Graphics g){
		//draw graphics
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.screenSize, Main.p);
		g.setColor(Color.WHITE);
		for(int y = 15; y < Main.p; y+=30){
			g.fillRect(Main.screenSize/2-7, y, 15, 15);
		}
		drawScore(g, leftScore, Main.screenSize/2-200);
		drawScore(g, rightScore, Main.screenSize/2+140);
		
		g.setColor(Color.RED);
		
		drawScore(g, leftWins, 100);
		drawScore(g, rightWins, Main.screenSize-180);
		
		g.setColor(Color.WHITE);
		for(Ball b: balls){
			b.draw(g);
		}
		for(Paddle p: paddles){
			p.draw(g);
		}
		requestFocus();
	}
	
	public void drawScore(Graphics g, int score, int x){
		switch(score){
		case 0:
			num1(x,g);num2(x,g);num3(x,g);num4(x,g);num5(x,g);num6(x,g);
			break;
		case 1:
			num4(x,g);num5(x,g);
			break;
		case 2:
			num0(x,g);num1(x,g);num3(x,g);num4(x,g);num6(x,g);
			break;
		case 3:
			num0(x,g);num3(x,g);num4(x,g);num5(x,g);num6(x,g);
			break;
		case 4:
			num0(x,g);num2(x,g);num4(x,g);num5(x,g);
			break;
		case 5:
			num0(x,g);num2(x,g);num3(x,g);num5(x,g);num6(x,g);
			break;
		case 6:
			num0(x,g);num1(x,g);num2(x,g);num3(x,g);num5(x,g);num6(x,g);
			break;
		case 7:
			num2(x,g);num3(x,g);num4(x,g);num5(x,g);
			break;
		case 8:
			num0(x,g);num1(x,g);num2(x,g);num3(x,g);num4(x,g);num5(x,g);num6(x,g);
			break;
		case 9:
			num0(x,g);num2(x,g);num3(x,g);num4(x,g);num5(x,g);
			break;
		case 10:
			if(leftScore == 10){
				leftWins++;
			}
			if(rightScore == 10){
				rightWins++;
			}
			if(leftWins == 10 || rightWins == 10){
				leftWins = 0;
				rightWins = 0;
			}
			leftScore = 0;
			rightScore = 0;
			break;
		}
	}
	
	public void num0(int x, Graphics g){
		g.fillRect(x, 160, 80, 10);
	}
	public void num1(int x, Graphics g){
		g.fillRect(x, 160, 10, 70);
	}
	public void num2(int x, Graphics g){
		g.fillRect(x, 100, 10, 70);
	}
	public void num3(int x, Graphics g){
		g.fillRect(x, 100, 80, 10);
	}
	public void num4(int x, Graphics g){
		g.fillRect(x+70, 100, 10, 70);
	}
	public void num5(int x, Graphics g){
		g.fillRect(x+70, 160, 10, 70);
	}
	
	public void num6(int x, Graphics g){
		g.fillRect(x, 220, 80, 10);
	}
	
	//controls
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()){
		case 'w':
			//up
			break;
		case 'a':
			//left
			break;
		case 's':
			//down
			break;
		case 'd':
			//right
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyChar()){
		case 'w':
			//up
			break;
		case 'a':
			//left
			break;
		case 's':
			//down
			break;
		case 'd':
			//right
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
