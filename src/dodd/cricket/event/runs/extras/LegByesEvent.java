package dodd.cricket.event.runs.extras;

import dodd.cricket.Innings;

public class LegByesEvent extends ExtrasEvent {
	
	public LegByesEvent(long runs) {
		super(runs, true, true, false);
	}
	
	@Override
	public void act(Innings innings) {
		super.act(innings);
		innings.extras.legByes += runs;
	}
}
