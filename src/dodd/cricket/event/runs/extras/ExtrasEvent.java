package dodd.cricket.event.runs.extras;

import dodd.cricket.event.runs.RunsEvent;

abstract class ExtrasEvent extends RunsEvent {
	
	ExtrasEvent(long runs, boolean battersRun, boolean countTowardsOver, boolean bowlerConcedes) {
		super(runs, countTowardsOver, battersRun, false, bowlerConcedes, true);
	}
}
