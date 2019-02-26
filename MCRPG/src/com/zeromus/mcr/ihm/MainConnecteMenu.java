package com.zeromus.mcr.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.zeromus.mcr.commons.Plateau;

public class MainConnecteMenu extends MCR_Container{

	private static final long serialVersionUID = 5154037010484016625L;
	JLabel l_vide[];
	JLabel l_didact,l_play,l_deco,l_quit,l_pieces;
	Font f_vide,f_button,f_button_over;
	Color c_black,c_red,c_gray;
	HashMap<String, String> textes;
	
	public MainConnecteMenu(Frame_Menu parent) {
	
		this.parent=parent;

		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		int width = (int)(d.width*0.4470);
		int height = (int)(d.height*0.09);
		Dimension label_dim = new Dimension(width,height);
		
		textes = parent.getTextes("MainConnecteMenu");
		
		c_black = new Color(0x000000);
		c_red = new Color(0xFF0000);
		c_gray = new Color(0x828282);
		
		f_button = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0625));
		f_button_over = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0677));
		
		l_didact = new JLabel(textes.get("dida"), SwingConstants.LEFT);
		l_didact.setFont(f_button);
		l_didact.setForeground(c_gray);
		l_didact.setPreferredSize(label_dim);

		
		l_play = new JLabel(textes.get("play"), SwingConstants.LEFT);
		l_play.setFont(f_button);
		l_play.setForeground(c_gray);
		l_play.setPreferredSize(label_dim);
		
		l_pieces = new JLabel(textes.get("pieces"), SwingConstants.LEFT);
		l_pieces.setFont(f_button);
		l_pieces.setForeground(c_gray);
		l_pieces.setPreferredSize(label_dim);
		
		l_deco = new JLabel(textes.get("deco"), SwingConstants.LEFT);
		l_deco.setFont(f_button);
		l_deco.setForeground(c_gray);
		l_deco.setPreferredSize(label_dim);
		
		l_quit = new JLabel(textes.get("quit"), SwingConstants.LEFT);
		l_quit.setFont(f_button);
		l_quit.setForeground(c_gray);
		l_quit.setPreferredSize(label_dim);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridx=0;
		x.gridy=0;
		this.add(l_didact,x);
		x.gridy=1;
		this.add(l_play,x);
		x.gridy=2;
		this.add(l_pieces,x);
		x.gridy=3;
		this.add(l_deco,x);
		x.gridy=4;
		this.add(l_quit,x);
		
		
		repaint();
		l_didact.addMouseListener(this);
		l_play.addMouseListener(this);
		l_pieces.addMouseListener(this);
		l_deco.addMouseListener(this);
		l_quit.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==l_didact){
			parent.showContener(new Didactitiel(new Plateau(), parent));
		}
		else if(arg0.getSource()==l_play){
			//parent.showContener(new Game(new Plateau(), parent));
			parent.showContener(new SearchGame(parent));
		}
		else if(arg0.getSource()==l_pieces){
			parent.showContener(new MyPiecesMenu(parent));
		}
		else if(arg0.getSource()==l_deco){
			parent.setConnected(null);
			parent.showContener(new MainMenu(parent));
		}
		else if(arg0.getSource()==l_quit){
			parent.dispose();
		}
	}
			

	@Override
	public void mouseEntered(MouseEvent arg0) {
		((JLabel)arg0.getSource()).setForeground(c_red);
		((JLabel)arg0.getSource()).setFont(f_button_over);		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		((JLabel)arg0.getSource()).setForeground(c_gray);
		((JLabel)arg0.getSource()).setFont(f_button);		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
