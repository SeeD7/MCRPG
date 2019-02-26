package com.zeromus.mcr.commons.pieces;

import com.zeromus.mcr.commons.Plateau;

public class Reine extends Piece{

	public Reine(int couleur, Plateau plateau) {
		super(couleur, plateau);
		setName("reine");
	}
	
	public Reine(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete, Plateau plateau){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete,plateau);
		setName("reine");
	}
	
	public Reine(int id, int joueur, String type, int level, int exp ,int pv, int strength, int inte, String rarete){
		super(id, joueur, type, level, exp ,pv, strength, inte, rarete);
		setName("reine");
	}

	@Override
	public boolean move(int baseX, int baseY, int moveX, int moveY) {
		if(moveX>=0 && moveX<8 && moveY>=0 && moveY<8){
			if((baseX==moveX && baseY!=moveY) || (baseX!=moveX && baseY==moveY)){
				if(baseY!=moveY){
					int incr=(moveY-baseY)/Math.abs(moveY-baseY);
					for(int i=baseY+1;i<=moveY;i+=incr)
						if(getPlateau().isPiece(baseX, i))
							return false;
					return true;
				}
				else{
					int incr=(moveX-baseX)/Math.abs(moveX-baseX);
					for(int i=baseX+1;i<=moveX;i+=incr)
						if(getPlateau().isPiece(i, baseY))
							return false;
					return true;
				}
			}
			else if(Math.abs(baseX-moveX)==Math.abs(baseY-moveY) && baseX-moveX!=0){
				int incrX=(moveX-baseX)/Math.abs(moveX-baseX);
				int incrY=(moveY-baseY)/Math.abs(moveY-baseY);
				int i=baseX;
				int j=baseY;
				for(int k=baseX+incrX;k<moveX;k+=incrX)
				{
					i+=incrX;
					j+=incrY;
					if(getPlateau().isPiece(i,j))
						return false;
				}
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
	
	public void lvlUp() {
        int coef=250;
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
