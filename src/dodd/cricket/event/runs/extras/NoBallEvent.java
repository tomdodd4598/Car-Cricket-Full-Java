package dodd.cricket.event.runs.extras;

import dodd.cricket.Innings;

public class NoBallEvent extends ExtrasEvent {
	
	public NoBallEvent(long runs) {
		super(runs, false, false, true);
	}
	
	@Override
	public void act(Innings innings) {
		super.act(innings);
		innings.bowlerScore.noBalls += runs;
		innings.extras.noBalls += runs;
	}
}
