import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Floor {
	BufferedImage floor;
	int ux, uy;
	static float low;
	int x;
	
	public Floor(int startx)
	{
		try {
			floor = ImageIO.read(new File("res/sprites/base.png"));
		} catch (IOException e) {
			Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
		}
		
		x = startx;
		
		ux = floor.getWidth();
		uy = floor.getHeight();
		
		low = Frame.HEIGHT - (2 * uy);
	}
	
	public void render(Graphics2D g)
	{
		//upside down floors
		//g.drawImage(floor, 0, (int) low, ux, uy, null);
		//g.drawImage(floor, Frame.WIDTH-ux, (int) low, ux, uy, null);
		
		Panel.drawRotated(g, floor, 180, Frame.WIDTH/2, Frame.HEIGHT, ux, uy);
		Panel.drawRotated(g, floor, 180, Frame.WIDTH, Frame.HEIGHT, ux, uy);
		
		//floor 1
		g.drawImage(floor, x, (int) low, ux, uy, null);
		
		

	}

	public void update()
	{

		if(Game.alive == true || Game.started == false)
		{
			x-= Game.moveConstant;
				
		}
				
		if(x<=0-ux)
		{
			x=Frame.WIDTH;
		}
			
	}
}
