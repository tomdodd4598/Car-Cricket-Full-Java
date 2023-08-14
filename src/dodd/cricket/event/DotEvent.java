package dodd.cricket.event;

import dodd.cricket.Innings;

public class DotEvent extends BallEvent {
	
	public DotEvent() {
		super(true, false);
	}
	
	@Override
	public void act(Innings innings) {
		innings.getCurrentBatterScore().addScore(batterRunsScored(), countTowardsOver, true);
		innings.bowlerScore.addScore(bowlerRunsConceded(), countTowardsOver, true);
	}
	
	@Override
	public long batterRunsScored() {
		return 0;
	}
	
	@Override
	public long bowlerRunsConceded() {
		return 0;
	}
	
	@Override
	public long extraRuns() {
		return 0;
	}
}
