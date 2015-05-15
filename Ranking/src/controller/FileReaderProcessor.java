package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.Match;
import model.Player;
import model.Weapon;

public class FileReaderProcessor {
	static File logFile = new File("ranking.log");
	static private List<Match> matches = new ArrayList<Match>();
	
	public static void main(String args[]){
		try{
			   FileInputStream fis = new FileInputStream(logFile);
			   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			   String strLine;
			   
			   while ((strLine = br.readLine()) != null)   {
			     
				 MatchProcessor.processFileLine(strLine, matches);  
			   }
			   
			   br.close();
			   
			   
			   
			   
			   
			   
			   System.out.println("Ranking Totals");
			   
			   
			   for(Match m : matches){
				   List<Player> playerList = new ArrayList<Player>(m.getPlayers().values());
				   
				   Collections.sort(playerList);//sorting by murder
				   System.out.println("Match "+m.getID());
				   System.out.println("Total Number of Players: "+matches.get(0).getPlayers().size());
				   for(int i = 0; i < playerList.size(); i++){
					   List<Weapon> playerWeapons = new ArrayList<Weapon>(playerList.get(i).getUsedWeapons().values());
					   Collections.sort(playerWeapons);
					   
					   Collections.sort(playerList.get(i).getKillingSequences());
					   int killingSeqSize = playerList.get(i).getKillingSequences().size();
					   killingSeqSize = ( killingSeqSize == 0) ? 1 : killingSeqSize;
					   
					   if(playerWeapons.size() > 0 && i == 0){
						   System.out.println("Winner: "+ playerList.get(i).getName() + "- Streak: "+playerList.get(i).getKillingSequences().get(killingSeqSize -1));
						   System.out.println("-- Preferred Weapon: "+playerWeapons.get(0).getName());
						   System.out.println("-- Total Murders: "+playerList.get(i).getMurderCounter());
						   System.out.println("-- Total Deaths: "+playerList.get(i).getDeathCounter());
						   System.out.println("");
					   }else{
						   System.out.println(playerList.get(i).getName()+ "- Streak: "+playerList.get(i).getKillingSequences().get(killingSeqSize -1));
						   System.out.println("-- Total Murders: "+playerList.get(i).getMurderCounter());
						   System.out.println("-- Total Deaths: "+playerList.get(i).getDeathCounter());
						   System.out.println("");
					   }
				   }
				   System.out.println("");
				   System.out.println("");
				   System.out.println("");
			   }
			  
			 
			} catch (Exception e) {
			     System.err.println("Error: " + e.getMessage());
			}
	}

	public static List<Match> getMatches() {
		return matches;
	}

	public static void setMatches(List<Match> matches) {
		FileReaderProcessor.matches = matches;
	}
}
