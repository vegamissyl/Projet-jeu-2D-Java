package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import View.PanelGame;
import Controller.Main;

public class EntityPlayer extends Entity {
	
	// Image du personnage
	private ImageIcon iconPerso;
	private Image imagePerso;
	// Image de la barre de vie
	private ImageIcon iconVies;
	private Image imageVies;
	private boolean saut;  // Etat du personnage sur le saut vrai/faux
	private int compteurSaut; // Compteur de l'ascension du personnage
	private int vies; // Compteur du nombre de vie
	private int compteurMort; // Compteur permettant de temporiser les pertes de coeur
	private boolean invincible; // Booleen permettant de temporiser aussi les pertes de coeur
	private boolean mort;
	private boolean victoire;
	
	// Constructeur
	public EntityPlayer(int _x, int _y) {
		super(_x, _y, 100, 208); //superconstructeur de la classe Entity
		this.vies = 3;
		this.compteurMort = 0;
		this.mort = false;
		this.victoire = false;
		this.invincible = false;
		iconVies = new ImageIcon(getClass().getResource("/images/3_coeurs.png"));
		this.imageVies = this.iconVies.getImage();
		
		
		iconPerso = new ImageIcon(getClass().getResource("/images/perso_immobile_d.png"));
		this.imagePerso = this.iconPerso.getImage();
		
		this.saut = false;
		this.compteurSaut = 0;
	}

	
	// Getters et Setters

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
		} else if(this.vies == 1) {
			this.iconVies = new ImageIcon(getClass().getResource("/images/coeur.png"));
			this.imageVies = this.iconVies.getImage();
		 }
		else {
			this.setMort(true);
		}
	}
	
	public int getCompteurMort() {return compteurMort;}
	public void setCompteurMort(int compteurMort) {	this.compteurMort = compteurMort;}
	
	public boolean isInvincible() {return invincible;}
	public void setInvincible(boolean invincible) {this.invincible = invincible;}

	public boolean isMort() {return mort;}
	public void setMort(boolean mort) {this.mort = mort;}
	
	public boolean isVictoire() {return victoire;}
	public void setVictoire(boolean victoire) {this.victoire = victoire;}
	
	//***Methods***//

	// Methode qui gère la saut du personnage
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
		}else if(this.getY() + this.getHeight() < View.PanelGame.getySol()) {this.setY(this.getY() + 4);
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
