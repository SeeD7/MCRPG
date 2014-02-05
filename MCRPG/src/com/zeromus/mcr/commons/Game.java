package com.zeromus.mcr.commons;

import java.util.ArrayList;


public class Game {
	
	private Compte player;
	private ArrayList<Piece> set;
	
	public Game(Compte player,ArrayList<Piece> set){
		this.player=player;
		this.set=set;
	}

	public Compte getPlayer() {
		return player;
	}

	public void setPlayer(Compte player) {
		this.player = player;
	}

	public ArrayList<Piece> getSet() {
		return set;
	}

	public void setSet(ArrayList<Piece> set) {
		this.set = set;
	}
	
	public void setColor(int Color){
		for(Piece piece : set)
			piece.setCouleur(Color);
	}

}
