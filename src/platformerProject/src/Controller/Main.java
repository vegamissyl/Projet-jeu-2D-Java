package Controller;

import View.*;

import Model.*;


public class Main {
	public static PanelGame scene;  
	
	public static void main(String[] args) {
		
		// on instancie la fenetre de jeu
		FrameGame fenetre_jeu = new FrameGame();
		// on instancie la scene du jeu 
		scene = new PanelGame();
		
		fenetre_jeu.setContentPane(scene); // on injecte la scene dans la fenetre du jeu
		fenetre_jeu.setVisible(true);
		
	}

}
