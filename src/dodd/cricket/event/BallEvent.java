package dodd.cricket.event;

import dodd.cricket.Innings;

public abstract class BallEvent {
	
	public final boolean countTowardsOver, battersRun;
	
	public BallEvent(boolean countTowardsOver, boolean battersRun) {
		this.countTowardsOver = countTowardsOver;
		this.battersRun = battersRun;
	}
	
	public abstract void act(Innings innings);
	
	public abstract long batterRunsScored();
	
	public abstract long bowlerRunsConceded();
	
	public abstract long extraRuns();
}
