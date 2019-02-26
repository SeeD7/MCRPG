package com.zeromus.mcr.commons.pieces;

import com.zeromus.mcr.commons.Plateau;

public class Cavalier extends Piece{

	public Cavalier(int couleur, Plateau plateau) {
		super(couleur, plateau);
		setName("cavalier");
	}
	
	public Cavalier(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete, Plateau plateau){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete,plateau);
		setName("cavalier");
	}
	
	public Cavalier(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete);
		setName("cavalier");
	}
	
	@Override
	public boolean move(int baseX, int baseY, int moveX, int moveY) {
		if(moveX>=0 && moveX<8 && moveY>=0 && moveY<8){
			if(Math.abs(baseX-moveX)==2 && Math.abs(baseY-moveY)==1 || Math.abs(baseX-moveX)==1 && Math.abs(baseY-moveY)==2){
				if(getPlateau().isPiece(moveX, moveY))
					return false;
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean attack(int baseX, int baseY, int moveX, int moveY) {
		if(moveX>=0 && moveX<8 && moveY>=0 && moveY<8)
			if(!(baseX-moveX==0 && baseY-moveY==0) && Math.abs(baseX-moveX)<2 && Math.abs(baseY-moveY)<2 && (Math.abs(baseX-moveX)+Math.abs(baseY-moveY)>0 || Math.abs(baseX-moveX)+Math.abs(baseY-moveY)<3)){
				if(getPlateau().getColor(moveX, moveY)==getCouleur())
					return false;
				return true;
			}
		return false;
	}

	@Override
	public boolean canAttack() {
		int baseX = getPosX();
		int baseY = getPosY();
		if(attack(baseX,baseY,baseX,baseY+1) || attack(baseX,baseY,baseX,baseY-1) || attack(baseX,baseY,baseX+1,baseY) || attack(baseX,baseY,baseX-1,baseY))
			if(attack(baseX,baseY,baseX+1,baseY+1) || attack(baseX,baseY,baseX+1,baseY-1) || attack(baseX,baseY,baseX-1,baseY+1) || attack(baseX,baseY,baseX-1,baseY-1))
				return true;
		return false;
	}

	@Override
	public void lvlUp() {
        int coef=150;
        {
            setExp(getExp()-(getLevel()*coef));
            setLevel(getLevel()+1);
            double num=1+((double)((int)((getLevel()+10)/10)))/100;
            setPv((int)(getPv()*num));
            setStrength((int)(getStrength()*num));
            setInte((int)(getInte()*num));
        }
	}
}
