package Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class windowVariables {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static double WIDTH = 1920/*screenSize.getWidth()*/;
	public static double HEIGHT = 1080/*screenSize.getHeight()*/;
	
	public static boolean start;
	
	public static ArrayList keyPressed = new ArrayList<Character>(); 
	public static ArrayList entities = new ArrayList<Model.Entity>();
	
}
