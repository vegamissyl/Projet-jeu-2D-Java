package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import View.PanelGame;
import Controller.Main;

public class EntityPlayer extends Entity {
	
	private ImageIcon iconPerso;
	private Image imagePerso;
	private boolean saut;
	private int compteurSaut;
	
	public EntityPlayer(int _x, int _y) {
		super(_x, _y, 100, 208);
		
		iconPerso = new ImageIcon(getClass().getResource("/images/perso_immobile_d.png"));
		this.imagePerso = this.iconPerso.getImage();
		
		this.saut = false;
		this.compteurSaut = 0;
	}

	
	//***Getters***///

	public Image getImagePerso() {return imagePerso;}


	public boolean isSaut() {return saut;}

	//***Setters***//
	
	public void setSaut(boolean saut) {this.saut = saut;}
	
	//***Methods***//
	
	public Image saute() {
		ImageIcon ico;
		Image img;
		String src;
		this.compteurSaut++;
		
		if(this.compteurSaut <= 35) {
			if(this.getY() > View.PanelGame.getHauteurPlafond()) {this.setY(this.getY() - 20);}
			else {this.compteurSaut = 36;}
			if(this.isDirectionRight() == true) {src = "/images/perso_marche_d.png";}
			else {src = "/images/perso_marche_g.png";}
		}else if(this.getY() + this.getHeight() < View.PanelGame.getySol()) {this.setY(this.getY() + 1);
			if(this.isDirectionRight() == true) {src = "/images/perso_marche_d.png";}
			else {src = "/images/perso_marche_g.png";}
		}else {
			if(this.isDirectionRight() == true) {src = "/images/perso_marche_d.png";}
			else {src = "/images/perso_marche_g.png";}
			this.saut = false;
			this.compteurSaut = 0;
		}
		
		ico = new ImageIcon(getClass().getResource(src));
		img = ico.getImage();
		return img;
	}
}
