package Controller;

import View.*;

import Model.*;


public class Main {
	public static PanelGame scene;  
	
	//private static Thread thread;
	/*private static final int FPS = 60;
	private static long targetTime = 1000 / FPS;
	
	public static void goLeft(Model.Entity entity) {
		entity.x = (int) ( entity.x - ( windowVariables.WIDTH/20 ) );
	}
	
	public static void goRight(Model.Entity entity) {
		entity.x = (int) ( entity.x + ( windowVariables.WIDTH/20 ) );
	}
	
	private static void init() {
		
	}
	
	private static void run() {
		init();
		
	}*/
	
	public static void main(String[] args) {
		//Frame output = new Frame();
		//windowVariables dataBase = new windowVariables(); 
		//output.setDataBase(dataBase);
		
		// on instancie la fenetre de jeu
		FrameGame fenetre_jeu = new FrameGame();
		// on instancie la scene du jeu 
		scene = new PanelGame();
		
		fenetre_jeu.setContentPane(scene); // on injecte la scene dans la fenetre du jeu
		fenetre_jeu.setVisible(true);
		
	}

}
