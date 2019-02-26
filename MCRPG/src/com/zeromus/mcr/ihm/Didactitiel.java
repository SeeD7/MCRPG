package com.zeromus.mcr.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.zeromus.mcr.commons.Plateau;
import com.zeromus.mcr.commons.pieces.Fou;
import com.zeromus.mcr.commons.pieces.Piece;
import com.zeromus.mcr.commons.pieces.Pion;
import com.zeromus.mcr.commons.pieces.Tour;
import com.zeromus.mcr.component.ImageBlinkingLabel;
import com.zeromus.mcr.component.TypewriterLabel;
import com.zeromus.mcr.graphique.Graphique_Info;
import com.zeromus.mcr.graphique.Graphique_Plateau;

/**
 *
 * @author KamiSama
 */
public class Didactitiel extends Game_Container implements MouseListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3107756552516567274L;
	private Graphique_Plateau g_tray;
	private Graphique_Info g_info;
	private Color c_gray;
	private JLabel l_vide[];
	private TypewriterLabel l_info;
	private Font f_info;
	//private int width,height;
    protected Piece[] piecesChoix = null, piecePartie = new Piece[16];
    private int avancement=1;
    private ImageBlinkingLabel pointer;
    
    private HashMap<String, String> textes;
    
    private Plateau game_tray;


    public Didactitiel(Plateau tab,Frame_Menu parent){
    	game_tray=tab;
    	this.parent=parent;
    	
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		/*width = (int)(d.width*0.0512);
		height = (int)(d.height*0.0911);*/
		
		textes = parent.getTextes("Didacticiel");
		
		c_gray = new Color(0x828282);
    	
		f_info = new Font("Northwood High", Font.BOLD, (int)(d.height*0.04427));
		
		l_info = new TypewriterLabel(textes.get(Integer.toString(avancement)),SwingConstants.CENTER,this);
		l_info.setFont(f_info);
		l_info.setForeground(c_gray);
		
		Image img,imgVoid;
		try {
			img = ImageIO.read(new File("Media/Img/fleche_bas.png"));
			imgVoid = ImageIO.read(new File("Media/Img/void.png"));
			pointer=new ImageBlinkingLabel(new ImageIcon(img.getScaledInstance((int)(d.width*0.014), (int)(d.height*0.026), 0)),new ImageIcon(imgVoid.getScaledInstance((int)(d.width*0.014), (int)(d.height*0.026), 0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		l_vide = new JLabel[2];
		for(int i=0;i<l_vide.length;i++){
			l_vide[i] = new JLabel("                             ");
		}
                
        g_tray=new Graphique_Plateau(game_tray, this);
        g_info=new Graphique_Info(this);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        x.fill=GridBagConstraints.BOTH;
        x.gridx=0;
        x.gridy=0;
        x.weightx = 1000;
        x.fill = GridBagConstraints.HORIZONTAL;
        this.add(l_vide[0],x);
        x.gridx=1;
        x.weightx = 0;
        x.fill = GridBagConstraints.NONE;
        this.add(g_tray,x);
        x.gridx=2;
        this.add(g_info,x);
        x.gridy=3;
        x.weightx = 1000;
        x.fill = GridBagConstraints.HORIZONTAL;
        this.add(l_vide[1],x);
        x.gridwidth=6;
        x.gridx=0;
        x.gridy=1;
        x.fill = GridBagConstraints.HORIZONTAL;
        x.weightx = 4;
        x.anchor = GridBagConstraints.LINE_START;
        this.add(l_info,x);
        x.gridx=4;
        x.gridy=2;
        this.add(pointer,x);
        
        repaint();
        
        g_tray.addMouseListener(this);
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    	/*int PosY=e.getX()/width;
        int PosX=e.getY()/height;*/
        
    	if(l_info.isWriting()){
    		l_info.stopWriting();
    		pointer.blink();
    	}
    	else{
			pointer.stop();
			incrAvancement();
			l_info.write(textes.get(Integer.toString(avancement)));
    	}
        
    	/*if(this.select==false){
    		if(game_tray.isPiece(PosX, PosY) && game_tray.getColor(PosX, PosY)==couleur){
	    		selected_x=PosX;
	    		selected_y=PosY;
	    		select=true;
    		}
    	}
        else{
        	if(selected_x==PosX && selected_y==PosY){
        		select=false;
        	}
        	else if(game_tray.canMove(selected_x,selected_y,PosX,PosY)){
    			game_tray.Move(selected_x,selected_y,PosX,PosY);
    			select=false;
    			couleur=(couleur+1)%2;
    		}
        }*/
        //repaint();

        
    }
    
    public void incrAvancement(){
    	if(avancement==9){
    		avancement++;
    		selected_x=1;
    		selected_y=1;
    		g_info.setSelected(game_tray.getPiece(1, 1));
    		select=true;
    	}
    	else if(avancement==26){
    		avancement++;
    		presentPiece(new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique",game_tray));
    	}    
    	else if(avancement==29){
    		avancement++;
    		presentPiece(new Tour(0, 0, "tour", 1, 0, 850, 200, 50,"Classique",game_tray));
    	}    
    	else if(avancement==32){
    		avancement++;
    		presentPiece(new Fou(0, 0, "fou", 1, 0, 650, 50, 300,"Classique",game_tray));
    	}    
    	else if(avancement>=36)
    		parent.showContener(new MainConnecteMenu(parent));
    	else
    		avancement++;
    }
    
    public void presentPiece(Piece piece){
    	Piece[][] plateau = new Piece[8][8];
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
            	plateau[i][j]=null;
        
        plateau[4][4]=piece;
        game_tray=null;
    	game_tray = new Plateau(plateau);
    	
    	g_tray.setTray(game_tray);
    	
        selected_x=4;
		selected_y=4;
		g_info.setSelected(game_tray.getPiece(4, 4));
		select=true;
		repaint();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

	public boolean isSelect() {
		return select;
	}

	public int getSelected_x() {
		return selected_x;
	}

	public int getSelected_y() {
		return selected_y;
	}
	
	public int getCouleur() {
		return couleur;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void finishWriting() {
		pointer.blink();
	}
}
