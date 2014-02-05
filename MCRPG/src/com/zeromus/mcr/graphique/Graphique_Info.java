package com.zeromus.mcr.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JComponent;

import com.zeromus.mcr.commons.Piece;
import com.zeromus.mcr.ihm.Game_Container;

/**
 * @author KamiSama
 */
public class Graphique_Info extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Game_Container parent;
    private Piece piece_sel;

    public Graphique_Info(Game_Container parent){
    	this.parent=parent;
    }

    public void paintComponent(Graphics D){
        
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
    	
        D.setFont(new Font("Northwood High", Font.PLAIN, (int)(d.height*0.039)));
        if(parent.getCouleur()==1)
                D.setColor(Color.RED);
        else
                D.setColor(Color.BLUE);
        if(parent.getCouleur()==1)
        {
            D.drawString("Vous etes joueur Noir", (int)(d.width*0.0073), (int)(d.height*0.052));
        }
        else
        {
            D.drawString("Vous etes joueur Blanc", (int)(d.width*0.0073), (int)(d.height*0.052));
        }
        D.setFont(new Font("Northwood High", Font.PLAIN, (int)(d.height*0.026)));
        if(piece_sel!=null)
        {
            if(piece_sel.getCouleur()==1)
                D.setColor(Color.RED);
            else
                D.setColor(Color.BLUE);
            D.drawString("Niveau de la pieces : "+piece_sel.getLevel(), (int)(d.width*0.0090), (int)(d.height*0.15));
            D.drawString("Point de vie actuel : "+piece_sel.getCurpv(), (int)(d.width*0.0090), (int)(d.height*0.208));
            D.drawString("Point d'attaque : "+piece_sel.getStrength(), (int)(d.width*0.0090),(int)(d.height*0.234));
            D.drawString("Point de magie : "+piece_sel.getInte(), (int)(d.width*0.0090), (int)(d.height*0.26));
        }
    }
    
    public Dimension getPreferredSize() {
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
    	return new Dimension((int)(d.width*0.2), (int)(d.height*0.1041*6));
    }
    
    public void setSelected(Piece selected){
    	piece_sel=selected;
    	repaint();
    }
}
