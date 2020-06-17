package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import View.PanelGame;
import Controller.Main;

public class EntityPlayer extends Entity {
	
	private ImageIcon iconPerso;
	private Image imagePerso;
	private ImageIcon iconVies;
	private Image imageVies;
	private boolean saut;
	private int compteurSaut;
	private int vies;
	private int compteurMort;
	private boolean invincible;
	
	public EntityPlayer(int _x, int _y) {
		super(_x, _y, 100, 208);
		this.vies = 3;
		this.compteurMort = 0;
		this.invincible = false;
		iconVies = new ImageIcon(getClass().getResource("/images/3_coeurs.png"));
		this.imageVies = this.iconVies.getImage();
		
		
		iconPerso = new ImageIcon(getClass().getResource("/images/perso_immobile_d.png"));
		this.imagePerso = this.iconPerso.getImage();
		
		this.saut = false;
		this.compteurSaut = 0;
	}

	
	//***Getters***///

	public Image getImagePerso() {return imagePerso;}

	public Image getImageVies() {return imageVies;}
	
	public boolean isSaut() {return saut;}
	
	public void setSaut(boolean saut) {this.saut = saut;}
	
	public int getVies() {return vies;}

	public void setVies(int vies) {
		this.vies = vies;
		if(this.vies == 2) {
			this.iconVies = new ImageIcon(getClass().getResource("/images/2_coeurs.png"));
			this.imageVies = this.iconVies.getImage();
		} else {
			this.iconVies = new ImageIcon(getClass().getResource("/images/coeur.png"));
			this.imageVies = this.iconVies.getImage();
		}
	}
	
	public int getCompteurMort() {return compteurMort;}
	public void setCompteurMort(int compteurMort) {	this.compteurMort = compteurMort;}
	
	public boolean isInvincible() {return invincible;}
	public void setInvincible(boolean invincible) {this.invincible = invincible;}

	
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
		}else if(this.getY() + this.getHeight() < View.PanelGame.getySol()) {this.setY(this.getY() + 2);
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
