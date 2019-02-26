package com.zeromus.mcr;


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.zeromus.mcr.ihm.Frame_Menu;

public class Runner {

	public static void main(String[] args) {
        
	    /*BDD_M Base=BDD_M.getInstance();
	    int[][] base={{450,75,75},{850,200,50},{700,150,150},{650,50,300},{800,200,200},{1200,100,100}};
	    String[] ty={"pion","tour","cavalier","fou","reine","roi"};
	    int joueur=1;
	    int id=0;

        int chance=(int)(Math.random()*1000000)%1000;
        //int type=(int)(Math.random()*51)%5;
        int type=0;
        if(chance>=801 && chance<=920)
        {
            int borne1=(int)(Math.random()*200);
            int borne2=(int)(Math.random()*200);
            int pv=base[type][0]+Math.min(borne1, borne2);
            int force=base[type][1]+(Math.max(borne1, borne2)-Math.min(borne1, borne2));
            int inte=base[type][2]+(200-Math.max(borne1, borne2));
            id=Base.New_piece(1,pv, force, inte, 1, 0, "Rare", ty[type]);
        }
        else if(chance>=921 && chance<=970)
        {
            int borne1=(int)(Math.random()*300);
            int borne2=(int)(Math.random()*300);
            int pv=base[type][0]+Math.min(borne1, borne2);
            int force=base[type][1]+(Math.max(borne1, borne2)-Math.min(borne1, borne2));
            int inte=base[type][2]+(300-Math.max(borne1, borne2));
            id=Base.New_piece(1,pv, force, inte, 1, 0, "Epique", ty[type]);
        }
        else if(chance>=971 && chance<=990)
        {
            int borne1=(int)(Math.random()*400);
            int borne2=(int)(Math.random()*400);
            int pv=base[type][0]+Math.min(borne1, borne2);
            int force=base[type][1]+(Math.max(borne1, borne2)-Math.min(borne1, borne2));
            int inte=base[type][2]+(400-Math.max(borne1, borne2));
            id=Base.New_piece(1,pv, force, inte, 1, 0, "Diabolique", ty[type]);
        }
        else if(chance>=991 && chance<=1000)
        {
            int borne1=(int)(Math.random()*500);
            int borne2=(int)(Math.random()*500);
            int pv=base[type][0]+Math.min(borne1, borne2);
            int force=base[type][1]+(Math.max(borne1, borne2)-Math.min(borne1, borne2));
            int inte=base[type][2]+(500-Math.max(borne1, borne2));
            id=Base.New_piece(1,pv, force, inte, 1, 0, "Legendaire", ty[type]);
        }
        System.out.println("Id : "+id+" Chance : "+chance);*/
		
		try {
		      Class.forName("org.postgresql.Driver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:postgresql://localhost:5432/MCRPG";
		      String user = "postgres";
		      String passwd = "azertyuiop123.";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");
		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
		/*Frame_Menu fenetre = new Frame_Menu();
        //On interdit le redimenssionement
        fenetre.setResizable(false);
        fenetre.setUndecorated(true);
        
        try {
            Image img;
            img = ImageIO.read(new File("Media/Img/Logo.jpg"));
            fenetre.setIconImage(img);

        } catch (IOException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //On done un titre a notre fenetre
        fenetre.setTitle("Mad Chess RPG");
    	
        //On indique ce que l'on souhaite faire lorsque l'on clique sur la croix
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);*/
	}

}
