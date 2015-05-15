package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import model.Match;
import model.Player;
import model.Weapon;

public class MatchProcessor {
	
	private static final String NEW_MATCH_TOKEN = "New match";
	private static final String WORLD_TOKEN = "<WORLD>";
	private static final String MATCH_END_TOKEN = "Match";
	
	static public void processFileLine(String strLine, List<Match> matches) throws ParseException{
		String strLineArray[] = strLine.split("-");
		String strDate = strLineArray[0].trim();
		String strInfo = strLineArray[1].trim();
		int lastMatch = matches.size() - 1;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy kk:mm:ss");
		
		if(strInfo.startsWith(NEW_MATCH_TOKEN)){//new match begins
			//System.out.println("new match ok");
			Long matchId = Long.parseLong(strInfo.split(" ")[2]);
			
			
			Match match = new Match();
			match.setMatchStartTime(dateFormat.parse(strDate));
			match.setID(matchId);
			
			matches.add(match);
			//System.out.println(matches.get(matches.size()-1).getMatchStartTime());
		
		}
		else if(strInfo.startsWith(WORLD_TOKEN)){//world has killed somebody
			String killedPlayerName = strInfo.split(" ")[2];//this will get killed's player name
			
			
			Player killedPlayerObj = null;
			
			Boolean arePlayersNull = new Boolean(matches.get(lastMatch).getPlayers() == null);
			
			if(!arePlayersNull){
				//try to get the players, but they may not exist. Thats fine.
				killedPlayerObj = matches.get(lastMatch).getPlayers().get(killedPlayerName);
			}
						
			applyDeadRules(killedPlayerObj, lastMatch, killedPlayerName, matches);
		
		}
		else if(strInfo.startsWith(MATCH_END_TOKEN)){//world has killed somebody
			matches.get(lastMatch).setMatchEndTime(dateFormat.parse(strDate));
		}
		else{//a player killed another player
			
			String killerPlayerName = strInfo.split(" ")[0];//this will get player's name
			String killedPlayerName = strInfo.split(" ")[2];//this will get killed's player name
			String weaponUsed = strInfo.split(" ")[4];
			
			
			Player killerPlayerObj = null;
			Player killedPlayerObj = null;
			
			Boolean isPlayersNull = new Boolean(matches.get(lastMatch).getPlayers() == null);
			
			if(!isPlayersNull){
				//try to get the players, but they may not exist. Thats fine.
				killerPlayerObj = matches.get(lastMatch).getPlayers().get(killerPlayerName);
				killedPlayerObj = matches.get(lastMatch).getPlayers().get(killedPlayerName);
			}
			
			applyKillerRules(killerPlayerObj, lastMatch, killerPlayerName, isPlayersNull, matches, weaponUsed);
			
			applyDeadRules(killedPlayerObj, lastMatch, killedPlayerName, matches);
			
			
		}
		
	}
	
	static private void applyKillerRules(Player killer, int lastMatch, String killerPlayerName, Boolean isPlayersNull, List<Match> matches, String weaponUsedName){
		if(killer != null){
			killer.incrementMurder();
		}
		else{
			killer = new Player(killerPlayerName);	
			if(isPlayersNull){
				matches.get(lastMatch).setPlayers(new HashMap<String, Player>());
			}
			matches.get(lastMatch).getPlayers().put(killerPlayerName, killer);
			killer.incrementMurder();
		}
		
		Weapon weaponObj = killer.getUsedWeapons().get(weaponUsedName);
		
		if(weaponObj != null){
			weaponObj.incrementWeaponUsed();
		}
		else{
			weaponObj = new Weapon(weaponUsedName);
			weaponObj.incrementWeaponUsed();
			killer.getUsedWeapons().put(weaponUsedName, weaponObj);
		}
		
		int killingSequenceSize = killer.getKillingSequences().size();
		if(killingSequenceSize == 0){
			killer.getKillingSequences().add(0, new Integer(1));
		}
		else{
			Integer killSize = killer.getKillingSequences().get(killingSequenceSize -1);
			killSize++;
			killer.getKillingSequences().remove(killingSequenceSize -1);
			killer.getKillingSequences().add(killingSequenceSize -1, killSize);
		}
		
		
		
	}
	
	static private void applyDeadRules(Player killedPlayerObj, int lastMatch, String killedPlayerName, List<Match> matches){
		if(killedPlayerObj != null){
			killedPlayerObj.incrementDeath();
		}
		else{
			killedPlayerObj = new Player(killedPlayerName);
			
			matches.get(lastMatch).getPlayers().put(killedPlayerName, killedPlayerObj);
			killedPlayerObj.incrementDeath();
		}
		
		killedPlayerObj.getKillingSequences().add(new Integer(0));
	}
	
	
	static public void identifyStreak(List<Match> matches){
		
	}
}
