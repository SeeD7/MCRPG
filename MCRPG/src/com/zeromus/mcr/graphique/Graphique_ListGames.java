package com.zeromus.mcr.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.zeromus.mcr.commons.Game;
import com.zeromus.mcr.ihm.MCR_Container;

/**
 * @author KamiSama
 */
public class Graphique_ListGames extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color c_gray;
	private int width, height, position=0;
	private MCR_Container parent;
	private ArrayList<Game> games=null;
	private Game selected=null;
    
    public Graphique_ListGames(MCR_Container Parent){
        parent=Parent;
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		width = (int)(d.width*0.22);
		height = (int)(d.height*0.5);
		
		c_gray = new Color(0x828282);
    }

    public void paintComponent(Graphics D){
		
		Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		int width_intra = (int)(d.width*0.007);
		int height_intra = (int)(d.height*0.05);
    	
		if(games!=null && selected!=null){
			D.setColor(Color.ORANGE);
			int pos = games.indexOf(selected)-position;
			D.fillRect(0, ((pos)*height_intra)+height_intra, width-12, height_intra);
		}
		
		D.setFont(new Font("Northwood High", Font.PLAIN, height_intra));
		
		D.setColor(c_gray);
		
		int height_intra_deca = (int)(d.height*0.048);
		
		if(games!=null){
			if(games.size()>8 && position>0)
				try {
					D.drawImage(ImageIO.read(new File("Media/Img/fleche_haut.png")),(int)(d.width*0.093), 0,(int)(d.width*0.029), height_intra,this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			for(int i=0;i<8;i++)
				if(i<games.size()){
					D.drawString(games.get(i+position).getPlayer().getLogin(), width_intra, ((i+1)*height_intra)+height_intra_deca);
				}
			if(games.size()>8 && position+8!=games.size())
				try {
					D.drawImage(ImageIO.read(new File("Media/Img/fleche_bas.png")),(int)(d.width*0.093), (8*height_intra)+height_intra_deca,(int)(d.width*0.029), height_intra,this);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		
		D.drawRect(0, 0, width-12, height-2);
		D.drawRect(1, 1, width-14, height-4);
		D.drawRect(2, 2, width-16, height-6);
		
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
    
    public void setGames(ArrayList<Game> games){
    	this.games=games;
    }
    
    public Game getGame(int pos){
    	if(games!=null && pos<games.size()){
	    	return games.get(pos);
    	}
    	else
    		return null;
    }
    
    public void incrPosition(){
    	if(position+8<games.size())
    		position++;
    }
    
    public void decrPosition(){
    	if(position>0)
    		position--;
    }
    
    public void setSelected(Game selected){
    	this.selected=selected;
    }

	public int getPosition() {
		return position;
	}
}
