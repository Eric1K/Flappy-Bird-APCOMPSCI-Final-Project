import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private static final long serialVersionUID = -9036015987764800991L;
	
	private Bird bird;
	private Game game;
	private Background background;
	private Pipes pipes;
	private Score score;
	private UI ui;
	
	Timer gameT;
	TimerTask loop;
	
	
	public static final int FPS = 60;
	
	public Panel()
	{
		actionListener listener = new actionListener();
		this.addKeyListener(listener);
		this.addMouseListener(listener);
		this.setFocusable(true);
        this.requestFocusInWindow();
        
        System.out.println("Log: Panel created");
        
        bird = new Bird();
        background = new Background();
        pipes = new Pipes();
        game = new Game();
        ui = new UI();
        score = new Score();
        bird.start();
        
        startGame();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
        Graphics2D g2d = (Graphics2D) g;
        
        background.renderOther(g2d);
        background.render(g2d);
        pipes.render(g2d);
        background.renderFloor(g2d);
        bird.render(g2d);
        ui.render(g2d);
        score.render(g2d);

	}
	
	public void startGame()
	{
		gameT = new Timer();
		gameT.schedule(new GameLoop(), 0L, 1000 / FPS);
	}
	
	private class GameLoop extends java.util.TimerTask
	{
	    public void run() 
	    {
	    	update();
	    	render();
	    }
	}
	
	private void update()
	{
		bird.update();
		background.update();
		pipes.update();
		ui.update();
		score.update();
	}
	
	private void render()
	{
		this.repaint();
	}
	
	public static void drawRotated(Graphics2D g2d, BufferedImage image, int angle, int x, int y, int width, int height)
	{
		AffineTransform old = g2d.getTransform();
    	g2d.rotate(Math.toRadians(angle), x, y);
    	
    	g2d.drawImage(image, x, y , width, height, null);
		
		g2d.setTransform(old);
	}
	
	public void restart()
	{
		System.out.println("----------------------\n" );
	    System.out.println("Log: Game restarted");
	    
	    score.restart();
		bird = new Bird();
	    pipes = new Pipes();
	    game = new Game();
	    ui = new UI();
	    score = new Score();
	    bird.start(); 
	}
	
	private class actionListener implements KeyListener, MouseListener
	{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 32 || e.getKeyCode()== KeyEvent.VK_UP)
			{
				jump();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			jump();
		}
		
		public void jump()
		{
			//intialize game
			if(Game.started == false)
			{
				game.startGame();
				bird.fly();
				bird.start();
			}else
			{
				if(Game.alive == true)
				{
					bird.fly();
				}
				
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(Game.alive == false && Game.started == true)
			{
				if(e.getX() > 174 == true && e.getX() < 402 == true && e.getY() < 690 == true && e.getY() > 563 == true)
				{
					Sound.playSound(Sound.swoosh);
					restart();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}

}