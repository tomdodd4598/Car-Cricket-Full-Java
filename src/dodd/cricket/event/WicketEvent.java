package dodd.cricket.event;

import dodd.cricket.Innings;
import dodd.cricket.score.BatterScore;

public class WicketEvent extends BallEvent {
	
	public WicketEvent() {
		super(true, false);
	}
	
	@Override
	public void act(Innings innings) {
		innings.wicket = true;
		++innings.score.wickets;
		++innings.bowlerScore.wickets;
		
		BatterScore batterScore = innings.getCurrentBatterScore();
		batterScore.wicketBowler = innings.bowler;
		batterScore.addScore(0, true, false);
		innings.bowlerScore.addScore(0, true, false);
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
