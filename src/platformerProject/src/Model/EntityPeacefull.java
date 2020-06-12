package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class EntityPeacefull extends Entity {
	
	private ImageIcon iconObstacle;
	private Image imageObstacle;
	
	public EntityPeacefull(int _x, int _y) {
		super(_x, _y, (int)(View.PanelGame.getLongueur()*0.06), (int)(View.PanelGame.getLongueur()*0.13));
	}

}
