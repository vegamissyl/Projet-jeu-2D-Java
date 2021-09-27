package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bloc extends Object {
	private ImageIcon iconBloc;
	private Image imageBloc;
	
	// Constructeur de l'objet Bloc
	public Bloc(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		iconBloc = new ImageIcon(getClass().getResource("/images/bloc.png"));
		this.imageBloc= this.iconBloc.getImage();
	}
	
	
	public Image getImageBloc() {return imageBloc;}
}
