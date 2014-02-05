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

public class MainMenu extends MCR_Container{

	private static final long serialVersionUID = 5154037010484016625L;
	JLabel l_vide[];
	JLabel l_create,l_connect,l_quit;
	Font f_vide,f_button,f_button_over;
	Color c_black,c_red,c_gray;
	HashMap<String, String> textes;
	
	public MainMenu(Frame_Menu parent) {
	
		this.parent=parent;

		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		int width = (int)(d.width*0.4470);
		int height = (int)(d.height*0.09);
		Dimension label_dim = new Dimension(width,height);
		
		textes = parent.getTextes("MainMenu");
		
		c_black = new Color(0x000000);
		c_red = new Color(0xFF0000);
		c_gray = new Color(0x828282);
		
		f_button = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0625));
		f_button_over = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0677));
		
		l_create = new JLabel(textes.get("create_compte"), SwingConstants.LEFT);
		l_create.setFont(f_button);
		l_create.setForeground(c_gray);
		l_create.setPreferredSize(label_dim);

		
		l_connect = new JLabel(textes.get("connect"), SwingConstants.LEFT);
		l_connect.setFont(f_button);
		l_connect.setForeground(c_gray);
		l_connect.setPreferredSize(label_dim);
		
		l_quit = new JLabel(textes.get("quit"), SwingConstants.LEFT);
		l_quit.setFont(f_button);
		l_quit.setForeground(c_gray);
		l_quit.setPreferredSize(label_dim);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridx=0;
		x.gridy=0;
		this.add(l_create,x);
		x.gridy=1;
		this.add(l_connect,x);
		x.gridy=2;
		this.add(l_quit,x);
		
		
		repaint();
		l_create.addMouseListener(this);
		l_connect.addMouseListener(this);
		l_quit.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==l_create){
			parent.showContener(new CreationMenu(parent));
		}
		else if(arg0.getSource()==l_connect){
			parent.showContener(new ConnectionMenu(parent));
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
