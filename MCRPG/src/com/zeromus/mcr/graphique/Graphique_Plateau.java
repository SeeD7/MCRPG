package com.zeromus.mcr.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.zeromus.mcr.commons.Plateau;
import com.zeromus.mcr.ihm.Game_Container;

/**
 * @author KamiSama
 */
public class Graphique_Plateau extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String piecename="";
    private Plateau game_tray;
    private int width,height;
    private HashMap<String,Image> skinpiece=new HashMap<String,Image>();
    private HashMap<String, Color> couleur = new HashMap<String, Color>();
    private String tab_couleur[];
    private Game_Container parent;

    public Graphique_Plateau(Plateau game_tray, Game_Container parent){
    	
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		width = (int)(d.width*0.0512);
		height = (int)(d.height*0.0911);
    	
    	tab_couleur=new String[2];
		
		tab_couleur[0]="blanc";
		tab_couleur[1]="noir";

        this.game_tray=game_tray;
        this.parent=parent;
        
        couleur.put("Classique", new Color(153,153,153));
        couleur.put("Commune", new Color(0,204,0));
        couleur.put("Rare", new Color(255,204,0));
        couleur.put("Epique", new Color(128,0,128));
        couleur.put("Diabolique", new Color(204,0,0));
        couleur.put("Legendaire", new Color(255,255,255));
        
        try {
            skinpiece.put("pion_noir", ImageIO.read(new File("Media/Img/Pion_noir.png")));
            skinpiece.put("tour_noir", ImageIO.read(new File("Media/Img/Tour_noir.png")));
            skinpiece.put("cavalier_noir", ImageIO.read(new File("Media/Img/Cavalier_noir.png")));
            skinpiece.put("fou_noir", ImageIO.read(new File("Media/Img/fou_noir.png")));
            skinpiece.put("reine_noir", ImageIO.read(new File("Media/Img/Reine_noir.png")));
            skinpiece.put("roi_noir", ImageIO.read(new File("Media/Img/Roi_noir.png")));
            skinpiece.put("pion_blanc", ImageIO.read(new File("Media/Img/Pion_blanc.png")));
            skinpiece.put("tour_blanc", ImageIO.read(new File("Media/Img/Tour_blanc.png")));
            skinpiece.put("cavalier_blanc", ImageIO.read(new File("Media/Img/Cavalier_blanc.png")));
            skinpiece.put("fou_blanc", ImageIO.read(new File("Media/Img/fou_blanc.png")));
            skinpiece.put("reine_blanc", ImageIO.read(new File("Media/Img/Reine_blanc.png")));
            skinpiece.put("roi_blanc", ImageIO.read(new File("Media/Img/Roi_blanc.png")));
        } catch (IOException ex) {
            Logger.getLogger(Graphique_Plateau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintComponent(Graphics D){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(((i+j)%2)==0)
                {
                    D.setColor(Color.WHITE);
                }
                else
                {
                    D.setColor(Color.BLACK);
                }
                D.fillRect((i*width),(j*height), width, height);
            }
        if(parent.isSelect()){
	        D.setColor(Color.ORANGE);
	        D.fillRect((parent.getSelected_y()*width), (parent.getSelected_x()*height), width, height);
        }
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(game_tray.isPiece(i, j))
                {
                	Toolkit t = this.getToolkit();
            		Dimension d = t.getScreenSize();
            		
                    piecename=game_tray.getName(i, j)+"_"+tab_couleur[game_tray.getColor(i, j)];
                    D.drawImage((Image)skinpiece.get(piecename), j*width, +i*height, width, height, this);
        	    	
                    D.setColor(Color.BLACK);
                    for(double k=0.04166-0.0013;k<=0.042961;k=k+0.0013)
                    	for(double l=0.0146-0.000732;l<0.016464;l=l+0.000732)
                    		D.drawString(Integer.toString(game_tray.getPiece(i, j).getLevel()), (j*width)+(int)(d.width*k), (i*height)+(int)(d.height*l));
                    
                    D.setColor((Color)couleur.get(game_tray.getPiece(i, j).getRarete()));
                    D.drawString(Integer.toString(game_tray.getPiece(i, j).getLevel()), (j*width)+(int)(d.width*0.04166), (i*height)+(int)(d.height*0.0146));
                    
                    D.setColor(Color.RED);
                    D.fillRect((j*width)+(int)(d.width*0.003), ((i+1)*height-(int)(d.height*0.012)), width-(int)(d.width*0.0256), (int)(d.height*0.005));
                    D.setColor(Color.GREEN);
                    int valeur=((game_tray.getPiece(i, j).getCurpv()*100)/(game_tray.getPiece(i, j).getPv()));
                    D.fillRect((j*width)+(int)(d.width*0.003), ((i+1)*height-(int)(d.height*0.012)), (valeur*(width-(int)(d.width*0.0256)))/100, (int)(d.height*0.005));
                }
            }
    }
    
    public void setTray(Plateau game_tray){
    	this.game_tray=game_tray;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(8*width+1,8*height+1);
    }
}
