package com.zeromus.mcr.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.zeromus.mcr.commons.Plateau;
import com.zeromus.mcr.graphique.Graphique_Info;
import com.zeromus.mcr.graphique.Graphique_Plateau;

/**
 *
 * @author KamiSama
 */
public class GameFrame extends Game_Container implements MouseListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3107756552516567274L;
	private Graphique_Plateau g_tray;
	private Graphique_Info g_info;
    private int width,height;
    //private Piece[] piecesChoix = null, piecePartie = new Piece[16];
    private int couleur=0;
    private boolean select=false, attack=false, move=false;
    private int selected_x,selected_y;

    public GameFrame(Plateau tab,Frame_Menu parent){
    	game_tray=tab;
    	this.parent=parent;
    	
    	Toolkit t = this.getToolkit();
		Dimension d = t.getScreenSize();
		
		width = (int)(d.width*0.0512);
		height = (int)(d.height*0.0911);
                
        g_tray=new Graphique_Plateau(game_tray, this);
        g_info=new Graphique_Info(this);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        x.fill=GridBagConstraints.BOTH;
        x.gridx=0;
        x.gridy=0;
        this.add(g_tray,x);
        x.gridx=1;
        x.gridy=0;
        this.add(g_info,x);
        
        repaint();
        
        g_tray.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    	int PosY=e.getX()/width;
        int PosX=e.getY()/height;
        
    	if(select==false){
    		if(game_tray.isPiece(PosX, PosY) && game_tray.getColor(PosX, PosY)==couleur){
	    		selected_x=PosX;
	    		selected_y=PosY;
	    		g_info.setSelected(game_tray.getPiece(PosX, PosY));
	    		select=true;
    		}
    	}
        else{
        	if(selected_x==PosX && selected_y==PosY){
        		select=false;
        		g_info.setSelected(null);
        	}
        	else if(!move && game_tray.canMove(selected_x,selected_y,PosX,PosY)){
    			game_tray.Move(selected_x,selected_y,PosX,PosY);
    			select=false;
    			if(!possibleAttack(couleur) || attack){
	    			g_info.setSelected(null);
	    			couleur=(couleur+1)%2;
	    			reInitAction();
    			}
    			else
    				move=true;
    		}
        	else if(!attack && game_tray.canAttack(selected_x,selected_y,PosX,PosY)){
    			game_tray.Attack(selected_x,selected_y,PosX,PosY);
    			select=false;
    			if(move){
	    			g_info.setSelected(null);
	    			couleur=(couleur+1)%2;
	    			reInitAction();
    			}
    			else
    				attack=true;
    		}
        }
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
    
    public void reInitAction(){
		move=false;
		attack=false;
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

	}
}
