package com.zeromus.mcr.filereader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class LanguageReader {
	
	private String langue;
	
	public LanguageReader(String langue) {
		this.langue=langue;
	}
	
	public HashMap<String, String> getSection(String section){
		String fichier ="Media/Language/"+langue+".lang";
		Boolean find=false,end=false;
		HashMap<String, String> textes=new HashMap<String, String>();
			
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null && end==false){
				if(find==false){
					if(ligne.compareTo("/*--"+section+"--*/")==0)
						find=true;
				}
				else{
					if(ligne.compareTo("/*-*/")==0)
						end=true;
					else{
						StringTokenizer st = new StringTokenizer(ligne,"*=*");
						textes.put(st.nextToken(), st.nextToken());
					}
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return textes;		
	}
}
