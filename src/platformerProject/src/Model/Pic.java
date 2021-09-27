package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pic extends Object {
	private ImageIcon iconPic;
	private Image imagePic;
	
	// Constructeur de l'objet Pic
	public Pic(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		iconPic = new ImageIcon(getClass().getResource("/images/pic.png"));
		this.imagePic= this.iconPic.getImage();
	}
	
	
	public Image getImagePic() {return imagePic;}
}
