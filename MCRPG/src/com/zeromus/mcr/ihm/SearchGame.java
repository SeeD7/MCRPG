package com.zeromus.mcr.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.zeromus.mcr.commons.Cavalier;
import com.zeromus.mcr.commons.Compte;
import com.zeromus.mcr.commons.Fou;
import com.zeromus.mcr.commons.Game;
import com.zeromus.mcr.commons.Piece;
import com.zeromus.mcr.commons.Pion;
import com.zeromus.mcr.commons.Reine;
import com.zeromus.mcr.commons.Roi;
import com.zeromus.mcr.commons.Tour;
import com.zeromus.mcr.graphique.Graphique_ListGames;
import com.zeromus.mcr.graphique.Graphique_PieceGame;

public class SearchGame extends MCR_Container{

	private static final long serialVersionUID = 5154037010484016625L;
	private JLabel b_creer,b_rafraichir,b_annuler,b_rejoindre,l_selected;
	private Font f_button,f_button_over;
	private Color c_red,c_gray;
	private Graphique_ListGames listGame;
	private Graphique_PieceGame listPiece;
	private HashMap<String, String> textes;
	
	public SearchGame(Frame_Menu parent) {
	
		this.parent=parent;

		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		textes = parent.getTextes("SearchGame");
		
		listGame = new Graphique_ListGames(this);
		listPiece = new Graphique_PieceGame(this);
		
		int width = (int)(d.width*0.2);
		int height = (int)(d.height*0.08);
		Dimension label_dim = new Dimension(width,height);
		
		c_red = new Color(0xFF0000);
		c_gray = new Color(0x828282);
		
		f_button = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0525));
		f_button_over = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0577));
		
		
		b_rafraichir = new JLabel(textes.get("refresh"), SwingConstants.LEFT);
		b_rafraichir.setFont(f_button);
		b_rafraichir.setForeground(c_gray);
		b_rafraichir.setPreferredSize(label_dim);
		
		b_creer = new JLabel(textes.get("create"), SwingConstants.LEFT);
		b_creer.setFont(f_button);
		b_creer.setForeground(c_gray);
		b_creer.setPreferredSize(label_dim);
		
		b_rejoindre = new JLabel(textes.get("join"), SwingConstants.LEFT);
		b_rejoindre.setFont(f_button);
		b_rejoindre.setForeground(c_gray);
		b_rejoindre.setPreferredSize(label_dim);
		
		b_annuler = new JLabel(textes.get("back"), SwingConstants.LEFT);
		b_annuler.setFont(f_button);
		b_annuler.setForeground(c_gray);
		b_annuler.setPreferredSize(label_dim);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridx=0;
		x.gridy=0;
		this.add(listGame,x);
		x.gridy=1;
		this.add(b_rafraichir,x);
		x.gridy=2;
		this.add(b_rejoindre,x);
		x.gridy=3;
		this.add(b_creer,x);
		x.gridy=4;
		this.add(b_annuler,x);
		
		x.gridx=1;
		x.gridy=0;
		this.add(listPiece,x);
		
		validate();
		repaint();
		
		listGame.addMouseListener(this);
		
		b_creer.addMouseListener(this);
		b_rafraichir.addMouseListener(this);
		b_rejoindre.addMouseListener(this);
		b_annuler.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==b_creer){
			//parent.showContener(new CreationMenu(parent));
		}
		
		else if(arg0.getSource()==b_rafraichir){
			Compte c1 = new Compte(0, "Chrysler", "", 0);
			Compte c2 = new Compte(0, "Hypno", "", 0);
			Compte c3 = new Compte(0, "Chepre", "", 0);
			Compte c4 = new Compte(0, "Ange", "", 0);
			Compte c5 = new Compte(0, "Marion", "", 0);
			
			ArrayList<Piece> set1 = new ArrayList<Piece>();
	        for(int i=0;i<8;i++)
	        	set1.add(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique"));
	        
	        set1.add(new Tour(0, 0, "tour", 45, 0, 850, 200, 50,"Commune"));
	        set1.add(new Cavalier(0, 0, "cavalier", 47, 0, 700, 150, 150,"Commune"));
	        set1.add(new Fou(0, 0, "fou", 23, 0, 650, 50, 300,"Classique"));
	        set1.add(new Roi(0, 0, "roi", 55, 0, 1200, 100, 100,"Rare"));
	        set1.add(new Reine(0, 0, "reine", 36, 0, 800, 200, 200,"Diabolique"));
	        set1.add(new Fou(0, 0, "fou", 52, 0, 650, 50, 300,"Classique"));
	        set1.add(new Cavalier(0, 0, "cavalier", 41, 0, 700, 150, 150,"Classique"));
	        set1.add(new Tour(0, 0, "tour", 42, 0, 850, 200, 50,"Classique"));
	        
			ArrayList<Piece> set2 = new ArrayList<Piece>();
	        for(int i=0;i<8;i++)
	        	set2.add(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique"));
	        
	        set2.add(new Tour(0, 0, "tour", 1, 0, 850, 200, 50,"Classique"));
	        set2.add(new Cavalier(0, 0, "cavalier", 1, 0, 700, 150, 150,"Classique"));
	        set2.add(new Fou(0, 0, "fou", 1, 0, 650, 50, 300,"Classique"));
	        set2.add(new Roi(0, 0, "roi", 1, 0, 1200, 100, 100,"Classique"));
	        set2.add(new Reine(0, 0, "reine", 1, 0, 800, 200, 200,"Classique"));
	        set2.add(new Fou(0, 0, "fou", 1, 0, 650, 50, 300,"Classique"));
	        set2.add(new Cavalier(0, 0, "cavalier", 1, 0, 700, 150, 150,"Classique"));
	        set2.add(new Tour(0, 0, "tour", 1, 0, 850, 200, 50,"Classique"));
	        
			ArrayList<Piece> set3 = new ArrayList<Piece>();
	        for(int i=0;i<8;i++)
	        	set3.add(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique"));
	        
	        set3.add(new Tour(0, 0, "tour", 36, 0, 850, 200, 50,"Classique"));
	        set3.add(new Cavalier(0, 0, "cavalier", 28, 0, 700, 150, 150,"Classique"));
	        set3.add(new Fou(0, 0, "fou", 18, 0, 650, 50, 300,"Classique"));
	        set3.add(new Roi(0, 0, "roi", 42, 0, 1200, 100, 100,"Epique"));
	        set3.add(new Reine(0, 0, "reine", 15, 0, 800, 200, 200,"Rare"));
	        set3.add(new Fou(0, 0, "fou", 25, 0, 650, 50, 300,"Classique"));
	        set3.add(new Cavalier(0, 0, "cavalier", 32, 0, 700, 150, 150,"Legendaire"));
	        set3.add(new Tour(0, 0, "tour", 15, 0, 850, 200, 50,"Classique"));
	        
			ArrayList<Piece> set4 = new ArrayList<Piece>();
	        for(int i=0;i<8;i++)
	        	set4.add(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique"));
	        
	        set4.add(new Tour(0, 0, "tour", 12, 0, 850, 200, 50,"Rare"));
	        set4.add(new Cavalier(0, 0, "cavalier", 8, 0, 700, 150, 150,"Classique"));
	        set4.add(new Fou(0, 0, "fou", 23, 0, 650, 50, 300,"Classique"));
	        set4.add(new Roi(0, 0, "roi", 26, 0, 1200, 100, 100,"Classique"));
	        set4.add(new Reine(0, 0, "reine", 12, 0, 800, 200, 200,"Diabolique"));
	        set4.add(new Fou(0, 0, "fou", 11, 0, 650, 50, 300,"Classique"));
	        set4.add(new Cavalier(0, 0, "cavalier", 20, 0, 700, 150, 150,"Commune"));
	        set4.add(new Tour(0, 0, "tour", 16, 0, 850, 200, 50,"Commune"));
	        
			ArrayList<Piece> set5 = new ArrayList<Piece>();
	        for(int i=0;i<8;i++)
	        	set5.add(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique"));
	        
	        set5.add(new Tour(0, 0, "tour", 45, 0, 850, 200, 50,"Classique"));
	        set5.add(new Cavalier(0, 0, "cavalier", 47, 0, 700, 150, 150,"Classique"));
	        set5.add(new Fou(0, 0, "fou", 23, 0, 650, 50, 300,"Classique"));
	        set5.add(new Roi(0, 0, "roi", 55, 0, 1200, 100, 100,"Classique"));
	        set5.add(new Reine(0, 0, "reine", 36, 0, 800, 200, 200,"Classique"));
	        set5.add(new Fou(0, 0, "fou", 52, 0, 650, 50, 300,"Classique"));
	        set5.add(new Cavalier(0, 0, "cavalier", 41, 0, 700, 150, 150,"Classique"));
	        set5.add(new Tour(0, 0, "tour", 42, 0, 850, 200, 50,"Classique"));
		    	
	        ArrayList<Game> games = new ArrayList<Game>();
	        
	        games.add(new Game(c1,set1));
	        games.add(new Game(c2,set2));
	        games.add(new Game(c3,set3));
	        games.add(new Game(c4,set4));
	        games.add(new Game(c5,set5));
	        
			listGame.setGames(games);
			listGame.repaint();			
		}
		else if(arg0.getSource()==listGame){
			Toolkit t = this.getToolkit();
			Dimension d = t.getScreenSize();
			int PosY=(int)(arg0.getY()/(d.getHeight()*0.05));
			
			if(PosY==0){
				listGame.decrPosition();
			}
			else if(PosY>0 && PosY<9){
				listGame.setSelected(listGame.getGame(PosY-1+listGame.getPosition()));
				listPiece.setSelect(listGame.getGame(PosY-1+listGame.getPosition()));
			}
			else if(PosY==9){
				listGame.incrPosition();
			}
			repaint();
		}		
		else if(arg0.getSource()==b_annuler){
			parent.showContener(new MainConnecteMenu(parent));
		}
	}
			

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource().getClass()==JLabel.class){
			((JLabel)arg0.getSource()).setForeground(c_red);
			((JLabel)arg0.getSource()).setFont(f_button_over);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource().getClass()==JLabel.class){
			if(arg0.getSource()!=l_selected){
				((JLabel)arg0.getSource()).setForeground(c_gray);
				((JLabel)arg0.getSource()).setFont(f_button);
			}
		}
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
