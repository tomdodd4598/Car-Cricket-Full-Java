package dodd.cricket.event.runs.extras;

import dodd.cricket.Innings;

public class ByesEvent extends ExtrasEvent {
	
	public ByesEvent(long runs) {
		super(runs, true, true, false);
	}
	
	@Override
	public void act(Innings innings) {
		super.act(innings);
		innings.extras.byes += runs;
	}
}
