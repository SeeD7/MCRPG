package com.zeromus.mcr.ihm;

import com.zeromus.mcr.commons.Plateau;

public abstract class Game_Container extends MCR_Container {
	
	private static final long serialVersionUID = 1L;
	protected int couleur=0;
    protected boolean select=false;
    protected int selected_x,selected_y;    
    protected Plateau game_tray;

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
	
	public boolean possibleAttack(int couleur){
		return game_tray.possibleAttack(couleur);
	}

}
