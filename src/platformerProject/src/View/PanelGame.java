package View;

import Model.EntityPeacefull;
import Model.EntityPlayer;
import Model.Pic;
import Model.Tuyau;

import javax.swing.JPanel;

// import com.sun.tools.sjavac.server.SysInfo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
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
	public static EntityPlayer player; //joueur
	
	
	public PanelGame() {
		super();
		Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		PanelGame.longueur = tailleMoniteur.width ; //longueur de l'écran de l'ordinateur
		PanelGame.hauteur = tailleMoniteur.height ; //hauteur de l'écran d'ordinateur
		this.xFond = 0; //pour que l'image de fond déborde de chaque côté de l'écran
		this.xFond2 = longueur;
		
		this.dx = 0;
		this.xPos = -1;
		this.ySol = (int)(PanelGame.hauteur*0.5058);
		this.hauteurPlafond = 0;
		iconFond = new ImageIcon(getClass().getResource("/images/fond.png"));
		this.imageFond = this.iconFond.getImage();
		this.imageFond2 = this.iconFond.getImage();
		
		iconPanneau = new ImageIcon(getClass().getResource("/images/panneau_fleche.png"));
		this.imagePanneau = this.iconPanneau.getImage();
		
		pic1 = new Pic((int)(PanelGame.longueur*0.56), (int)(PanelGame.hauteur*0.3058),10,10);
		
		player = new EntityPlayer((int)(PanelGame.longueur*0.26), (int)(PanelGame.hauteur*0.5058));
		
		Audio.playSound("/images/musique.mp3");
		
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



	public void setxFond(int xFond) {
		this.xFond = xFond;
	}

	public int getxFond2() {
		return xFond2;
	}

	public void setxFond2(int xFond2) {
		this.xFond2 = xFond2;
	}
	
	public static int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

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
		if(this.xFond <= -this.longueur) { //si le 1er fond est passé il va à la suite du 2e fond
			this.xFond = this.longueur;
		}
		else if(this.xFond2 <= -this.longueur) { //si le 2e fond est passé on le met à la suite du 1er fond
			this.xFond2 = this.longueur;
		}
		///de même lorsque le personnage se deplace à gauche
		else if(this.xFond >= this.longueur) { 
			this.xFond = -this.longueur;
		}
		else if(this.xFond2 >= this.longueur) { 
			this.xFond2 = -this.longueur;
		}
	}
	

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; //améliore les graphismes
		
		this.moveScreen(); //on appel la fonction pour deplacer l'écran à chaque boucle du run du thread
		if(this.player.contactAvant(pic1) == true) {
			this.player.setWalk(false);
			this.dx = 0;
		}
		this.pic1.mouvement();
		
		g2.drawImage(this.imageFond, this.xFond,0,this.getWidth(),this.getHeight(),this); //dessin image du fond
		g2.drawImage(this.imageFond2, this.xFond2,0,this.getWidth(),this.getHeight(),this);
		//g2.drawImage(this.player.walk("perso",(int)(this.longueur*0.0052)), (int)(this.longueur*0.26), (int)(this.hauteur*0.5058),null); //** provisoire
		g2.drawImage(this.imagePanneau, (int)(this.longueur*0.24)-this.xPos, (int)(this.hauteur*0.4016),null);//on fixe le panneau au même endroit
		g2.drawImage(this.pic1.getImagePic(),this.pic1.getX(), this.pic1.getY(),null);
		//utilisation de longueur et hauteur de l'écran pour bien placer le personnage quelque soit la taille de l'écran de l'ordinateur
		if(this.player.isSaut() == true) {g2.drawImage(this.player.saute(),this.player.getX(),this.player.getY(),null);}
		else {g2.drawImage(this.player.walk("perso",(int)(this.longueur*0.0052)), (int)(this.longueur*0.26), (int)(this.hauteur*0.5058),null);}
	}
}
