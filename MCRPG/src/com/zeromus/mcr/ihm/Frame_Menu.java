package com.zeromus.mcr.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.zeromus.mcr.commons.Compte;
import com.zeromus.mcr.filereader.LanguageReader;

public class Frame_Menu extends JFrame implements KeyListener{

	private static final long serialVersionUID = -2491318572428961996L;
	private MCR_Container currentContainer;
	Compte connected = null;
	LanguageReader language_selector;

	public Frame_Menu() {

		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		this.setSize(d.width, d.height);
		
		JLabel label;
		try {
			Image background = ImageIO.read(new File("Media/Img/Background.jpg"));
			label = new JLabel(new ImageIcon(background.getScaledInstance(d.width, d.height, 0)));
			this.setContentPane(label);
			setLayout(new BorderLayout());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showContener(new LanguageMenu(this));
		this.addKeyListener(this);
	}

	public void showContener(MCR_Container newContainer) {

		if (currentContainer != null) {
			this.remove(currentContainer);
		}
		this.currentContainer = newContainer;
		this.add(currentContainer);

		this.validate();
		this.repaint();
	}
	
	public void setConnected(Compte connected){
		this.connected=connected;
	}

	public Compte getConnected() {
		return connected;
	}
	
	public HashMap<String,String> getTextes(String selection) {
		if(language_selector!=null)
			return language_selector.getSection(selection);
		else
			return null;
	}
	
	public void setLanguageSelector(String language) {
		language_selector = new LanguageReader(language);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		currentContainer.keyPressed(arg0);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}