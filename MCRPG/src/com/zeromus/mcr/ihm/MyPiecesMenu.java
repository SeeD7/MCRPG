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

import com.zeromus.mcr.bdd.BDD_M;
import com.zeromus.mcr.graphique.Graphique_ListPieces;
import com.zeromus.mcr.graphique.Graphique_PieceDetails;

public class MyPiecesMenu extends MCR_Container{

	private static final long serialVersionUID = 5154037010484016625L;
	JLabel b_supprimer,b_annuler,l_selected;
	Font f_vide,f_button,f_button_over;
	Color c_black,c_red,c_gray;
	JLabel[] l_pieces;
	Graphique_ListPieces pieces;
	Graphique_PieceDetails details;
	BDD_M bdd_manager;
	HashMap<String, String> textes;
	
	public MyPiecesMenu(Frame_Menu parent) {
	
		this.parent=parent;
		
		bdd_manager = BDD_M.getInstance();
		
		pieces = new Graphique_ListPieces(this);
		details = new Graphique_PieceDetails(this);

		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		textes = parent.getTextes("MyPiecesMenu");
		
		int width = (int)(d.width*0.15);
		int height = (int)(d.height*0.06);
		Dimension label_dim = new Dimension(width,height);
		
		c_black = new Color(0x000000);
		c_red = new Color(0xFF0000);
		c_gray = new Color(0x828282);
		
		f_button = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0625));
		f_button_over = new Font("Northwood High", Font.BOLD, (int)(d.height*0.0677));
		
		l_pieces = new JLabel[6];
		
		l_pieces[0] = new JLabel(textes.get("pion")+"s", SwingConstants.LEFT);
		l_pieces[1] = new JLabel(textes.get("tour")+"s", SwingConstants.LEFT);
		l_pieces[2] = new JLabel(textes.get("cavalier")+"s", SwingConstants.LEFT);
		l_pieces[3] = new JLabel(textes.get("fou")+"s", SwingConstants.LEFT);
		l_pieces[4] = new JLabel(textes.get("reine")+"s", SwingConstants.LEFT);
		l_pieces[5] = new JLabel(textes.get("roi")+"s", SwingConstants.LEFT);
		
		for(int i=0; i<l_pieces.length;i++){
			l_pieces[i].setFont(f_button);
			l_pieces[i].setForeground(c_gray);
			l_pieces[i].setPreferredSize(label_dim);
		}
		
		height = (int)(d.height*0.15);
		label_dim = new Dimension(width,height);
		
		b_supprimer = new JLabel(textes.get("suppr"), SwingConstants.LEFT);
		b_supprimer.setFont(f_button);
		b_supprimer.setForeground(c_gray);
		b_supprimer.setPreferredSize(label_dim);
		
		b_annuler = new JLabel(textes.get("back"), SwingConstants.LEFT);
		b_annuler.setFont(f_button);
		b_annuler.setForeground(c_gray);
		b_annuler.setPreferredSize(label_dim);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints x = new GridBagConstraints();
		x.fill=GridBagConstraints.BOTH;
		x.gridx=0;
		x.gridy=0;
		
		for(int i=0; i<l_pieces.length;i++){
			x.gridy=i;
			this.add(l_pieces[i],x);
		}
		
		x.gridy=6;
		this.add(b_supprimer,x);
		x.gridy=7;
		this.add(b_annuler,x);
		
		x.gridheight=8;
		x.gridx=1;
		x.gridy=0;
		this.add(pieces,x);
		
		x.gridx=2;
		this.add(details,x);
		
		validate();
		repaint();
		
		for(int i=0; i<l_pieces.length;i++){
			l_pieces[i].addMouseListener(this);
		}
		
		pieces.addMouseListener(this);
		
		b_supprimer.addMouseListener(this);
		b_annuler.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==l_pieces[0] || arg0.getSource()==l_pieces[1] || arg0.getSource()==l_pieces[2] || arg0.getSource()==l_pieces[3] || arg0.getSource()==l_pieces[4] || arg0.getSource()==l_pieces[5]){
			if(l_selected!=null){
				l_selected.setForeground(c_gray);
				l_selected.setFont(f_button);
			}
			l_selected = (JLabel)arg0.getSource();
			((JLabel)arg0.getSource()).setForeground(c_red);
			((JLabel)arg0.getSource()).setFont(f_button_over);
			if(arg0.getSource()==l_pieces[0])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"pion"));
			else if(arg0.getSource()==l_pieces[1])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"tour"));
			else if(arg0.getSource()==l_pieces[2])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"cavalier"));
			else if(arg0.getSource()==l_pieces[3])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"fou"));
			else if(arg0.getSource()==l_pieces[4])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"reine"));
			else if(arg0.getSource()==l_pieces[5])
				pieces.setSelection(bdd_manager.getPiecesByType(parent.getConnected(),"roi"));
			pieces.repaint();
		}
		else if(arg0.getSource()==pieces){
			Toolkit t = this.getToolkit();
			Dimension d = t.getScreenSize();
			int PosY=(int)(arg0.getY()/(d.getHeight()*0.05));
			
			if(PosY==0){
				pieces.decrPosition();
			}
			else if(PosY>0 && PosY<9){
				pieces.setSelected(pieces.getPiece(PosY-1+pieces.getPosition()));
				details.SetSelect(pieces.getPiece(PosY-1+pieces.getPosition()));
			}
			else if(PosY==9){
				pieces.incrPosition();
			}
			repaint();
		}
		else if(arg0.getSource()==b_supprimer){
			//parent.showContener(new CreationMenu(parent));
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
