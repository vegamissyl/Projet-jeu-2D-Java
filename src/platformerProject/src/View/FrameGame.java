package View;
import javax.swing.JFrame;

import Model.windowVariables;
public class FrameGame extends JFrame {
	windowVariables dataBase;
	
	public FrameGame() {
		super();
		this.setTitle("Platformer Project"); //titre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de pouvoir fermer la fenêtre
		this.setSize(1420, 800); //taille
		this.setLocationRelativeTo(null); //centrer à l'écran
		//this.setExtendedState(this.MAXIMIZED_BOTH);// taille max
		//this.setResizable(false); // interdit de la redimensionner
		this.setAlwaysOnTop(true); // fenêtre devant les autres
		
		
	}
	
	public void setDataBase(windowVariables dataBase) {
		this.dataBase = dataBase;
	}
}
