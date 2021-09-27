package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Plateforme extends Object {
	private ImageIcon iconPlateforme;
	private Image imagePlateforme;
	
	// Constructeur de l'objet Plateforme
	public Plateforme(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		iconPlateforme = new ImageIcon(getClass().getResource("/images/plateforme.png"));
		this.imagePlateforme= this.iconPlateforme.getImage();
	}
	
	
	public Image getImagePlateforme() {return imagePlateforme;}
}
