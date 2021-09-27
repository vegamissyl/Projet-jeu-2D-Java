package View;


import Model.Bloc;
import Model.Drapeau;
import Model.EntityPlayer;
import Model.Object;
import Model.Pic;
import Model.Plateforme;

import javax.swing.JPanel;

import Controller.Main;

// import com.sun.tools.sjavac.server.SysInfo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

public class PanelGame extends JPanel {
	private ImageIcon iconFond;
	private Image imageFond;
	private Image imageFond2;
	
	private ImageIcon iconPanneau;
	private Image imagePanneau;
	
	
	private int xFond;// variable : abscisse supérieur gauche de l'image de fond par rapport à l'ecran
	private int xFond2;
	private static int dx; // variable permettant de deplacer l'ecran (decors) horizontalement
	private static int xPos; //variable qui va repérer les éléments du jeu moins leur position en x = position absolu dans le jeu
	private static int ySol; // hauteur du sol
	private static int hauteurPlafond; // hauteur du plafond du jeu
	private  static int  longueur; // longueur de la fenêtre de jeu
	private  static int hauteur; // hauteur de la fenêtre de jeu
	
	// Declaration de l'objet Drapeau de fin du jeu
	public Drapeau drapeau;
	
	// Declaration des objets Pic du jeu
	public Pic pic1;
	public Pic pic2;
	public Pic pic3;
	public Pic pic4;
	public Pic pic5;
	public Pic pic6;
	public Pic pic7;
	public Pic pic8;
	public Pic pic9;
	public Pic pic10;
	
	
	// Declaration des objects blocs du jeu
	public Bloc bloc1;
	public Bloc bloc2;
	public Bloc bloc3;
	public Bloc bloc4;
	public Bloc bloc5;
	public Plateforme plateforme1;
	public static EntityPlayer player; //joueur
	
	
	public PanelGame() {
		super();
		PanelGame.longueur = 1420; //longueur de l'écran de l'ordinateur
		PanelGame.hauteur = 800 ; //hauteur de l'écran d'ordinateur
		this.xFond = 0; //pour que l'image de fond déborde de chaque côté de l'écran
		this.xFond2 = 1420;
		
		this.dx = 0;
		this.xPos = -1;
		this.ySol = 690;
		this.hauteurPlafond = 0;
		
		// Initialisation des images de fond de jeu et du panneau de départ
		iconFond = new ImageIcon(getClass().getResource("/images/fond.png"));
		this.imageFond = this.iconFond.getImage();
		this.imageFond2 = this.iconFond.getImage();
		
		iconPanneau = new ImageIcon(getClass().getResource("/images/panneau_fleche.png"));
		this.imagePanneau = this.iconPanneau.getImage();
		
		// Initialisation des objets du jeu en fonction de leurs coordonnées + un rectangle représentant leur hitbox
		drapeau = new Drapeau(4300,250,370,675);
		
		pic1 = new Pic(1300,620 , 117 ,63);
		pic2 = new Pic(1930,620 , 117 ,63);
		pic3 = new Pic(2030,620 , 117 ,63);
		pic4 = new Pic(2130,620 , 117 ,63);
		pic6 = new Pic(3000,620 , 117 ,63);
		pic7 = new Pic(3120,620 , 117 ,63);
		pic9 = new Pic(3540,620 , 117 ,63);
		pic10 = new Pic(3660,620 , 117 ,63);
		bloc1 = new Bloc(1000,595,85,85);
		bloc2 = new Bloc(1600,595,85,85);
		bloc3 = new Bloc(1800,385,85,85);
		bloc4 = new Bloc(3410,385,85,85);
		bloc5 = new Bloc(3880,385,85,85);
		plateforme1 = new Plateforme(2400, 330, 480,65);
		player = new EntityPlayer(300, 480);
		
		
		this.setFocusable(true); //mettre le focus pour écouter l'écran
		this.requestFocusInWindow(); //pour être sur de récupérer le focus
		this.addKeyListener(new Input()); //on ajoute à la scene les évènements clavier avec la classe input 
		
		Thread DisplayScreen = new Thread(new Display()); //on creer un thread sur l affichage de l'écran de décor
		DisplayScreen.start(); //on lance le thread d'affichage de l'écran
	}
	
	/*getters and setters*/
	
	
	public int getxFond() {return xFond;}
	public static  int getLongueur() {return longueur;}
	public void setxFond(int xFond) {this.xFond = xFond;}
	public int getxFond2() {return xFond2;}
	public void setxFond2(int xFond2) {this.xFond2 = xFond2;}
	public static int getxPos() {return xPos;}
	public void setxPos(int xPos) {this.xPos = xPos;}

	public static int getDx() {return dx;}
	public void setDx(int dx) {this.dx = dx;}
	public static int getySol() {return ySol;}
	public void setySol(int ySol) {this.ySol = ySol;}
	public static int getHauteurPlafond() {return hauteurPlafond;}
	public void setHauteurPlafond(int hauteurPlafond) {this.hauteurPlafond = hauteurPlafond;}

	//***Methodes***//
	
	public void moveScreen() { //met à jour la position du fond d'écran
		
		if(this.xPos >= 0) {
			this.xPos = this.xPos + this.dx;
		
			this.xFond = this.xFond - this.dx;
			this.xFond2 = this.xFond2 - this.dx;
		} //si xpos <0 LE PERSONNAGE ne peut pas bouger, il est bloquer par le panneau, il doit aller à droite
		
		///lorsque le personnage se deplace à droite
		if(this.xFond <= -1420) { //si le 1er fond est passé il va à la suite du 2e fond
			this.xFond = 1420;
		}
		else if(this.xFond2 <= -1420) { //si le 2e fond est passé on le met à la suite du 1er fond
			this.xFond2 = 1420;
		}
		///de même lorsque le personnage se deplace à gauche
		else if(this.xFond >= 1420) { 
			this.xFond = -1420;
		}
		else if(this.xFond2 >= 1420) { 
			this.xFond2 = -1420;
		}
	}
	
	// Fonction qui va gérer le contact entre le personnage et un objet du jeu
	public void contact(Object objet) {
		
		// Contact horizontal à gauche ou à droite
		if((this.player.contactAvant(objet) == true && player.isDirectionRight() == true) || (player.contactArriere(objet) == true && player.isDirectionRight() == false)) {
			if(objet.getClass().getName() == "Model.Pic") {
				this.player.setCompteurMort(this.player.getCompteurMort()+1);
				if(this.player.getCompteurMort() % 100 == 0) {
					this.player.setVies(this.player.getVies()-1);
				}
			}else if(objet.getClass().getName() == "Model.Drapeau") {this.player.setVictoire(true);}
			this.setDx(0);
			this.player.setWalk(false);
		}
	
		// Contact sous le personnage
		if(this.player.contactDessous(objet) == true && this.player.isSaut() == true) {
			if(objet.getClass().getName() == "Model.Pic") {
				if(this.player.isInvincible() == false) {
					this.player.setVies(this.player.getVies()-1);
					this.player.setInvincible(true);
				} else {
					this.player.setInvincible(false);
				}	
			}
			this.setySol(objet.getY());
			
		}else if(this.player.contactDessous(objet) == false) {
			this.setySol(690);
			if(this.player.isSaut() == false) {this.player.setY(480);}
		}
		
		//Contact au dessus du personnage
		if(this.player.contactDessus(objet) == true) {
			this.setHauteurPlafond(objet.getY() + objet.getHeight());
		}else if(this.player.contactDessus(objet) == false && this.player.isSaut() == false) {
			this.setHauteurPlafond(0);
		}
	}

	// Methode qui va vérifier si le personnage est mort ou si le jeu est fini
	public void finjeu() {
		if(this.player.isMort() == true) {
			Main.fenetre_menu.setVisible(true);
			MenuFrame.fenetre_jeu.dispose();
			this.player.setMort(false);
		}else if(this.player.isVictoire() == true) {
			Main.fenetre_menu.setVisible(true);
			MenuFrame.fenetre_jeu.dispose();
			this.player.setVictoire(false);
		}
	}
	// Fonction qui va repeindre le jeu et les objets
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; //améliore les graphismes
		
		// Vérification du contact avec chaque objet
		if(this.player.proche(pic1)) {this.contact(pic1);}
		if(this.player.proche(pic2)) {this.contact(pic2);}
		if(this.player.proche(pic3)) {this.contact(pic3);}
		if(this.player.proche(pic4)) {this.contact(pic4);}
		if(this.player.proche(pic6)) {this.contact(pic6);}
		if(this.player.proche(pic7)) {this.contact(pic7);}
		if(this.player.proche(pic9)) {this.contact(pic9);}
		if(this.player.proche(pic10)) {this.contact(pic10);}
		if(this.player.proche(bloc1)) {this.contact(bloc1);}
		if(this.player.proche(bloc2)) {this.contact(bloc2);}
		if(this.player.proche(bloc3)) {this.contact(bloc3);}
		if(this.player.proche(bloc4)) {this.contact(bloc4);}
		if(this.player.proche(bloc5)) {this.contact(bloc5);}
		if(this.player.proche(plateforme1)) {this.contact(plateforme1);}
		if(this.player.proche(drapeau)) {this.contact(drapeau);}
	
		this.moveScreen(); //on appel la fonction pour deplacer l'écran à chaque boucle du run du thread
		
		// On gère le déplacement des objets par rapport au personnage
		this.drapeau.mouvement();
		this.pic1.mouvement();
		this.pic2.mouvement();
		this.pic3.mouvement();
		this.pic4.mouvement();
		this.pic6.mouvement();
		this.pic7.mouvement();
		this.pic9.mouvement();
		this.pic10.mouvement();
		this.bloc1.mouvement();
		this.bloc2.mouvement();
		this.bloc3.mouvement();
		this.bloc4.mouvement();
		this.bloc5.mouvement();
		this.plateforme1.mouvement();
		
		this.finjeu();
		
		//Dessin de toutes les entités présentes dans le jeu
		
		g2.drawImage(this.imageFond, this.xFond,0,null); //dessin image du fond
		g2.drawImage(this.imageFond2, this.xFond2,0,null);
		
		g2.drawImage(this.imagePanneau, 300-this.xPos,280,null);//on fixe le panneau au même endroit
		g2.drawImage(this.drapeau.getImageDrapeau(),this.drapeau.getX(),this.drapeau.getY(),this.drapeau.getWidth(),this.drapeau.getHeight(),null);
		g2.drawImage(this.pic1.getImagePic(),this.pic1.getX(), this.pic1.getY(),this.pic1.getWidth(),this.pic1.getHeight(),null);
		g2.drawImage(this.pic2.getImagePic(),this.pic2.getX(), this.pic2.getY(),this.pic2.getWidth(),this.pic2.getHeight(),null);
		g2.drawImage(this.pic3.getImagePic(),this.pic3.getX(), this.pic3.getY(),this.pic3.getWidth(),this.pic3.getHeight(),null);
		g2.drawImage(this.pic4.getImagePic(),this.pic4.getX(), this.pic4.getY(),this.pic4.getWidth(),this.pic4.getHeight(),null);
		g2.drawImage(this.pic6.getImagePic(),this.pic6.getX(), this.pic6.getY(),this.pic6.getWidth(),this.pic6.getHeight(),null);
		g2.drawImage(this.pic7.getImagePic(),this.pic7.getX(), this.pic7.getY(),this.pic7.getWidth(),this.pic7.getHeight(),null);
		g2.drawImage(this.pic9.getImagePic(),this.pic9.getX(), this.pic9.getY(),this.pic9.getWidth(),this.pic9.getHeight(),null);
		g2.drawImage(this.pic10.getImagePic(),this.pic10.getX(), this.pic10.getY(),this.pic10.getWidth(),this.pic10.getHeight(),null);
		g2.drawImage(this.bloc1.getImageBloc(),this.bloc1.getX(), this.bloc1.getY(),this.bloc1.getWidth(),this.bloc1.getHeight(),null);
		g2.drawImage(this.bloc2.getImageBloc(),this.bloc2.getX(), this.bloc2.getY(),this.bloc2.getWidth(),this.bloc2.getHeight(),null);
		g2.drawImage(this.bloc3.getImageBloc(),this.bloc3.getX(), this.bloc3.getY(),this.bloc3.getWidth(),this.bloc3.getHeight(),null);
		g2.drawImage(this.bloc4.getImageBloc(),this.bloc4.getX(), this.bloc4.getY(),this.bloc4.getWidth(),this.bloc4.getHeight(),null);
		g2.drawImage(this.bloc5.getImageBloc(),this.bloc5.getX(), this.bloc5.getY(),this.bloc5.getWidth(),this.bloc5.getHeight(),null);
		g2.drawImage(this.plateforme1.getImagePlateforme(),this.plateforme1.getX(), this.plateforme1.getY(),this.plateforme1.getWidth(),this.plateforme1.getHeight(),null);
	
		// Ici on gère le saut ou non du personnage
		if(this.player.isSaut() == true) {g2.drawImage(this.player.saute(),this.player.getX(),this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		else {g2.drawImage(this.player.walk("perso",5),this.player.getX(), this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		g2.drawImage(this.player.getImageVies(), 0, 0, null);	
		
	}
}
