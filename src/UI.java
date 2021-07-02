import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	
	BufferedImage start, dead, scoreboard, play;
	BufferedImage bronze, silver, gold, platinum;
	private int startux, deadux, scbx;
	private int startuy, deaduy, scby;
	public UI()
	{
		try {
			start = ImageIO.read(new File("res/sprites/message.png"));
			dead = ImageIO.read(new File("res/sprites/gameover.png"));
			scoreboard = ImageIO.read(new File("res/sprites/ui/scoreboard.png"));
			play = ImageIO.read(new File("res/sprites/ui/play.png"));
			bronze = ImageIO.read(new File("res/sprites/ui/bronze.png"));
			silver = ImageIO.read(new File("res/sprites/ui/silver.png"));
			gold = ImageIO.read(new File("res/sprites/ui/gold.png"));
			platinum = ImageIO.read(new File("res/sprites/ui/platinum.png"));
		} catch (IOException e) {
			Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
		}
		startux = 2 * start.getWidth();
		startuy = 2 * start.getHeight();
		deadux = 2 * dead.getWidth();
		deaduy = 2 * dead.getHeight();
		scbx = (int) (1.3 * scoreboard.getWidth());
		scby = (int) (1.3 * scoreboard.getHeight());
		
		System.out.println("Log: UI initialized");
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g)
	{
		if(Game.started == false)
		{
			g.drawImage(start, Frame.WIDTH/2 - startux/2, Frame.HEIGHT/2 - startuy/2 - (int) (Frame.HEIGHT/10.5), startux, startuy, null);
		}
		
		if(Game.alive == false && Game.started == true)
		{
			g.drawImage(dead, Frame.WIDTH/2 - deadux/2, Frame.HEIGHT/5 - deaduy/2, deadux, deaduy, null);
			g.drawImage(scoreboard, Frame.WIDTH/2 - scbx/2, (int) (Frame.HEIGHT/2.5 - scby/2), scbx, scby, null);
			g.drawImage(play, 174, 563, 228, 146, null);
			
			//g.setColor(Color.RED);
			//g.drawLine(174, 563, 402, 563);
			//g.drawLine(174, 563, 174 , 690);
			
			renderMedal(g);
		}
	}
	
	private void renderMedal(Graphics2D g)
	{
		if(Game.medal.equals("bronze") == true)
		{
			g.drawImage(bronze, Frame.WIDTH/4 - (int) (bronze.getWidth()*0.22), Frame.HEIGHT/2 - bronze.getHeight() - 9, (int) (0.75 * bronze.getWidth()), (int) (0.75 * bronze.getHeight()), null); 
		}
		
		if(Game.medal.equals("silver") == true)
		{
			g.drawImage(silver, Frame.WIDTH/4 - (int) (silver.getWidth()*0.22), Frame.HEIGHT/2 - silver.getHeight() - 9, (int) (0.75 * silver.getWidth()), (int) (0.75 * silver.getHeight()), null); 
		}
		
		if(Game.medal.equals("gold") == true)
		{
			g.drawImage(gold, Frame.WIDTH/4 - (int) (gold.getWidth()*0.22), Frame.HEIGHT/2 - gold.getHeight() - 9, (int) (0.75 * gold.getWidth()), (int) (0.75 * gold.getHeight()), null); 
		}
		
		if(Game.medal.equals("platinum") == true)
		{
			g.drawImage(platinum, Frame.WIDTH/4 - (int) (platinum.getWidth()*0.22), Frame.HEIGHT/2 - platinum.getHeight() - 9, (int) (0.75 * platinum.getWidth()), (int) (0.75 * platinum.getHeight()), null); 
		}
	}
	
}
