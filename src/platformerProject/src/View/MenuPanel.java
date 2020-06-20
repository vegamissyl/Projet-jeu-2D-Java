package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	//images
	private ImageIcon iconFondGif;
	private Image imageFondGif;
	
	private ImageIcon iconTitle;
	private Image imageTitle;
	
	private ImageIcon iconCommandes;
	private Image imageCommandes;
	
	
	
	public MenuPanel() {
		//creation des icones et images
		iconFondGif = new ImageIcon(getClass().getResource("/images/fondGif2.gif"));
		this.imageFondGif = this.iconFondGif.getImage();
		
		iconTitle = new ImageIcon(getClass().getResource("/images/theTitle.png"));
		this.imageTitle = this.iconTitle.getImage();
		
		
		iconCommandes = new ImageIcon(getClass().getResource("/images/commandes.png"));
		this.imageCommandes = this.iconCommandes.getImage();
	}

	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; //améliore les graphismes
		g2.drawImage(this.imageFondGif,0,0, this.getWidth(),this.getHeight(),this); //dessin image du fond
		g2.drawImage(this.imageTitle,355,0, 710,100,this);
		g2.drawImage(this.imageCommandes,483,590, 430,150,this);
}


}




