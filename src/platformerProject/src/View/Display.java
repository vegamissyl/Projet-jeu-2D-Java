package View;

import Controller.Main;

public class Display implements Runnable {
	
	private final int PAUSE = 3; //temps de pause entre 2 tours de boucle run
	@Override
	public void run() {
		while (true) { //on repaint le decor avec la methode repaint
			Main.scene.repaint(); //repaint appel la fonction paintcomponent de PanelGame
			
			try {
				Thread.sleep(PAUSE); //pause du thread de 3ms 
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
