package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import View.PanelGame;
import Controller.Main;

public class EntityPlayer extends Entity {
	
	private ImageIcon iconPerso;
	private Image imagePerso;
	
	public EntityPlayer(int _x, int _y) {
		super(_x, _y, (int)(View.PanelGame.getLongueur()*0.06), (int)(View.PanelGame.getLongueur()*0.13));
		
		iconPerso = new ImageIcon(getClass().getResource("/images/perso_immobile_d.png"));
		this.imagePerso = this.iconPerso.getImage();
	}

	
	//***Getters***///

	public Image getImagePerso() {return imagePerso;}

}
