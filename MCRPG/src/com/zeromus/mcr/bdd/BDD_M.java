package com.zeromus.mcr.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zeromus.mcr.commons.Cavalier;
import com.zeromus.mcr.commons.Compte;
import com.zeromus.mcr.commons.Fou;
import com.zeromus.mcr.commons.Piece;
import com.zeromus.mcr.commons.Pion;
import com.zeromus.mcr.commons.Reine;
import com.zeromus.mcr.commons.Roi;
import com.zeromus.mcr.commons.Tour;

/**
 * @author KamiSama
 */
public class BDD_M {

    private static BDD_M Singleton = null;
    private Connection con;
    private Statement st;
    private ResultSet rs;

    
    public final static BDD_M getInstance() {
         //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet d'éviter un appel coà»teux à  synchronized, 
         //une fois que l'instanciation est faite.
         if (BDD_M.Singleton == null) {
            // Le mot-clé synchronized sur ce bloc empàªche toute instanciation multiple màªme par différents "threads".
            // Il est TRES important.
            synchronized(BDD_M.class) {
              if (BDD_M.Singleton == null) {
                BDD_M.Singleton = new BDD_M();
              }
            }
         }
         return BDD_M.Singleton;
     }

    BDD_M(){
        //Cette partie permet la création de la connexion à  la base de donnée
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:mcrpg.sql");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("DB ERROR: "+ex);
        }
    }

    public Compte getAccount(String login){
    	try {
    		rs = st.executeQuery("SELECT * FROM player WHERE login LIKE \""+login+"\";");
            rs.next();
            return(new Compte(rs.getInt("id"),rs.getString("login"),rs.getString("password"),0));
    	} catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return null;
    }
    
    public boolean checkLogin(String login){
    	try {
            rs = st.executeQuery("SELECT COUNT(*) AS NUMBER FROM player WHERE login like \""+login+"\";");
            rs.next();
            if(rs.getInt("NUMBER")!=0)
            	return false;
            else
            	return true;
    	} catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return false;
    }
        
    public boolean createAccount(Compte account){

        try {
			Statement ST = con.createStatement();
			if(account.getLogin().length()>2 && account.getPassword().length()>4 && account.getLogin().length()<9 && account.getPassword().length()<9){
			    ST.executeUpdate("INSERT INTO player (login,password) VALUES ('"+account.getLogin()+"','"+account.getPassword()+"');");
			    rs = st.executeQuery("SELECT MAX(id) as value FROM piece;");
			    rs.next();
			    int id=rs.getInt("value");
			    
			    for(int i=0;i<8;i++)
			    	st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'pion', 1, 0, 450, 75, 75,'Classique');");

			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'tour', 1, 0, 850, 200, 50,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'tour', 1, 0, 850, 200, 50,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'cavalier', 1, 0, 700, 150, 150,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'cavalier', 1, 0, 700, 150, 150,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'fou', 1, 0, 650, 50, 300,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'fou', 1, 0, 650, 50, 300,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'reine', 1, 0, 800, 200, 200,'Classique');");
			    st.executeUpdate("INSERT INTO piece VALUES (NULL, "+id+", 'roi', 1, 0, 1200, 100, 100,'Classique');");
			    
			    return true;
			}
        } catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
  //Remplis le tableau de piéces du joueur passé en paramètre
    public ArrayList<Piece> getPieces(Compte Joueur){
    	ArrayList<Piece> my_pieces = new ArrayList<Piece>();
    	try {
            rs = st.executeQuery("SELECT * FROM piece WHERE Id_Joueur = "+Joueur.getId_player()+" ORDER BY Type;");
            while(rs.next())
            {
            	if(rs.getString("Type").compareTo("pion")==0)
            		my_pieces.add(new Pion(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(rs.getString("Type").compareTo("tour")==0)
            		my_pieces.add(new Tour(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(rs.getString("Type").compareTo("cavalier")==0)
            		my_pieces.add(new Cavalier(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(rs.getString("Type").compareTo("fou")==0)
            		my_pieces.add(new Fou(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(rs.getString("Type").compareTo("reine")==0)
            		my_pieces.add(new Reine(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(rs.getString("Type").compareTo("roi")==0)
            		my_pieces.add(new Roi(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return my_pieces;
    }
    
    //Remplis le tableau de piéces du joueur passé en paramètre
    public ArrayList<Piece> getPiecesByType(Compte Joueur, String type){
    	ArrayList<Piece> my_pieces = new ArrayList<Piece>();
    	try {
            rs = st.executeQuery("SELECT * FROM piece WHERE Id_Joueur = "+Joueur.getId_player()+" AND Type LIKE '"+type+"' ORDER BY Type;");
            while(rs.next())
            {
            	if(type.compareTo("pion")==0)
            		my_pieces.add(new Pion(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(type.compareTo("tour")==0)
            		my_pieces.add(new Tour(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(type.compareTo("cavalier")==0)
            		my_pieces.add(new Cavalier(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(type.compareTo("fou")==0)
            		my_pieces.add(new Fou(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(type.compareTo("reine")==0)
            		my_pieces.add(new Reine(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            	else if(type.compareTo("roi")==0)
            		my_pieces.add(new Roi(rs.getInt("id"), rs.getInt("Id_Joueur"),rs.getString("Type"),rs.getInt("Niveau"),rs.getInt("Experience"),rs.getInt("Pv"),rs.getInt("Force"),rs.getInt("Inte"),rs.getString("Rarete")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return my_pieces;
    }
    
    public int New_piece(int Joueur,int pv, int strength, int inte, int level, int exp, String rarete, String type){
        try {
            st.executeUpdate("INSERT INTO piece VALUES (NULL,"+Joueur+",'"+type+"',"+level+","+exp+","+pv+","+strength+","+inte+",'"+rarete+"');");
            rs = st.executeQuery("SELECT MAX(id) as value FROM piece;");
            rs.next();
            return(rs.getInt("value"));
        } catch (SQLException ex) {
            Logger.getLogger(BDD_M.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(-1);
    }
}
