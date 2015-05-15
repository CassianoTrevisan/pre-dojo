package model;

public class Weapon implements Comparable<Weapon>{
	
	public Weapon(String name){
		this.name = name;
	}
	
	private String name;
	private int usedTimes = 0;
	
	public void incrementWeaponUsed(){
		usedTimes++;
	}

	@Override
	public int compareTo(Weapon o) {
		if(this.usedTimes < o.getUsedTimes()){
			return 1;
		}
		if(this.usedTimes > o.getUsedTimes()){
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

	public int getUsedTimes() {
		return usedTimes;
	}

	public void setUsedTimes(int usedTimes) {
		this.usedTimes = usedTimes;
	}
}
