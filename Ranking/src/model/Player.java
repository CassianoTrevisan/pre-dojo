package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Comparable<Player>{
	
	public Player(String name){
		this.name = name;
	}
	
	private String name;
	private HashMap <String, Weapon> usedWeapons = new HashMap<String, Weapon>();
	private List<Integer> killingSequences = new ArrayList<Integer>();
	private int deathCounter = 0;
	private int murderCounter = 0;
	
	public int compareTo(Player player) {
        if (this.murderCounter < player.getMurderCounter()) {
            return 1;
        }
        if (this.murderCounter > player.getMurderCounter()) {
            return -1;
        }
        return 0;
    }
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeathCounter() {
		return deathCounter;
	}

	public void setDeathCounter(int deathCounter) {
		this.deathCounter = deathCounter;
	}

	public int getMurderCounter() {
		return murderCounter;
	}

	public void setMurderCounter(int murderCounter) {
		this.murderCounter = murderCounter;
	}

	
	
	public void incrementMurder(){
		murderCounter++;
	}
	
	public void incrementDeath(){
		deathCounter++;
	}



	public HashMap<String, Weapon> getUsedWeapons() {
		return usedWeapons;
	}



	public void setUsedWeapons(HashMap<String, Weapon> usedWeapons) {
		this.usedWeapons = usedWeapons;
	}

	public List<Integer> getKillingSequences() {
		return killingSequences;
	}



	public void setKillingSequences(List<Integer> killingSequences) {
		this.killingSequences = killingSequences;
	}
}
