package dodd.cricket.simulation;

import dodd.cricket.Innings;
import dodd.cricket.event.BallEvent;
import dodd.cricket.match.StandardMatch;
import dodd.cricket.score.*;

public class SimulationMatch extends StandardMatch {
	
	public SimulationMatch(String name, boolean modifiers) {
		super(name, modifiers);
	}
	
	@Override
	public void runBall(BallEvent event) {
		Innings innings = getCurrentInnings();
		
		super.runBall(event);
		
		boolean endOfInnings = innings.isEndOfInnings();
		
		if (endOfMatch || endOfInnings) {
			System.out.println(innings.score);
		}
		
		if (endOfMatch || endOfInnings) {
			System.out.println("\nBatting:");
			for (BatterScore score : innings.batterScores.values()) {
				System.out.println(score);
			}
			System.out.println("\nBowling:");
			for (BowlerScore score : innings.bowlerScores.values()) {
				System.out.println(score);
			}
			System.out.println();
		}
		
		if (!endOfMatch && endOfInnings) {
			System.out.println(status.description);
			System.out.println();
		}
	}
}
