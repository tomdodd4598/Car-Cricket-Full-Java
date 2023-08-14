package dodd.cricket.event.runs.extras;

import dodd.cricket.Innings;

public class WideEvent extends ExtrasEvent {
	
	public WideEvent(long runs) {
		super(runs, false, false, true);
	}
	
	@Override
	public void act(Innings innings) {
		super.act(innings);
		innings.bowlerScore.wides += runs;
		innings.extras.wides += runs;
	}
}
