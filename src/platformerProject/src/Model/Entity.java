package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

// import com.sun.tools.sjavac.server.SysInfo;

import Controller.Main;

public class Entity {
	//dimension de l'objet (largeur et haiteur)
	private int width;
	private int height;
	
	//position de l'objet
	private int x;
	private int y;
	
	private boolean walk; //true quand le personnage marche
	private boolean directionRight;//vrai quand le personnage est tourné vers la droite
	public int counter; //compteur des pas du personnage
	
	
	// Constructeur
	public Entity(int _x, int _y, int _width, int _height) {
		
		this.x = _x;
		this.y = _y;
		this.width = _width;
		this.height = _height;
		this.counter = 0;
		this.walk = false;
		this.directionRight = true;
		
	}
	
	//getters//
	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public int getX() {return x;}

	public int getY() {return y;}

	public boolean isWalk() {return walk;}

	public boolean isDirectionRight() {return directionRight;}

	public int getCounter() {return counter;}

	//setters//
	public void setWidth(int width) {this.width = width;}
	
	public void setHeight(int height) {this.height = height;}
	
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	
	public void setWalk(boolean walk) {this.walk = walk;}
	
	public void setDirectionRight(boolean directionRight) {this.directionRight = directionRight;}
	
	public void setCounter(int counter) {this.counter = counter;}
	
	//methodes//
	public Image walk(String nom, int frequence) { //nom du personnage et fréquence pour laquelle il marche
		String str;
		ImageIcon ico;
		Image img;
		
		if(this.walk == false || Main.scene.getxPos()<=0) {//si personnage arrêté ou coller au panneau
			if(this.directionRight == true) {
				str = "/images/" + nom + "_immobile_d.png";
			}
			else {
				str = "/images/" + nom + "_immobile_g.png";
			}
		
		}
		
		else { //le personnage se déplace
			this.counter++; //on incrémente le compteur à chaque fois que la scene est redessiné
			if((int)(this.counter / frequence)== 0) { //le personnage est à l'arrêt dans ce cas
				if(this.directionRight == true) {
					str = "/images/" + nom + "_immobile_d.png";
				}
				
				else {
					str = "/images/" + nom + "_immobile_g.png";
				}
			}
			else {
				if(this.directionRight == true) {
					if(this.counter/frequence == 1)
						str = "/images/" + nom + "_marche_d2.png";
					else {
						str = "/images/" + nom + "_marche_d.png";
					}
				}
				
				else { 
					if(this.counter/frequence == 1) {
						str = "/images/" + nom + "_marche_g2.png";
					}
					else {
						str = "/images/" + nom + "_marche_g.png";
					}
						
					}
			}
			
			if(this.counter == 3*frequence) { //si le compteur vaut 2 fois la fréquence, on le remet à 0
				this.counter = 0;
			}
			
		}
		
		//Affichage de l'image du personnage
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img; 
	}
	
	// Detection d'un contact sur la droite
	public boolean contactAvant(Object objet) {
		if (this.x + this.width < objet.getX() || this.x + this.width > objet.getX() + 5 || this.y + this.height <= objet.getY() || this.y >= objet.getY() + objet.getHeight()) {
			return false;
		} else {
			return true;
		}
	}
	// Detection d'un contact sur la gauche
	public boolean contactArriere(Object objet) {
		if (this.x > objet.getX() + objet.getWidth() || this.x + this.width > objet.getX() + objet.getWidth() + objet.getWidth() ||
				this.y + this.height <= objet.getY() || this.y >= objet.getY() + objet.getHeight()) {
			return false;
		} else {
			return true;
		}
	}
	
	// Detection d'un contact sous le personnage
	public boolean contactDessous(Object objet) {
		if (this.x  > objet.getX() + objet.getWidth() || this.x  > objet.getX() + objet.getWidth() - 5||
				this.y + this.height < objet.getY() || this.y + this.height > objet.getY() + 5 ) {
			return false;
		} else {
			return true;
		}
	}
	
	// Détection d'un contact au dessus du personnage
	public boolean contactDessus(Object objet) {
		if (this.x + this.width < objet.getX() + 5  || this.x  > objet.getX() + objet.getWidth() - 5 ||
				this.y < objet.getY() + objet.getHeight() || this.y > objet.getY() + objet.getHeight() + 5) {
			return false;
		} else {
			return true;
		}
	}
	
	// Détection de la proximité du personnage avec un objet
	public boolean proche(Object objet) {
		if ((this.x > objet.getX() - 10 && this.x < objet.getX() + objet.getWidth() + 10) ||
				(this.x + this.width > objet.getX() - 10 && this.x + this.width < objet.getX() + objet.getWidth() + 10)) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
