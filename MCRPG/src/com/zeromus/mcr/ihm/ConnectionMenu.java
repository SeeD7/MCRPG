package com.zeromus.mcr.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.zeromus.mcr.bdd.BDD_M;
import com.zeromus.mcr.commons.Compte;

public class ConnectionMenu extends MCR_Container implements MouseListener{

	private static final long serialVersionUID = 5154037010484016625L;
	JLabel l_vide[],l_vide_light[],l_erreur, l_login, l_mdp,b_connexion,b_supprimer,b_annuler;
	JTextField t_login;
	JPasswordField t_password;
	Font f_vide,f_button,f_vide_light,f_button_over,f_textfield;
	Color c_gray,c_red;
	BDD_M bdd_manager;
	HashMap<String, String> textes;
	
	public ConnectionMenu(Frame_Menu parent) {
	
		this.parent=parent;
	
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		int width = (int)(d.width*0.25);
		int height = (int)(d.height*0.05);
		Dimension text_dim = new Dimension(width,height);
		
		textes = parent.getTextes("ConnectionMenu");
		
		bdd_manager=BDD_M.getInstance();
		
		c_gray = new Color(0x828282);
		c_red = new Color(0xFF0000);
		
		f_vide = new Font("Impact", Font.BOLD, (int)(d.height*0.013));
		f_vide_light = new Font("Impact", Font.BOLD, (int)(d.height*0.0065));
		f_button = new Font("Northwood High", Font.BOLD, (int)(d.height*0.04427));
		f_textfield = new Font("Times New Roman", Font.BOLD, (int)(d.height*0.04427));
		f_button_over = new Font("Northwood High", Font.BOLD, (int)(d.height*0.04948));
		
		t_login=new JTextField();
		t_login.setPreferredSize(text_dim);
		t_login.setFont(f_textfield);
		
		t_password=new JPasswordField();
		t_password.setPreferredSize(text_dim);
		t_password.setFont(f_textfield);
		
		l_login = new JLabel(textes.get("login"), SwingConstants.LEFT);
		l_login.setFont(f_button);
		l_login.setForeground(c_gray);
		
		l_mdp = new JLabel(textes.get("mdp"), SwingConstants.LEFT);
		l_mdp.setFont(f_button);
		l_mdp.setForeground(c_gray);
		
		l_erreur = new JLabel(" ");
		l_erreur.setForeground(c_red);
		l_erreur.setFont(f_button);
		
		l_vide = new JLabel[4];
		for(int i=0;i<l_vide.length;i++){
			l_vide[i] = new JLabel("     ");
			l_vide[i].setFont(f_vide);
		}
		
		b_connexion = new JLabel(textes.get("connect"));
		b_connexion.setFont(f_button);
		b_connexion.setForeground(c_gray);
		b_connexion.setPreferredSize(new Dimension((int)(d.width*0.17), (int)(d.height*0.05)));
		
		b_annuler = new JLabel(textes.get("back"), SwingConstants.CENTER);
		b_annuler.setFont(f_button);
		b_annuler.setForeground(c_gray);
		b_annuler.setPreferredSize(new Dimension((int)(d.width*0.07), (int)(d.height*0.05)));
		
		b_supprimer = new JLabel(textes.get("suppr"), SwingConstants.RIGHT);
		b_supprimer.setFont(f_button);
		b_supprimer.setForeground(c_gray);
		b_supprimer.setPreferredSize(new Dimension((int)(d.width*0.2), (int)(d.height*0.02)));

		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridwidth=3;
		
		x.gridx=0;
		x.gridy=0;
		this.add(l_login,x);		
		x.gridy=1;
		this.add(l_mdp,x);
		
		x.gridx=1;
		x.gridy=0;
		this.add(t_login,x);		
		x.gridy=1;
		this.add(t_password,x);
		
		x.gridwidth=4;
		x.gridx=0;
		x.gridy=4;
		this.add(l_erreur,x);
		
		x.gridwidth=1;
		x.gridx=0;
		x.gridy=5;
		this.add(b_connexion,x);
		x.gridx=3;
		this.add(b_supprimer,x);
		
		x.gridy=2;
		this.add(l_vide[0],x);
		x.gridy=4;
		this.add(l_vide[1],x);
		
		x.gridwidth=4;
		x.gridx=0;
		x.gridy=6;
		this.add(b_annuler,x);
		
		repaint();
		b_connexion.addMouseListener(this);
		b_supprimer.addMouseListener(this);
		b_annuler.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==b_connexion){
			if(!bdd_manager.checkLogin(t_login.getText())){
				String password = new String(t_password.getPassword());
				Compte account = bdd_manager.getAccount(t_login.getText());
				if(account.getPassword().compareTo(password)==0){
					parent.setConnecté(account);
					parent.showContener(new MainConnecteMenu(parent));
				}
				else
					l_erreur.setText(textes.get("pb_mdp").replace("?-?",t_login.getText()));
			}
			else
				l_erreur.setText(textes.get("pb_compte").replace("?-?",t_login.getText()));
		}
		else if(arg0.getSource()==b_supprimer){
			parent.showContener(new ConnectionMenu(parent));
		}
		else if(arg0.getSource()==b_annuler){
			parent.showContener(new MainMenu(parent));
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