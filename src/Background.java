import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
	
	Floor[] f;
	
	BufferedImage background;
	
	int ux;
	int uy;
	
	
	
	
	public Background()
	{
		try {
			
			background = ImageIO.read(new File("res/sprites/background-day.png"));
		} catch (IOException e) {
			Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
		}
	
		ux = background.getWidth();
		uy = background.getHeight();
		
		f = new Floor[3];
		createFloors();
		
		System.out.println("Log: Background initialized");
	}
	
	public void createFloors()
	{
		f[0] = new Floor(0);
		f[1] = new Floor(Frame.WIDTH-ux);
		f[2] = new Floor(Frame.WIDTH);
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(background, 0, 0, Frame.WIDTH, Frame.HEIGHT, null);	
	}
	
	public void renderOther(Graphics2D g)
	{
		Font font = new Font("Times New Roman", 0, 20);
        g.setFont(font);
        g.drawString("Unfortunately, the game textures seem to be missing.", (Frame.WIDTH / 2) - 230, Frame.HEIGHT / 2);
        g.drawString("Please check the sprites and audio under the res folder", (Frame.WIDTH / 2) - 230, (Frame.HEIGHT / 2) + 50);
        font = new Font("Times New Roman", 0, 30);
        g.drawString("Created by Eric Feng.", (Frame.WIDTH / 2) - 100, (Frame.HEIGHT / 2) -300);
	}
	
	public void update()
	{
		for(int i = 0; i < f.length; i++)
		{
			f[i].update();
		}
	}
	
	public void renderFloor(Graphics2D g)
	{
		for(int i = 0; i < f.length; i++)
		{
			f[i].render(g);
		}
	}
}
