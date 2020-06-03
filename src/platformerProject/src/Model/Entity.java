package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.sun.tools.sjavac.server.SysInfo;

import Controller.Main;

public class Entity {
	//dimension du personnage (largeur et haiteur)
	private int width;
	private int height;
	
	//position du personnage
	private int x;
	private int y;
	
	private boolean walk; //true quand le personnage marche
	private boolean directionRight;//vrai quand le personnage est tourn� vers la droite
	public int counter; //compteur des pas du personnage
	
	//public static int life = 100;*/
	
	public Entity(int _x, int _y, int _width, int _height) {
		
		this.x = _x;
		this.y = _y;
		this.width = _width;
		this.height = _height;
		this.counter = 0;
		this.walk = false;
		this.directionRight = true;
		
	}
	
	///getters///
	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public int getX() {return x;}

	public int getY() {return y;}

	public boolean isWalk() {return walk;}

	public boolean isDirectionRight() {return directionRight;}

	public int getCounter() {return counter;}

	///setters///
	public void setWidth(int width) {this.width = width;}
	
	public void setHeight(int height) {this.height = height;}
	
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	
	public void setWalk(boolean walk) {this.walk = walk;}
	
	public void setDirectionRight(boolean directionRight) {this.directionRight = directionRight;}
	
	public void setCounter(int counter) {this.counter = counter;}
	
	///methodes///
	public Image walk(String nom, int frequence) { //nom du personnage et fr�quence pour laquelle il marche
		String str;
		ImageIcon ico;
		Image img;
		
		if(this.walk == false || Main.scene.getxPos()<=0) {//si personnage arr�t� ou coller au panneau
			if(this.directionRight == true) {
				str = "/images/" + nom + "_immobile_d.png";
			}
			else {
				str = "/images/" + nom + "_immobile_g.png";
			}
		
		}
		
		else { //le personnage se d�place
			this.counter++; //on incr�mente le compteur � chaque fois que la scene est redessin�
			if((int)(this.counter / frequence)== 0) { //le personnage est � l'arr�t dans ce cas
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
			
			if(this.counter == 3*frequence) { //si le compteur vaut 2 fois la fr�quence, on le remet � 0
				this.counter = 0;
			}
			
		}
		
		//Affichage de l'image du personnage
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img; 
	}
	
}