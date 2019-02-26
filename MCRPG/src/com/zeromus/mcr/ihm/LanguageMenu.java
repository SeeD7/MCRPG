package com.zeromus.mcr.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.zeromus.mcr.Runner;

public class LanguageMenu extends MCR_Container{

	private static final long serialVersionUID = 5154037010484016625L;
	private JLabel l_french,l_english,l_nfrench,l_nenglish,l_vide[];
	private Font f_name,f_vide;
	private Color c_red,c_orange;
	private int position=0;
	
	public LanguageMenu(Frame_Menu parent) {
	
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		this.parent=parent;

		c_red = new Color(0xFF0000);
		c_orange = new Color(0xFFA200);
		
		f_name = new Font("Times New Roman", Font.BOLD, (int)(d.height*0.0651));
		f_vide = new Font("Times New Roman", Font.BOLD, (int)(d.height*0.039));
		try {

			Image background = ImageIO.read(new File("Media/Img/french_flag.jpg"));
			l_french = new JLabel(new ImageIcon(background.getScaledInstance((int)(d.width*0.2), (int)(d.height*0.25), 0)));
			l_french.setBorder(javax.swing.BorderFactory.createLineBorder(c_orange));
			
			background = ImageIO.read(new File("Media/Img/english_flag.jpg"));
			l_english = new JLabel(new ImageIcon(background.getScaledInstance((int)(d.width*0.2), (int)(d.height*0.25), 0)));
        } catch (IOException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		l_nfrench = new JLabel("Fran�ais", SwingConstants.CENTER);
		l_nfrench.setFont(f_name);
		l_nfrench.setForeground(c_red);
		l_nenglish = new JLabel("Anglais", SwingConstants.CENTER);
		l_nenglish.setFont(f_name);
		l_nenglish.setForeground(c_red);
		
		l_vide = new JLabel[3];
		for(int i=0;i<l_vide.length;i++){
			l_vide[i] = new JLabel("          ");
			l_vide[i].setFont(f_vide);
		}
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridx=0;
		x.gridy=0;
		this.add(l_french,x);
		x.gridx=1;
		this.add(l_vide[0],x);
		x.gridx=2;
		this.add(l_english,x);
		
		x.gridx=0;
		x.gridy=1;
		this.add(l_vide[1],x);
		
		x.gridx=0;
		x.gridy=2;
		this.add(l_nfrench,x);
		x.gridx=1;
		this.add(l_vide[2],x);
		x.gridx=2;
		this.add(l_nenglish,x);	
		repaint();
		
		l_french.addMouseListener(this);
		l_nfrench.addMouseListener(this);
		l_english.addMouseListener(this);
		l_nenglish.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==l_french || arg0.getSource()==l_nfrench)
			parent.setLanguageSelector("francais");
		if(arg0.getSource()==l_english || arg0.getSource()==l_nenglish)
			parent.setLanguageSelector("english");
		parent.showContener(new MainMenu(parent));
	}
			

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource()==l_french || arg0.getSource()==l_nfrench){
			if(position!=0){
				position=0;
				changeLanguages();				
			}
		}
		if(arg0.getSource()==l_english || arg0.getSource()==l_nenglish){
			if(position!=1){
				position=1;
				changeLanguages();
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()) {
	        case KeyEvent.VK_ENTER:
	        	System.out.println("Position : "+position);
	        	break;
	        case KeyEvent.VK_LEFT:
	        	position=(Math.abs(position-1))%2;
	        	changeLanguages();
	        	break;
	        case KeyEvent.VK_RIGHT:
	        	position=(position+1)%2;
	        	changeLanguages();
	        	break;
		}
	}
	
	public void changeLanguages(){
		switch(position) {
		case 0:
			l_nfrench.setText("Français");
			l_nenglish.setText("Anglais");
			l_french.setBorder(javax.swing.BorderFactory.createLineBorder(c_orange));
			l_english.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case 1:
			l_nfrench.setText("French");
			l_nenglish.setText("English");
			l_english.setBorder(javax.swing.BorderFactory.createLineBorder(c_orange));
			l_french.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		}
	}
}
