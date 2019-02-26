package com.zeromus.mcr.commons.pieces;

import com.zeromus.mcr.commons.Plateau;

public abstract class Piece {
	
	private int id, couleur, pv, curpv, strength, inte, level, exp, joueur,posX,posY;
    private String rarete, type;
	
	private String name;
	private boolean hasMove=false;
	private Plateau plateau;

	public Piece(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete,Plateau plateau){
        this.id=id;
        this.joueur=joueur;
        this.pv=pv;
        this.strength=strength;
        this.inte=inte;
        this.rarete=rarete;
        this.level=level;
        this.exp=exp;
        this.type=type;
        this.curpv=pv;
        this.plateau=plateau;
    }
	
	public Piece(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete){
        this.id=id;
        this.joueur=joueur;
        this.pv=pv;
        this.strength=strength;
        this.inte=inte;
        this.rarete=rarete;
        this.level=level;
        this.exp=exp;
        this.type=type;
        this.curpv=pv;
    }
	
	public Piece(int couleur, Plateau plateau){
		this.couleur=couleur;
		this.plateau=plateau;
	}
	
	public abstract boolean move(int baseX, int baseY, int moveX, int moveY);
	public abstract boolean attack(int baseX, int baseY, int moveX, int moveY);
	public abstract boolean canAttack();
	
	public void move(){
		hasMove=true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	public boolean getHasMove() {
		return hasMove;
	}

	public void setHasMove(boolean hasMove) {
		this.hasMove = hasMove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getCurpv() {
		return curpv;
	}

	public void setCurpv(int curpv) {
		this.curpv = curpv;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getInte() {
		return inte;
	}

	public void setInte(int inte) {
		this.inte = inte;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public String getRarete() {
		return rarete;
	}

	public void setRarete(String rarete) {
		this.rarete = rarete;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void addExp(int exp){
        this.exp=this.exp+exp;
    }
	
	public void attack(int strength){
        this.curpv=this.curpv-strength;
    }

    public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	abstract public void lvlUp();
}
