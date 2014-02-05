package com.zeromus.mcr.commons;

public class Compte {

	private int id_player;
	private String login;
	private String password;
	private int points;
	
	
	public Compte(int id_player, String login, String password, int points) {
		super();
		this.id_player = id_player;
		this.login = login;
		this.password = password;
		this.points = points;
	}
	
	public int getId_player() {
		return id_player;
	}
	public void setId_player(int id_player) {
		this.id_player = id_player;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
