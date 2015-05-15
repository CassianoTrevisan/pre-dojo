package model;

import java.util.Date;
import java.util.HashMap;

public class Match {
	private Long ID;
	private HashMap<String, Player> players ;
	private Date matchStartTime;
	private Date matchEndTime;
	
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	public HashMap<String, Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}
	public Date getMatchStartTime() {
		return matchStartTime;
	}
	public void setMatchStartTime(Date matchStartTime) {
		this.matchStartTime = matchStartTime;
	}
	public Date getMatchEndTime() {
		return matchEndTime;
	}
	public void setMatchEndTime(Date matchEndTime) {
		this.matchEndTime = matchEndTime;
	}
}
