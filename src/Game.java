import javax.swing.JOptionPane;

public class Game {
	static boolean alive;
	static boolean started = false;
	static int score = 0;
	static boolean running;
	static boolean hitPipe;
	static String medal;
	
	final static int moveConstant = 4;
	public Game()
	{
		hitPipe = false;
		running = true;
		started = false;
		alive = false;
		medal = "";
		score = 0;
	}

	public void startGame()
	{
		alive = true;
		started = true;
		score = 0;
		System.out.println("Log: Game has been started!");
	}
	
	public static void end()
	{
		alive = false;
		if(score > Score.highscore == true)
		{
			Score.highscore = score;
			Score.data.set(0, Integer.toString(Score.highscore)); //writes highscore on line 0
		}
		
		Score.writeData();
		
		if(score >= 10 == true && score < 20 == true)
		{
			medal = "bronze";
		}else if(score >= 20 == true && score < 30 == true)
		{
			medal = "silver";
		}else if(score >= 30 == true && score < 40 == true)
		{
			medal = "gold";
		}else if(score >= 40 == true)
		{
			medal = "platinum";
		}
	}
	

	public static void showError(String text)
	{
		JOptionPane.showMessageDialog(null, text, "Flappy Bird: Error", JOptionPane.ERROR_MESSAGE);
	}
}
