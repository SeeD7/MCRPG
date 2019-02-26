package com.zeromus.mcr.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.zeromus.mcr.commons.Game;
import com.zeromus.mcr.commons.pieces.Piece;
import com.zeromus.mcr.ihm.MCR_Container;

/**
 * @author KamiSama
 */
public class Graphique_PieceGame extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int width, height;
    private MCR_Container parent;
    private HashMap<String, Color> couleur = new HashMap<String, Color>();
    private HashMap<String, BufferedImage> skinpiece=new HashMap<String, BufferedImage>();
    private Game select=null;

    public Graphique_PieceGame(MCR_Container Parent){
        parent=Parent;
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		width = (int)(d.width*0.0512);
		height = (int)(d.height*0.0911);
		
        couleur.put("Classique", new Color(153,153,153));
        couleur.put("Commune", new Color(0,204,0));
        couleur.put("Rare", new Color(255,204,0));
        couleur.put("Epique", new Color(128,0,128));
        couleur.put("Diabolique", new Color(204,0,0));
        couleur.put("Legendaire", new Color(255,255,255));
        
        try {
			skinpiece.put("pion", ImageIO.read(new File("Media/Img/Pion_noir.png")));
			skinpiece.put("tour", ImageIO.read(new File("Media/Img/Tour_noir.png")));
	        skinpiece.put("cavalier", ImageIO.read(new File("Media/Img/Cavalier_noir.png")));
	        skinpiece.put("fou", ImageIO.read(new File("Media/Img/fou_noir.png")));
	        skinpiece.put("reine", ImageIO.read(new File("Media/Img/Reine_noir.png")));
	        skinpiece.put("roi", ImageIO.read(new File("Media/Img/Roi_noir.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void paintComponent(Graphics D){
		
    	if(select!=null){
    		ArrayList<Piece> pieces = select.getSet();
    		for(int i=0;i<pieces.size();i++)
            {
    			Piece piece=pieces.get(i);
            	Toolkit t = this.getToolkit();
        		Dimension d = t.getScreenSize();
        		
                String piecename=piece.getName();
                D.drawImage((Image)skinpiece.get(piecename), (i%8)*width, ((int)(i/8))*height, width, height, this);
    	    	
                D.setColor(Color.BLACK);
                for(double k=0.04166-0.0013;k<=0.042961;k=k+0.0013)
                	for(double l=0.0146-0.000732;l<0.016464;l=l+0.000732)
                		D.drawString(Integer.toString(piece.getLevel()), ((i%8)*width)+(int)(d.width*k), ((i/8)*height)+(int)(d.height*l));
                
                D.setColor((Color)couleur.get(piece.getRarete()));
                D.drawString(Integer.toString(piece.getLevel()), ((i%8)*width)+(int)(d.width*0.04166), ((i/8)*height)+(int)(d.height*0.0146));
            }
    	}
    }
    
    public Dimension getPreferredSize() {
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
        return new Dimension((int)(d.width*0.4),(int)(d.height*0.5));
    }
    
    public void setSelect(Game select){
    	this.select=select;
    }
}
