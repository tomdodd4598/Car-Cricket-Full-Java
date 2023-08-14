package dodd.cricket.score;

import dodd.Helpers;
import dodd.cricket.Player;
import dodd.cricket.match.Match;
import dodd.cricket.over.Over;

public class BowlerScore extends PlayerScore {
	
	public int wickets;
	public long overs, maidens, wides, noBalls;
	
	public BowlerScore(Match match, Player bowler) {
		super(match, bowler);
	}
	
	public void finishOver(Over over) {
		balls = 0;
		++overs;
		if (over.maiden) {
			++maidens;
		}
	}
	
	public double getOversFP() {
		return (double) overs + (double) balls / (double) match.settings.ballsPerOver;
	}
	
	public double getEconomy() {
		return (double) runs / getOversFP();
	}
	
	@Override
	public String toString() {
		return String.format("%s | %d.%d | %d | %d | %d | %s | %d | %d", player, overs, balls, maidens, runs, wickets, Helpers.sigFigs(getEconomy(), 3), wides, noBalls);
	}
}
