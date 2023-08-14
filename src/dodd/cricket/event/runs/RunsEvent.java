package dodd.cricket.event.runs;

import dodd.cricket.Innings;
import dodd.cricket.event.BallEvent;

public abstract class RunsEvent extends BallEvent {
	
	public final long runs;
	public final boolean batterScores, bowlerConcedes, extras;
	
	public RunsEvent(long runs, boolean countTowardsOver, boolean battersRun, boolean batterScores, boolean bowlerConcedes, boolean extras) {
		super(countTowardsOver, battersRun);
		this.runs = runs;
		this.batterScores = batterScores;
		this.bowlerConcedes = bowlerConcedes;
		this.extras = extras;
	}
	
	@Override
	public void act(Innings innings) {
		innings.score.runs += runs;
		long batterRuns = batterRunsScored();
		innings.getCurrentBatterScore().addScore(batterRuns, countTowardsOver, batterScores);
		innings.bowlerScore.addScore(bowlerRunsConceded(), countTowardsOver, batterScores);
		innings.extras.total += extraRuns();
		innings.updateBatterEnds(battersRun ? batterRuns : 0);
	}
	
	@Override
	public long batterRunsScored() {
		return batterScores ? runs : 0;
	}
	
	@Override
	public long bowlerRunsConceded() {
		return bowlerConcedes ? runs : 0;
	}
	
	@Override
	public long extraRuns() {
		return extras ? runs : 0;
	}
}
