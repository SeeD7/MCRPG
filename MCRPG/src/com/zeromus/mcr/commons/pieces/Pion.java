package com.zeromus.mcr.commons.pieces;

import com.zeromus.mcr.commons.Plateau;

public class Pion extends Piece{

	public Pion(int couleur, Plateau plateau) {
		super(couleur, plateau);
		setName("pion");
	}
	
	public Pion(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete, Plateau plateau){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete,plateau);
		setName("pion");
	}
	
	public Pion(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete);
		setName("pion");
	}

	@Override
	public boolean move(int baseX, int baseY, int moveX, int moveY) {
		if(moveX>=0 && moveX<8 && moveY>=0 && moveY<8){
			if(getCouleur()==0){
				if(!getHasMove() && baseY==moveY && baseX+2==moveX && !getPlateau().isPiece(baseX+1, baseY)){
					if(getPlateau().isPiece(moveX, moveY))
						return false;
					return true;
				}
				else if(baseX+1==moveX && baseY==moveY){
					if(getPlateau().isPiece(moveX, moveY))
						return false;
					return true;
				}
			}
			else{
				if(!getHasMove() && baseY==moveY && baseX-2==moveX && !getPlateau().isPiece(baseX-1, baseY)){
					if(getPlateau().isPiece(moveX, moveY))
							return false;
					return true;
				}
				else if(baseX-1==moveX && baseY==moveY){
					if(getPlateau().isPiece(moveX, moveY))
							return false;
					return true;
				}
			}
		}		
		return false;
	}

	@Override
	public boolean attack(int baseX, int baseY, int moveX, int moveY) {
		if(moveX>=0 && moveX<8 && moveY>=0 && moveY<8){
			if(getCouleur()==0){
				if((baseY+1==moveY || baseY-1==moveY) && baseX+1==moveX && getPlateau().isPiece(moveX, moveY))
					if(getPlateau().getColor(moveX, moveY)!=getCouleur())
						return true;
			}
			else{
				if((baseY+1==moveY || baseY-1==moveY) && baseX-1==moveX && getPlateau().isPiece(moveX, moveY))
					if(getPlateau().getColor(moveX, moveY)!=getCouleur())
						return true;

			}
		}
		return false;
	}

	@Override
	public boolean canAttack() {
		int baseX = getPosX();
		int baseY = getPosY();
		if(getCouleur()==0){
			if(attack(baseX,baseY,baseX+1,baseY+1) || attack(baseX,baseY,baseX+1,baseY-1))
				return true;
			else
				return false;
		}
		else{
			if(attack(baseX,baseY,baseX-1,baseY+1) || attack(baseX,baseY,baseX-1,baseY-1))
				return true;
			else
				return false;
		}
	}
	
	public void lvlUp() {
        int coef=100;
        while(getExp()>(getLevel()*coef))
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
