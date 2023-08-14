package dodd.cricket.simulation;

import dodd.Helpers;
import dodd.cricket.*;
import dodd.cricket.match.Match;

public class Simulation {
	
	private Match match;
	
	public Simulation() {
		
	}
	
	public Match simulate() {
		match = new SimulationMatch("Simulation", true);
		
		match.settings.teamSize = 11;
		match.settings.ballsPerOver = 6;
		match.settings.noInnings = 2;
		
		match.setNumberOfInnings();
		
		int teamSize = match.settings.teamSize;
		match.teamA = new Team("Team A", teamSize);
		match.teamB = new Team("Team B", teamSize);
		
		double batting = 0.75, bowling = 0.25;
		for (int i = 0; i < teamSize; ++i) {
			match.teamA.players[i] = new Player("Player " + (i + 1) + "A", (i + 1) + "A", batting, bowling, Helpers.clamp(0.5 + 0.25 * match.rand.nextGaussian(), 0, 1));
			match.teamB.players[i] = new Player("Player " + (i + 1) + "B", (i + 1) + "B", batting, bowling, Helpers.clamp(0.5 + 0.25 * match.rand.nextGaussian(), 0, 1));
			double d = 0.55 / teamSize;
			batting -= d;
			bowling += d;
		}
		
		match.setTossWinner(match.rand.nextBoolean());
		match.setFirstInnings(match.rand.nextBoolean());
		
		System.out.println(match.toss.description);
		System.out.println();
		
		match.beginCurrentInnings();
		
		while (!match.endOfMatch) {
			match.runBall(match.nextBallEvent(nextCar()));
		}
		
		match.result.setDescription();
		System.out.println(match.result.description);
		
		return match;
	}
	
	public CarType nextCar() {
		double d = match.rand.nextDouble();
		if (d < 0.493) {
			return CarType.WHITE;
		}
		else if (d < 0.692) {
			return CarType.BLACK;
		}
		else if (d < 0.782) {
			return CarType.RED;
		}
		else if (d < 0.791 + 0.01) {
			return CarType.GREEN;
		}
		else if (d < 0.96 + 0.01) {
			return CarType.BLUE;
		}
		else if (d < 0.974 + 0.01) {
			return CarType.ORANGE;
		}
		else if (d < 0.978 + 0.01) {
			return CarType.YELLOW;
		}
		else {
			return CarType.OTHER;
		}
	}
}
