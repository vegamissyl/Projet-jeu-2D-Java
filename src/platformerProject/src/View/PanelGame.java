package View;


import Model.Bloc;
import Model.EntityPlayer;
import Model.Object;
import Model.Pic;
import Model.Tuyau;

import javax.swing.JPanel;

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
	
	private ImageIcon iconTuyau;
	private Image imageTuyau;
	
	private int xFond;// variable : abscisse supérieur gauche de l'image de fond par rapport à l'ecran
	private int xFond2;
	private static int dx; // variable permettant de deplacer l'ecran (decors) horizontalement
	private static int xPos; //variable qui va repérer les éléments du jeu moins leur position en x = position absolu dans le jeu
	private static int ySol; // hauteur du sol
	private static int hauteurPlafond; // hauteur du plafond du jeu
	private  static int  longueur;
	private  static int hauteur;
	

	public Pic pic1;
	public Bloc bloc1;
	public static EntityPlayer player; //joueur
	
	
	public PanelGame() {
		super();
		//Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		PanelGame.longueur = 1420; //longueur de l'écran de l'ordinateur
		PanelGame.hauteur = 800 ; //hauteur de l'écran d'ordinateur
		this.xFond = 0; //pour que l'image de fond déborde de chaque côté de l'écran
		this.xFond2 = 1420;
		
		this.dx = 0;
		this.xPos = -1;
		this.ySol = 690;
		this.hauteurPlafond = 0;
		iconFond = new ImageIcon(getClass().getResource("/images/fond.png"));
		this.imageFond = this.iconFond.getImage();
		this.imageFond2 = this.iconFond.getImage();
		
		iconPanneau = new ImageIcon(getClass().getResource("/images/panneau_fleche.png"));
		this.imagePanneau = this.iconPanneau.getImage();
		
		pic1 = new Pic(1200,620 , 117 ,63);
		bloc1 = new Bloc(1400,330,85,85);
		player = new EntityPlayer(300, 480);
		
		//Audio.playSound("/images/musique.mp3");
		
		this.setFocusable(true); //mettre le focus pour écouter l'écran
		this.requestFocusInWindow(); //pour être sur de récupérer le focus
		this.addKeyListener(new Input()); //on ajoute à la scene les évènements clavier avec la classe input 
		
		Thread DisplayScreen = new Thread(new Display()); //on creer un thread sur l affichage de l'écran de décor
		DisplayScreen.start(); //on lance le thread d'affichage de l'écran
	}
	/*getters and setters*/
	
	
	public int getxFond() {
		return xFond;
	}

	public static  int getLongueur() {
		return longueur;
	}



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
	
	public void contact(Object objet) {
		if((this.player.contactAvant(objet) == true && player.isDirectionRight() == true) || (player.contactArriere(objet) == true && player.isDirectionRight() == false)) {
			this.setDx(0);
			this.player.setWalk(false);
		}
	
		if(this.player.contactDessous(objet) == true && this.player.isSaut() == true) {
			this.setySol(objet.getY());
		}else if(this.player.contactDessous(objet) == false) {
			this.setySol(690);
			if(this.player.isSaut() == false) {this.player.setY(480);}
		}
		
		if(this.player.contactDessus(objet) == true) {
			this.setHauteurPlafond(objet.getY() + objet.getHeight());
		}else if(this.player.contactDessus(objet) == false && this.player.isSaut() == false) {
			this.setHauteurPlafond(0);
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; //améliore les graphismes
		
		if(this.player.proche(pic1)) {this.contact(pic1);}
		if(this.player.proche(bloc1)) {this.contact(bloc1);}
	
		this.moveScreen(); //on appel la fonction pour deplacer l'écran à chaque boucle du run du thread
		this.pic1.mouvement();
		this.bloc1.mouvement();
		
		System.out.println("pnj y : " + (this.player.getY()+this.player.getHeight()) );
		System.out.println("pic y : " + this.pic1.getY());
		g2.drawImage(this.imageFond, this.xFond,0,null); //dessin image du fond
		g2.drawImage(this.imageFond2, this.xFond2,0,null);
		
		g2.drawImage(this.imagePanneau, 300-this.xPos,280,null);//on fixe le panneau au même endroit
		g2.drawImage(this.pic1.getImagePic(),this.pic1.getX(), this.pic1.getY(),this.pic1.getWidth(),this.pic1.getHeight(),null);
		g2.drawImage(this.bloc1.getImageBloc(),this.bloc1.getX(), this.bloc1.getY(),this.bloc1.getWidth(),this.bloc1.getHeight(),null);
		//utilisation de longueur et hauteur de l'écran pour bien placer le personnage quelque soit la taille de l'écran de l'ordinateur
		if(this.player.isSaut() == true) {g2.drawImage(this.player.saute(),this.player.getX(),this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		else {g2.drawImage(this.player.walk("perso",5),this.player.getX(), this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		
		g2.drawRect(this.player.getX(),this.player.getY(),this.player.getWidth(),this.player.getHeight()); // drawRect(x-position, y-position, width, height)
		g2.drawRect(this.pic1.getX(),this.pic1.getY(),this.pic1.getWidth(),this.pic1.getHeight());
		g2.drawRect(this.bloc1.getX(),this.bloc1.getY(),this.bloc1.getWidth(),this.bloc1.getHeight());
	}
}
