package dodd.cricket.event.runs.extras;

import dodd.cricket.Innings;

public class PenaltyEvent extends ExtrasEvent {
	
	public PenaltyEvent(long runs) {
		super(runs, false, true, false);
	}
	
	@Override
	public void act(Innings innings) {
		super.act(innings);
		innings.extras.penalty += runs;
	}
}
