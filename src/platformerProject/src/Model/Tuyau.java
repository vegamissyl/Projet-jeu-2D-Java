package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tuyau extends Object{

	private ImageIcon iconTuyau;
	private Image imageTuyau;
	
	public Tuyau(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		iconTuyau = new ImageIcon(getClass().getResource("/images/Tuyau.png"));
		this.imageTuyau = this.iconTuyau.getImage();
	}
	
	
	public Image getImageTuyau() {return imageTuyau;}
	

}
