package dodd.cricket.event.runs;

public class ShotEvent extends RunsEvent {
	
	public ShotEvent(long runs) {
		super(runs, true, true, true, true, false);
	}
}
