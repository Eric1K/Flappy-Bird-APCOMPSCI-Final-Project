import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Frame extends JFrame{

	private static final long serialVersionUID = 6588577019334806811L;
	public final static int WIDTH = 576;
	public final static int HEIGHT = 1024;
	
	public Frame()
	{
		JFrame frame = new JFrame("Flappy Bird");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("Log: Frame created");
		
		JPanel Panel = new Panel();
		
		 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/res/icon.png")));
		
		frame.getContentPane().add(Panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		setTheme();
		new Frame();
	}
	
	private static void setTheme()
	{
		try {  
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			Game.showError(e.toString());
		}
		catch (ClassNotFoundException e) {
			Game.showError(e.toString());
		}
    	catch (InstantiationException e) {
    		Game.showError(e.toString());
    	}
		catch (IllegalAccessException e) {
			Game.showError(e.toString());
		}
	}
}
