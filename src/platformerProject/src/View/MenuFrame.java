package View;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MenuFrame extends JFrame implements ActionListener{
		MenuPanel menu;
		
		private ImageIcon iconPlay;
		private Image imagePlay;
		
		private ImageIcon iconExit;
		private Image imageExit;
		
		private ImageIcon iconOptions;
		private Image imageOptions;
		
		URL url;
		Audio audio;
		
		public MenuFrame() {
			super();
			Audio.joue = true;
			audio = new Audio("/audio/Song_of_Sadhana.wav");
			iconPlay = new ImageIcon(getClass().getResource("/images/play.png"));
			this.imagePlay = this.iconPlay.getImage();
			
			iconExit = new ImageIcon(getClass().getResource("/images/exit.png"));
			this.imageExit = this.iconExit.getImage();
			
			iconOptions = new ImageIcon(getClass().getResource("/images/options.png"));
			this.imageOptions = this.iconOptions.getImage();
			
			menu = new MenuPanel();
			this.setTitle("La Quête de GrAAl"); //titre
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de pouvoir fermer la fenêtre
			this.setSize(1420,800);
			this.setLocationRelativeTo(null); //centrer à l'écran
			//this.setExtendedState(Frame.MAXIMIZED_BOTH);// taille max
			this.setResizable(false); // interdit de la redimensionner
			this.setAlwaysOnTop(true); // fenêtre devant les autres
			this.setContentPane(menu);
			
			this.url = getClass().getResource("/audio/Song_of_Sadhana.wav"); //on enregistre l'url de la musique
			audio.loop();
			
			//bouton exit
			JButton exit = new JButton(iconExit);
			exit.setOpaque(false);
			exit.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
			exit.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
			exit.setFocusPainted(false);
			exit.addActionListener(new EventExit());
			
			//bouton play
			JButton play = new JButton(iconPlay);
			play.setOpaque(false);
			play.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
			play.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
			play.setFocusPainted(false);
			play.addActionListener(this);
			
			
			//bouton options
			JButton option = new JButton(iconOptions);
			option.setOpaque(false);
			option.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
			option.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
			option.setFocusPainted(false);
			option.addActionListener(new EventOptions(this.audio));
			
			//construction du GridBagLayout
			GridBagLayout G = new GridBagLayout();
			this.getContentPane().setLayout(G);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth=1;
			c.gridheight=1;
			c.anchor = GridBagConstraints.CENTER;
			//c.anchor = GridBagConstraints.PAGE_END;
			c.fill = GridBagConstraints.NONE;
			this.add(play,c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth=1;
			c.gridheight=1;
			//c.weightx = 5;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.NONE;
			this.add(option,c);
			
		
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth=1;
			c.gridheight=1;
		
			c.anchor = GridBagConstraints.PAGE_END;
			c.fill = GridBagConstraints.NONE;
			this.add(exit,c);
	}
		

		public void actionPerformed(ActionEvent e) {
			
			// on instancie la fenetre de jeu
			FrameGame fenetre_jeu = new FrameGame();
			// on instancie la scene du jeu 
			Main.scene = new PanelGame();
			
			fenetre_jeu.setContentPane(Main.scene); // on injecte la scene dans la fenetre du jeu
			fenetre_jeu.setVisible(true);
			this.dispose();
			
		}
		
		public class EventExit implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		
		}
		public class EventOptions implements ActionListener{
			Audio music;
			public EventOptions(Audio son) {
				music = son;
			}
			public void actionPerformed(ActionEvent e) {
				
				OptionsFrame fenetre_options = new OptionsFrame(this.music);
				fenetre_options.setVisible(true);
			}
		
		}

}			



