import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Pipe {
	BufferedImage pipe;
	int ux,uy;
	
	float x,y;
	
	final static int difference = 200;
	
	float ry;
	private Random r = new Random();
	
	private boolean pass;
	
	public Pipe(int startx)
	{
		try {
			pipe = ImageIO.read(new File("res/sprites/pipe-green.png"));
		} catch (IOException e) {
			Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
		}
		
		x = startx;
		
		ux = pipe.getWidth();
		uy = pipe.getHeight();
		
		//ry = 100;
		ry = randomY();
		pass = false;
	}
	
	
	public void update()
	{
		
		if(Game.started == true)
		{
			if(Game.alive == true)
			{
				x-= Game.moveConstant;
				
			}
			
			if(x<=0-(ux*2))
			{
				x= (Frame.WIDTH + 374);
				ry = randomY();
				pass = false;
			}
			
			if(pass == false)
			{
				if(x <= Bird.x)
				{
					pass = true;
					Game.score++;
					Sound.playSound(Sound.point);
				}
			}
			
			
			//System.out.println(Game.score);
		}
		
		y = ry;
				
	}
	
	public void render(Graphics2D g)
	{
		
		g.drawImage(pipe, (int) x, (int) ry + difference/2, (int) (2 * ux), (int) (2 * uy), null);
		Panel.drawRotated(g, pipe, 180, (int) x + (ux * 2), (int) ry - difference/2, (int) (2 * ux), (int) (2 * uy));
	}
	
	private float randomY()
	{
		return (r.nextFloat() * (Frame.HEIGHT-600)) + 150;
	}
}
