import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

public class Bird {
	
	public static float x,y, veX, veY;
	public int size = 50;
	
	BufferedImage birdup, birdmid, birddown;
	//All images are 34 by 24
	
	private int ux;
	private int uy;
	private int lastangle;
	
	public boolean lookUp = true;
	int flap;
	private int fallangle;
	private boolean low = false;;
	
	private Timer wingFlap;
	//private Timer Tlook;
	
	//private TimerTask lookT;
	private TimerTask flapT;
	
	
	
	public Bird()
	{
		x = Frame.WIDTH/4;
		y = Frame.HEIGHT/2;
		
		flap = 1;
		
		try {
            birdup = ImageIO.read(new File("res/sprites/yellowbird-upflap.png"));
            birdmid = ImageIO.read(new File("res/sprites/yellowbird-midflap.png"));
            birddown = ImageIO.read(new File("res/sprites/yellowbird-downflap.png"));
        }
        catch(IOException e) {
        	Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
        }
		

		ux = birdup.getWidth();
		uy = birdup.getHeight();
		
		
		System.out.println("Log: Bird class has been initialized");
	}
	
	
	
	
	
	public void render(Graphics2D g2d)
	{
        g2d.setColor(Color.BLACK);
        
        
        if(Game.alive == true || Game.hitPipe == true || Game.started == false)
        {
        	
        	if(fallangle > 90)
        	{
        		fallangle = 90;
        	}
        	/*
        		Look 0 = flap down
        		Look 1 = flap middle
        		Look 2 = flap up
        	*/
        	if(Game.started == false)
        	{
        		fallangle = 0;
        		lookUp = false;
        	}
        	if(flap == 0)
        	{
        		if(lookUp == true)
        		{
        			drawBird(g2d, birddown, -20);
            		lastangle = -20;
        		}else
        		{
        			drawBird(g2d, birddown, fallangle );
            		lastangle = fallangle;
        		}
        	}else if(flap == 1)
        	{
        		if(lookUp == true)
            	{
            		//draw up bird
            		drawBird(g2d, birdmid, -20);
            		lastangle = -20;
            	}else
            	{
            		//draw down bird
            		drawBird(g2d, birdmid, fallangle);
            		lastangle = fallangle;
            	}
        	}else if(flap == 2)
        	{
        		if(lookUp == true)
            	{
            		//draw up bird
            		drawBird(g2d, birdup, -20);
            		lastangle = -20;
            	}else
            	{
            		//draw down bird
            		drawBird(g2d, birdup, fallangle);
            		lastangle = fallangle;
            	}
        	}else
        	{
        		System.out.println("Error: Integer flap is out of range");
        	}
        	/*
        	if(flap == true)
            {
            	//Flapping
        		
        		if(lookUp == true)
            	{
            		//draw up bird
            		drawBird(g2d, birdup, -20);
            		lastangle = -20;
            	}else
            	{
            		//draw down bird
            		drawBird(g2d, birdup, fallangle );
            		lastangle = fallangle;
            	}
        		
            }else
            {
            	// Not flapping
            	
            	if(lookUp == true)
            	{
            		//draw up bird
            		drawBird(g2d, birdmid, -20);
            		lastangle = -20;
            	}else
            	{
            		//draw down bird
            		drawBird(g2d, birdmid, fallangle);
            		lastangle = fallangle;
            	}
            	
            	
            }
            */
        }else if(Game.alive == false)
        {
        	if(low == true)
        	{
        		if(lookUp == true)
        		{
            		drawBird(g2d, birdmid, -20);
        		}else
        		{
        			drawBird(g2d, birdmid, lastangle);	
        		}
        		
        	}
        	
        }
      //Flap: g2d.drawImage(birdup, (int)x - ux, (int)y - yx , ux*2, yx*2, null);
      //Not Flap: g2d.drawImage(birddown, (int)x - ux, (int)y - yx , ux*2, yx*2, null);
        
	}
	
	private void drawBird(Graphics2D g2d, BufferedImage bird, int angle)
	{
		AffineTransform old = g2d.getTransform();
    	g2d.rotate(Math.toRadians(angle), x, y);
    	
    	g2d.drawImage(bird, (int) (x-ux), (int)y - uy , ux*2, uy*2, null);
		
		g2d.setTransform(old);
	}
	
	private void updatePhys()
	{
		if(Game.alive == true || Game.hitPipe == true)
		{
			//Change the bird's position
			y += veY;
			
			//Gravity
			veY += 1f;
			
			if(veY >= 16)
			{
				veY=16;
			}
			if(y >= (Floor.low-25))
			{
				y = Floor.low-25;
			}
		}
	}
	
	private void animateWing()
	{
		flapT = new TimerTask()
		{
			public void run()
			{
				//flap = !flap;
				if(Game.hitPipe == true)
				{
					flap = 1;
				}else
				{
					flap++;
					if(flap > 2)
					{
						flap = 0;
					}
				}
				
			}
		};
		
		wingFlap = new Timer("wing");
			
		wingFlap.scheduleAtFixedRate(flapT, 10L, 90L);
		
	}
	
	private void updateLook()
	{
		if(Game.started == true)
		{
			if(veY > (26/3))
			{
				lookUp = false;
			}else
			{
				lookUp = true;
			}
		}
		
				

	}
	
	private void updateAngle()
	{
		
		if(lookUp == false)
		{
			fallangle = (int) ((0.5 * (veY-10) *  30));	
		}
				
	}
	
	public void checkDeath()
	{
		if(Game.alive == true)
		{
			//Checks if the bird hit the ground
			if(y >= (Floor.low-25))
			{
				Sound.playSound(Sound.hit);
				y = Floor.low - 25;
				flap = 1;
				flapT.cancel();

				low = true;
				Game.end();
				Sound.playSound(Sound.swoosh);
			}
			
			//Check if it hits a pipe
			
			if(Pipes.checkCollision(x, y, ux * 2, uy * 2) == true)
			{
				//die
				Sound.playSound(Sound.hit);
				Sound.playSound(Sound.die);
				flap = 1;
				flapT.cancel();
				Game.hitPipe = true;
				Game.end();
				
				Sound.playSound(Sound.swoosh);
			}
		}
		
		
	}
	
	public void fly()
	{
		veY= -13f;
		Sound.playSound(Sound.wing);
	}
	
	public void start()
	{
		animateWing();
	}
	
	public void update()
	{
		this.updateLook();
		this.updateAngle();
		
		this.updatePhys();
		
		this.checkDeath();
		
	}
	
	
}
