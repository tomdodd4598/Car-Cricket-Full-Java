package dodd.cricket.score;

import dodd.*;
import dodd.cricket.Player;
import dodd.cricket.match.Match;

public class BatterScore extends PlayerScore {
	
	public Player wicketBowler;
	
	public BatterScore(Match match, Player batter) {
		super(match, batter);
	}
	
	public String getWicketDescription() {
		if (wicketBowler == null) {
			return Lang.localize("batter.notOut");
		}
		else {
			return Lang.localize("batter.wicket", wicketBowler.shortName);
		}
	}
	
	public double getStrikeRate() {
		return 100 * (double) runs / (double) balls;
	}
	
	@Override
	public String toString() {
		return String.format("%s | %s | %d | %d | %d | %d | %s", player, getWicketDescription(), runs, balls, shotScores.get(4), shotScores.get(6), Helpers.sigFigs(getStrikeRate(), 3));
	}
}
