import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Score {

	BufferedImage[] nums;
	
	private ArrayList<Integer> ascore;
	static ArrayList<String> data;
	static ArrayList<Integer> hscore;
	/* ABOUT DATA:
	 * Line 1: Highscore
	 * 
	 */
	Scanner sc;
	static PrintWriter pw;
	private int score;
	static int highscore;
	
	public Score()
	{
		nums = new BufferedImage[10];
		
		
		try {
			nums[0] = ImageIO.read(new File("res/sprites/0.png"));
			nums[1] = ImageIO.read(new File("res/sprites/1.png"));
			nums[2] = ImageIO.read(new File("res/sprites/2.png"));
			nums[3] = ImageIO.read(new File("res/sprites/3.png"));
			nums[4] = ImageIO.read(new File("res/sprites/4.png"));
			nums[5] = ImageIO.read(new File("res/sprites/5.png"));
			nums[6] = ImageIO.read(new File("res/sprites/6.png"));
			nums[7] = ImageIO.read(new File("res/sprites/7.png"));
			nums[8] = ImageIO.read(new File("res/sprites/8.png"));
			nums[9] = ImageIO.read(new File("res/sprites/9.png"));
			
			sc = new Scanner(new File("res/data.ini"));
			
		} catch (IOException e) {
			Game.showError(e.toString() + " Check the res folder for missing or corrupted files.");
		}
		
		ascore = new ArrayList<Integer>();
		data = new ArrayList<String>();
		hscore = new ArrayList<Integer>();
		
		score = Game.score;
		ascore.add(score);
		
		getData();
		
		System.out.println("Log: Score initialized");
	}
	
	private void getData()
	{
		int count = 0;
		while(sc.hasNext())
		{
			String temp = sc.nextLine();
			data.add(temp.toString());
			if(count == 0)
			{
				highscore = Integer.parseInt(temp);
			}
			count++;
		}
		
		
	}
	
	public static void writeData()
	{
		try {
			pw = new PrintWriter(new File("res/data.ini"));
		} catch (FileNotFoundException e) {
			Game.showError(e.getMessage());
		}
		for(int i = 0; i < data.size(); i++)
		{
			pw.println(data.get(i));
			
		}
		pw.close();
	}
	public void update()
	{
		int temp = Game.score;
		if(temp > score == true)
		{
			score = temp;
			String str = Integer.toString(temp);
			
			ascore.clear();
			for(int i = 0; i < str.length(); i++)
			{
				ascore.add(Integer.parseInt(String.valueOf(str.charAt(i))));
			}
			//System.out.println(ascore);
			
		}
		
		String temp2 = Integer.toString(highscore);
		hscore.clear();
		for(int i = 0; i < temp2.length(); i++)
		{
			hscore.add(Integer.parseInt(String.valueOf(temp2.charAt(i))));
		}
		

	}
	
	//Render the score
	public void render(Graphics2D g)
	{
		//g.drawImage(nums[score], Frame.WIDTH / 2 - nums[score].getWidth(), Frame.HEIGHT / 10 - nums[score].getHeight(), 2 * nums[score].getWidth(), 2 * nums[score].getHeight(), null);
		if(Game.started == true && Game.alive == true)
		{
			renderScore(g);
		}
		
		if(Game.alive == false && Game.started == true)
		{
			renderHighscore(g);
			renderFinalScore(g);
		}
		//g.setColor(Color.red);
		//g.drawLine(Frame.WIDTH/2, Frame.HEIGHT, Frame.WIDTH/2, 0);
				
	}
	
	public void restart()
	{
		score = 0;
		ascore.clear();
	}
	private void renderScore(Graphics2D g)
	{
		int side = ascore.size()/2;
		if(ascore.size() % 2 == 0)
		{
			//SCORE IS EVEN LENGTH
			for(int i = 0; i < ascore.size(); i++)
			{
				int temp = ascore.size()-1-i;
				BufferedImage even = nums[ascore.get(temp)];
				
				if(ascore.get(temp) == 1)
				{
					if(temp < ascore.size() / 2)
					{
						//right side of middle
						g.drawImage(even, (int) ((Frame.WIDTH/2-32) + (48 * (side-i))), Frame.HEIGHT / 10 - even.getHeight(), 2 * even.getWidth(), 2 *even.getHeight(), null);
					}else
					{
						//left side of middle
						g.drawImage(even, (int) ((Frame.WIDTH/2-32) + (-16  + 48 * (side-i))), Frame.HEIGHT / 10 - even.getHeight(), 2 * even.getWidth(), 2 * even.getHeight(), null);
					}
					
				}else
				{
					g.drawImage(even, (int) ((Frame.WIDTH/2-48) + (48 * (side-i))), Frame.HEIGHT / 10 - even.getHeight(), 2 * even.getWidth(), 2 * even.getHeight(), null);
				}
				
				
			}
			
		}else if(ascore.size() == 1)
		{
			//Draw single digit
			for(int i = 0; i < ascore.size(); i++)
			{
				BufferedImage num = nums[ascore.get(i)];
				g.drawImage(num, (int) (Frame.WIDTH/2) - num.getWidth(), Frame.HEIGHT / 10 - num.getHeight(), 2 * num.getWidth(), 2 * num.getHeight(), null);
			}
		}else
		{
			//SCORE IS ODD LENGTH
			int middle = ascore.size() / 2 + 1;
			
			
			
			for(int i = 0; i < ascore.size(); i++)
			{
				int temp = ascore.size()-1-i;
				BufferedImage odd = nums[ascore.get(temp)];
				
				if(i == middle - 1)
				{
					//Draw middle digit
					BufferedImage mid = nums[ascore.get(middle-1)];
					g.drawImage(mid, (int) (Frame.WIDTH/2) - mid.getWidth(), Frame.HEIGHT / 10 - mid.getHeight(), 2 * mid.getWidth(), 2 * mid.getHeight(), null);
				}else
				{
					if(ascore.get(temp) == 1)
					{
						if(temp < ascore.size() / 2)
						{
							//right side of middle
							g.drawImage(odd, (int) ((Frame.WIDTH/2-32) + (24 + 48 * (side-i))), Frame.HEIGHT / 10 - odd.getHeight(), 2 * odd.getWidth(), 2 * odd.getHeight(), null);
						}else
						{
							//left side of middle
							g.drawImage(odd, (int) ((Frame.WIDTH/2-32) + (8 + 48 * (side-i))), Frame.HEIGHT / 10 - odd.getHeight(), 2 * odd.getWidth(), 2 * odd.getHeight(), null);
						}
						
						
					}else
					{
						g.drawImage(odd, (int) ((Frame.WIDTH/2-48) + (24 + 48 * (side-i))), Frame.HEIGHT / 10 - odd.getHeight(), 2 * odd.getWidth(), 2 * odd.getHeight(), null);
					}
				}
				
				
				
			}
		}
	}
	private void renderHighscore(Graphics2D g)
	{
		int side = hscore.size()/2;
		if(hscore.size() % 2 == 0)
		{
			//SCORE IS EVEN LENGTH
			for(int i = 0; i < hscore.size(); i++)
			{
				int temp = hscore.size()-1-i;
				BufferedImage even = nums[hscore.get(temp)];
				
				if(hscore.get(temp) == 1)
				{
					if(temp < hscore.size() / 2)
					{
						//right side of middle
						g.drawImage(even, (int) ((Frame.WIDTH*4/5-24) + (24 * (side-i))), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
					}else
					{
						//left side of middle
						g.drawImage(even, (int) ((Frame.WIDTH*4/5-16) + (-8  + 24 * (side-i))), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
					}
					
				}else
				{
					g.drawImage(even, (int) ((Frame.WIDTH*4/5-32) + (24 * (side-i))), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
				}
				
				
			}
			
		}else if(hscore.size() == 1)
		{
			//Draw single digit
			for(int i = 0; i < hscore.size(); i++)
			{
				BufferedImage num = nums[hscore.get(i)];
				g.drawImage(num, (int) (Frame.WIDTH*4/5) - num.getWidth(),(int) (Frame.HEIGHT / 2.2) - (int) (0.5 * num.getHeight()), num.getWidth(), num.getHeight(), null);
			}
		}else
		{
			//SCORE IS ODD LENGTH
			int middle = hscore.size() / 2 + 1;
			
			
			
			for(int i = 0; i < hscore.size(); i++)
			{
				int temp = hscore.size()-1-i;
				BufferedImage odd = nums[hscore.get(temp)];
				
				if(i == middle - 1)
				{
					//Draw middle digit
					BufferedImage mid = nums[hscore.get(middle-1)];
					g.drawImage(mid, (int) ((Frame.WIDTH*4/5) - mid.getWidth()), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * odd.getHeight()), mid.getWidth(),  mid.getHeight(), null);
				}else
				{
					if(hscore.get(temp) == 1)
					{
						if(temp < hscore.size() / 2)
						{
							//right side of middle
							g.drawImage(odd, (int) ((Frame.WIDTH*4/5) + (24 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
						}else
						{
							//left side of middle
							g.drawImage(odd, (int) (((Frame.WIDTH*4/5)-16) + (8 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.2) - (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
						}
						
						
					}else
					{
						g.drawImage(odd, (int) (((Frame.WIDTH*4/5)-48) + (24 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.2)- (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
					}
				}
				
				
				
			}
		}
	}
	
	
	private void renderFinalScore(Graphics2D g)
	{
		int side = ascore.size()/2;
		if(ascore.size() % 2 == 0)
		{
			//SCORE IS EVEN LENGTH
			for(int i = 0; i < ascore.size(); i++)
			{
				int temp = ascore.size()-1-i;
				BufferedImage even = nums[ascore.get(temp)];
				
				if(ascore.get(temp) == 1)
				{
					if(temp < ascore.size() / 2)
					{
						//right side of middle
						g.drawImage(even, (int) ((Frame.WIDTH*4/5-24) + (24 * (side-i))), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
					}else
					{
						//left side of middle
						g.drawImage(even, (int) ((Frame.WIDTH*4/5-16) + (-8  + 24 * (side-i))), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
					}
					
				}else
				{
					g.drawImage(even, (int) ((Frame.WIDTH*4/5-32) + (24 * (side-i))), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * even.getHeight()), even.getWidth(), even.getHeight(), null);
				}
				
				
			}
			
		}else if(ascore.size() == 1)
		{
			//Draw single digit
			for(int i = 0; i < ascore.size(); i++)
			{
				BufferedImage num = nums[ascore.get(i)];
				g.drawImage(num, (int) (Frame.WIDTH*4/5) - num.getWidth(),(int) (Frame.HEIGHT / 2.65) - (int) (0.5 * num.getHeight()), num.getWidth(), num.getHeight(), null);
			}
		}else
		{
			//SCORE IS ODD LENGTH
			int middle = ascore.size() / 2 + 1;
			
			
			
			for(int i = 0; i < ascore.size(); i++)
			{
				int temp = ascore.size()-1-i;
				BufferedImage odd = nums[ascore.get(temp)];
				
				if(i == middle - 1)
				{
					//Draw middle digit
					BufferedImage mid = nums[ascore.get(middle-1)];
					g.drawImage(mid, (int) ((Frame.WIDTH*4/5) - mid.getWidth()), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * odd.getHeight()), mid.getWidth(),  mid.getHeight(), null);
				}else
				{
					if(ascore.get(temp) == 1)
					{
						if(temp < ascore.size() / 2)
						{
							//right side of middle
							g.drawImage(odd, (int) ((Frame.WIDTH*4/5) + (24 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
						}else
						{
							//left side of middle
							g.drawImage(odd, (int) (((Frame.WIDTH*4/5)-16) + (8 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.65) - (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
						}
						
						
					}else
					{
						g.drawImage(odd, (int) (((Frame.WIDTH*4/5)-48) + (24 + 24 * (side-i))), (int) (Frame.HEIGHT / 2.65)- (int) (0.5 * odd.getHeight()), odd.getWidth(), odd.getHeight(), null);
					}
				}
				
				
				
			}
		}
	}
}
	
	

