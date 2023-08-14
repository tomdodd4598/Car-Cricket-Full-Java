package dodd.cricket;

public class Player {
	
	public final String name, shortName;
	
	public double batting = 0.5, bowling = 0.5, aggression = 0.5;
	
	public Player(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}
	
	public Player(String name, String shortName, double batting, double bowling, double aggression) {
		this(name, shortName);
		this.batting = batting;
		this.bowling = bowling;
		this.aggression = aggression;
	}
	
	public double getBattingAbility(Innings innings) {
		return batting;
	}
	
	public double getBowlingAbility(Innings innings) {
		return bowling * innings.bowlerStates.get(this).energy;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Player && name.equals(((Player) other).name);
	}
}
