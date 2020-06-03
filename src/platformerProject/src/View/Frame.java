package View;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.*;

public class Frame extends JFrame{
	windowVariables dataBase;
	
	public Frame() {
		this.setTitle("Platformer Project");
		
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setSize(1920, 1080);
		this.setUndecorated(true);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.setContentPane(new Pannel());
	    this.setVisible(true);
	    
	    this.addKeyListener(new Input());
	}

	public void setDataBase(windowVariables dataBase) {
		this.dataBase = dataBase;
	}
}


