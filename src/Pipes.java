import java.awt.Graphics2D;

public class Pipes {
	
	public Pipe[] p;
	static Pipe[] ps;
	public Pipes()
	{
		p = new Pipe[3];
		ps = new Pipe[3];
		createPipes();
		System.out.println("Log: Pipes initialized");
	}
	
	
	
	
	private void createPipes()
	{
		//354 diff 3/4 * (576 - (52 * 2))
		p[0] = new Pipe(930);
		p[1] = new Pipe(1284);
		p[2] = new Pipe(1638);
	}
	
	public void render(Graphics2D g)
	{
		for(int i =0; i < p.length; i++)
		{
			p[i].render(g);
		}
	}
	
	public void update()
	{
		for(int i = 0; i < p.length; i++)
		{
			p[i].update();
			ps[i] = p[i];
		}
		
	}
	
	public static boolean checkCollision(float x, float y, int ux, int uy)
	{
		float px,py;
		
		@SuppressWarnings("unused")
		int pux,puy;
		for(int i =0; i < ps.length; i++)
		{
			px = ps[i].x;
			py = ps[i].y;
			pux = ps[i].ux;
			puy = ps[i].uy;
			
			//Check X boundaries, the first one checks left of end pipe and the second one checks right of front pipe
			if(x <= (px + (pux*2)) == true && x >= (px - (pux/2)) == true)
			{

				//Check Y boundaries
				//Top pipe
				//g.drawLine((int)(px + (pux*2)), Frame.HEIGHT/2, (int) (px - (pux/2)) , Frame.HEIGHT/2);
				
				if(y < (py - (Pipe.difference / 2) + (uy / 2)) == true)
				{

					return true;
				}
			
				//Lower pipe	
				if(y > (py + (Pipe.difference / 2) - (uy / 2)) == true)
				{
					
					return true;
				}
				
				
			}
			//Debug boundaries
			//g.drawLine((int) (px - (pux/2)), (int) (py + (Pipe.difference / 2) - (0.5 * uy)),(int) (px - (pux/2)), Frame.HEIGHT);
			//g.drawLine((int) (px - (pux/2)), (int) (py - (Pipe.difference / 2) + (uy / 2)),(int) (px - (pux/2)), 0);
		}
		
		return false;
	}
	
}
