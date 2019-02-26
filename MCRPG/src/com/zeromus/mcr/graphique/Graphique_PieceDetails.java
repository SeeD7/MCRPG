package com.zeromus.mcr.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.zeromus.mcr.commons.pieces.Piece;
import com.zeromus.mcr.ihm.MCR_Container;

/**
 * @author KamiSama
 */
public class Graphique_PieceDetails extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int width, height;
    private MCR_Container parent;
    private Piece select=null;
    private HashMap<String, Color> couleur = new HashMap<String, Color>();
    private HashMap<String, BufferedImage> piece=new HashMap<String, BufferedImage>();
    private HashMap<String, String> textes;

    public Graphique_PieceDetails(MCR_Container Parent){
        parent=Parent;
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		textes = parent.getParent().getTextes("Graphique_PieceDetails");
		
		width = (int)(d.width*0.22);
		height = (int)(d.height*0.5);
		
        couleur.put("Classique", new Color(153,153,153));
        couleur.put("Commune", new Color(0,204,0));
        couleur.put("Rare", new Color(255,204,0));
        couleur.put("Epique", new Color(128,0,128));
        couleur.put("Diabolique", new Color(204,0,0));
        couleur.put("Legendaire", new Color(255,255,255));
        
        try {
			piece.put("pion", ImageIO.read(new File("Media/Img/Pion_noir.png")));
			piece.put("tour", ImageIO.read(new File("Media/Img/Tour_noir.png")));
	        piece.put("cavalier", ImageIO.read(new File("Media/Img/Cavalier_noir.png")));
	        piece.put("fou", ImageIO.read(new File("Media/Img/fou_noir.png")));
	        piece.put("reine", ImageIO.read(new File("Media/Img/Reine_noir.png")));
	        piece.put("roi", ImageIO.read(new File("Media/Img/Roi_noir.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void paintComponent(Graphics D){
		
    	if(select!=null){
    		Toolkit t = this.getToolkit();
    		Dimension d = t.getScreenSize();
    		
    		int width_intra = (int)(d.width*0.07);
    		int height_intra = (int)(d.height*0.045);
	    	
			D.setFont(new Font("Northwood High", Font.PLAIN, height_intra));
			
			D.setColor(couleur.get(select.getRarete()));
			
			D.drawImage(piece.get(select.getType()), (int)(d.width*0.16),(int)(d.height*0.02), (int)(d.width*0.073),(int)(d.height*0.13), this);

			height_intra = (int)(d.height*0.22);
			D.drawString(textes.get("lvl")+" "+select.getLevel()+"     "+textes.get("exp")+" "+select.getExp(), width_intra, height_intra);
			height_intra = (int)(d.height*0.27);
			D.drawString(textes.get("pv")+" "+select.getPv(), width_intra, height_intra);
			height_intra = (int)(d.height*0.32);
			D.drawString(textes.get("str")+" "+select.getStrength()+"     "+textes.get("int")+" " +select.getInte(), width_intra, height_intra);
    	}
    }
    
    public Dimension getPreferredSize() {
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		width = (int)(d.width*0.4);
		height = (int)(d.height*0.5);
        return new Dimension(width, height);
    }
    
    public void SetSelect(Piece select){
    	this.select=select;
    }
}
