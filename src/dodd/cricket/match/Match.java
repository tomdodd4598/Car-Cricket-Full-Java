package dodd.cricket.match;

import java.util.Random;

import dodd.cricket.*;
import dodd.cricket.event.BallEvent;
import dodd.cricket.over.BallInfo;

public abstract class Match {
	
	public final Random rand = new Random();
	
	public final String name;
	public final MatchToss toss = new MatchToss(this);
	public final MatchSettings settings = new MatchSettings();
	public final MatchStatus status = new MatchStatus(this);
	public final MatchResult result = new MatchResult(this);
	
	public Team teamA, teamB;
	
	public Innings[] inningsA, inningsB;
	public int inningsCountA = -1, inningsCountB = -1;
	
	/** true: A, false: B. */
	public boolean batting;
	public boolean finishedBattingA, finishedBattingB, endOfMatch;
	
	public Match(String name) {
		this.name = name;
	}
	
	public void setNumberOfInnings() {
		inningsA = new Innings[settings.noInnings];
		inningsB = new Innings[settings.noInnings];
	}
	
	public Innings getCurrentInnings() {
		return batting ? inningsA[inningsCountA] : inningsB[inningsCountB];
	}
	
	public void beginCurrentInnings() {
		if (batting) {
			if (!finishedBattingA) {
				inningsA[inningsCountA] = new Innings(this);
			}
		}
		else {
			if (!finishedBattingB) {
				inningsB[inningsCountB] = new Innings(this);
			}
		}
	}
	
	/** true: A, false: B. */
	public void setTossWinner(boolean team) {
		toss.winner = team;
	}
	
	/** true: bat, false: bowl. */
	public void setFirstInnings(boolean batOrBowl) {
		toss.batOrBowl = batOrBowl;
		if (batting = toss.winner ^ batOrBowl) {
			inningsCountA = 0;
		}
		else {
			inningsCountB = 0;
		}
		toss.setDescription();
	}
	
	public void setNextInnings() {
		if (batting) {
			if (isFinalInningsA()) {
				finishedBattingA = true;
			}
			++inningsCountB;
		}
		else {
			if (isFinalInningsB()) {
				finishedBattingB = true;
			}
			++inningsCountA;
		}
		batting = !batting;
	}
	
	public Team getBattingTeam() {
		return batting ? teamA : teamB;
	}
	
	public Team getBowlingTeam() {
		return batting ? teamB : teamA;
	}
	
	protected long getTotalRuns(Innings[] innings) {
		long runs = 0;
		for (int i = 0; i < innings.length && innings[i] != null; ++i) {
			runs += innings[i].score.runs;
		}
		return runs;
	}
	
	public long getTotalRunsA() {
		return getTotalRuns(inningsA);
	}
	
	public long getTotalRunsB() {
		return getTotalRuns(inningsB);
	}
	
	public long getBattingTeamTotalRuns() {
		return getTotalRuns(batting ? inningsA : inningsB);
	}
	
	public long getBowlingTeamTotalRuns() {
		return getTotalRuns(batting ? inningsB : inningsA);
	}
	
	public boolean isFinalInningsA() {
		return inningsCountA == inningsA.length - 1;
	}
	
	public boolean isFinalInningsB() {
		return inningsCountB == inningsB.length - 1;
	}
	
	public boolean isFinalMatchInnings() {
		return (finishedBattingA && isFinalInningsB()) || (finishedBattingB && isFinalInningsA());
	}
	
	protected boolean isEndOfMatch() {
		if (batting) {
			if (finishedBattingA && status.position.isBWinning()) {
				return true;
			}
			else if (finishedBattingB && status.position.isAWinning()) {
				return true;
			}
		}
		else {
			if (finishedBattingB && status.position.isAWinning()) {
				return true;
			}
			else if (finishedBattingA && status.position.isBWinning()) {
				return true;
			}
		}
		return finishedBattingA && finishedBattingB;
	}
	
	public void runBall(BallEvent event) {
		Innings innings = getCurrentInnings();
		innings.currentOver.nextBallInfo(new BallInfo(innings.getCurrentBatter(), innings.bowler, event));
		event.act(innings);
		innings.finishBall();
		
		status.updatePosition();
		
		if (innings.isEndOfOver()) {
			innings.finishOver();
		}
		
		boolean endOfInnings = innings.isEndOfInnings();
		
		if (endOfInnings) {
			setNextInnings();
		}
		else if (innings.wicket) {
			innings.setNextBatter();
			innings.wicket = false;
		}
		
		endOfMatch = isEndOfMatch();
		
		if (endOfInnings && !endOfMatch) {
			beginCurrentInnings();
			status.updateDescription();
		}
	}
	
	public abstract BallEvent nextBallEvent(CarType car);
	
	public abstract double bowlerEnergyLossRate(Player bowler);
	
	public abstract double bowlerEnergyRecoveryRate(Player bowler);
	
	public abstract double getBallModifier(Innings innings, Player batter, Player bowler);
}
