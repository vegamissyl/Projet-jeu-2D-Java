package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Drapeau extends Object {
	private ImageIcon iconDrapeau;
	private Image imageDrapeau;
	
	// Constructeur de l'objet Drapeau
	public Drapeau(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		iconDrapeau = new ImageIcon(getClass().getResource("/images/DrapeauA.png"));
		this.imageDrapeau= this.iconDrapeau.getImage();
	}
	
	
	public Image getImageDrapeau() {return imageDrapeau;}
}
