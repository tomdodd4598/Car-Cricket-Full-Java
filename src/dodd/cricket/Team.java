package dodd.cricket;

public class Team {
	
	public final String name;
	
	public final Player[] players;
	
	public Team(String name, int size) {
		this.name = name;
		players = new Player[size];
	}
	
	public Player getNextBatter(Innings innings) {
		return players[innings.score.wickets + 1];
	}
	
	public Player getNextBowler(Innings innings) {
		Player next = null;
		double best = Double.NEGATIVE_INFINITY;
		for (Player player : players) {
			double ability = player.getBowlingAbility(innings);
			if (player != innings.bowler && ability > best) {
				best = ability;
				next = player;
			}
		}
		return next;
	}
}
