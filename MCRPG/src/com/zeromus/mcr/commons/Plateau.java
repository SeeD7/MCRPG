package com.zeromus.mcr.commons;

import java.util.ArrayList;

/**
 * @author KamiSama
 */
public class Plateau {

    private Piece plateau[][];
    private ArrayList<Piece> list_noire = new ArrayList<Piece>();
    private ArrayList<Piece> list_blanche= new ArrayList<Piece>();

    //Initialisation du plateau. On lui donne la taille du plateau et éventuellement les cases vides
    public Plateau(){
        plateau = new Piece[8][8];
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
            	plateau[i][j]=null;
        
        for(int i=0;i<8;i++){
        	plateau[1][i]=new Pion(0, 0, "pion", 1, 0, 450, 75, 75,"Classique",this);
        	plateau[1][i].setCouleur(0);
        	plateau[6][i]=new Pion(1, 1, "pion", 1, 0, 450, 75, 75,"Classique",this);
        	plateau[6][i].setCouleur(1);
        }
        
        plateau[0][0]=new Tour(0, 0, "tour", 1, 0, 850, 200, 50,"Classique",this);
    	plateau[0][1]=new Cavalier(0, 0, "cavalier", 1, 0, 700, 150, 150,"Classique",this);
    	plateau[0][2]=new Fou(0, 0, "fou", 1, 0, 650, 50, 300,"Classique",this);
    	plateau[0][3]=new Roi(0, 0, "roi", 1, 0, 1200, 100, 100,"Classique",this);
    	plateau[0][4]=new Reine(0, 0, "reine", 1, 0, 800, 200, 200,"Classique",this);
    	plateau[0][5]=new Fou(0, 0, "fou", 1, 0, 650, 50, 300,"Classique",this);
    	plateau[0][6]=new Cavalier(0, 0, "cavalier", 1, 0, 700, 150, 150,"Classique",this);
    	plateau[0][7]=new Tour(0, 0, "tour", 1, 0, 850, 200, 50,"Classique",this);
    	
    	plateau[7][0]=new Tour(1, 1, "tour", 1, 0, 850, 200, 50,"Classique",this);
     	plateau[7][1]=new Cavalier(1, 1, "cavalier", 1, 0, 700, 150, 150,"Classique",this);
     	plateau[7][2]=new Fou(1, 1, "fou", 1, 0, 650, 50, 300,"Classique",this);
     	plateau[7][3]=new Roi(1, 1, "roi", 1, 0, 1200, 100, 100,"Classique",this);
     	plateau[7][4]=new Reine(1, 1, "reine", 1, 0, 800, 200, 200,"Classique",this);
     	plateau[7][5]=new Fou(1, 1, "fou", 1, 0, 650, 50, 300,"Classique",this);
     	plateau[7][6]=new Cavalier(1, 1, "cavalier", 1, 0, 700, 150, 150,"Classique",this);
     	plateau[7][7]=new Tour(1, 1, "tour", 1, 0, 850, 200, 50,"Classique",this);
     	
        for(int i=0;i<8;i++){
        	plateau[0][i].setCouleur(0);
        	plateau[7][i].setCouleur(1);
        }
        
        for(int i=0;i<2;i++)
        	for(int j=0;j<8;j++){
        		list_blanche.add(plateau[i][j]);
        		plateau[i][j].setPosX(i);
        		plateau[i][j].setPosY(j);
        	}
        
        for(int i=7;i>5;i--)
        	for(int j=0;j<8;j++){
        		list_noire.add(plateau[i][j]);
        		plateau[i][j].setPosX(i);
        		plateau[i][j].setPosY(j);
        	}
    }
    
    public Plateau(Piece[][] tableau){
        plateau = tableau;
    }

	public boolean canMove(int baseX, int baseY, int moveX, int moveY) {
		return (plateau[baseX][baseY].move(baseX, baseY, moveX, moveY));
	}
	
	public void Move(int selected_x, int selected_y, int posX, int posY) {
		plateau[posX][posY]=plateau[selected_x][selected_y];
		plateau[selected_x][selected_y]=null;
		plateau[posX][posY].move();
		plateau[posX][posY].setPosX(posX);
		plateau[posX][posY].setPosY(posY);
	}
	
	public boolean possibleAttack(int couleur){
		boolean attack=false;
		if(couleur==0){
			for(Piece piece : list_blanche)
				if(piece.canAttack())
					attack=true;
		}
		else{
			for(Piece piece : list_blanche)
				if(piece.canAttack())
					attack=true;
		}
		return attack;
	}
	
	public boolean canAttack(int baseX, int baseY, int moveX, int moveY) {
		return (plateau[baseX][baseY].attack(baseX, baseY, moveX, moveY));
	}
	
	public void Attack(int selected_x, int selected_y, int posX, int posY) {
		plateau[posX][posY].attack(plateau[selected_x][selected_y].getStrength());
		if(plateau[posX][posY].getCurpv()<=0)
			plateau[posX][posY]=null;
	}

	public boolean isPiece(int baseX, int baseY) {
		if(plateau[baseX][baseY]==null)
			return false;
		else
			return true;
	}

	public int getColor(int baseX, int baseY) {
		if(isPiece(baseX, baseY))
			return plateau[baseX][baseY].getCouleur();
		return -1;
	}
	
	public Piece getPiece(int baseX, int baseY) {
		return plateau[baseX][baseY];
	}
	
	public String getName(int baseX, int baseY) {
		if(isPiece(baseX, baseY))
			return plateau[baseX][baseY].getName();
		return "-1";
	}
}
