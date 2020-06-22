package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Controller.Main;

public class OptionsFrame extends JFrame  {
	JPanel panel;
	JTabbedPane onglets;
	JRadioButton on;
	JRadioButton off;
	Audio audio;
	
	
	public OptionsFrame(Audio son) {
		
		audio = son;
		this.setTitle("Options"); //titre
		
		this.setSize(400,220);
		this.setLocationRelativeTo(null); //centrer à l'écran
		this.setResizable(false); // interdit de la redimensionner
		this.setAlwaysOnTop(true); // fenêtre devant les autres
		
		panel = new JPanel();
		onglets = new JTabbedPane(SwingConstants.TOP);
		
		JPanel onglet1 = new JPanel();
	    JLabel titreOnglet1 = new JLabel("Music : ");
	    onglet1.add(titreOnglet1);
	    onglet1.setPreferredSize(new Dimension(380, 200));
	    onglets.addTab("Music", onglet1);

	    JPanel onglet2 = new JPanel();
	    JLabel titreOnglet2 = new JLabel("Other Settings");
	    onglet2.add(titreOnglet2);
	    onglets.addTab("Other Settings", onglet2);
	    
	    ButtonGroup groupe = new ButtonGroup();
	    this.on = new JRadioButton("On",Audio.joue);
	    groupe.add(on);
	    onglet1.add(on);
	    this.on.addActionListener(new EventOn(audio));
	    
	    if(Audio.joue == true) {
	    	 this.off = new JRadioButton("Off",false);
	    }
	    else {this.off = new JRadioButton("Off",true);}
	    groupe.add(off);
	    onglet1.add(off);
	    this.off.addActionListener(new EventOff(audio));

	    onglets.setOpaque(true);
	    panel.add(onglets);
	    this.getContentPane().add(panel);
	    
	    
	}

	
	public class EventOff implements ActionListener{
		Audio audio;
		public EventOff(Audio son){
			audio = son;
		}
		public void actionPerformed(ActionEvent e) {
			Audio.joue = false;
			audio.stop();
		}
	
	}
	public class EventOn implements ActionListener{
		Audio audio;
		public EventOn(Audio son){
			audio = son;
		}
		public void actionPerformed(ActionEvent e) {
			Audio.joue = true;
			audio.loop();
		}
	
	}

}
