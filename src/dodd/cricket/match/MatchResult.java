package dodd.cricket.match;

import dodd.Lang;
import dodd.cricket.Innings;

public class MatchResult {
	
	protected final Match match;
	
	public String description;
	
	public MatchResult(Match match) {
		this.match = match;
	}
	
	public void setDescription() {
		long runsA = match.getTotalRunsA(), runsB = match.getTotalRunsB();
		if (runsA == runsB) {
			description = Lang.localize("match.result.tie");
		}
		else if (runsA > runsB) {
			if (match.finishedBattingA) {
				description = Lang.localize("match.result.runs", match.teamA.name, Math.abs(runsA - runsB));
			}
			else {
				Innings currentInnings = match.getCurrentInnings();
				if (currentInnings == null) {
					description = Lang.localize("match.result.innings", match.teamA.name, Math.abs(runsA - runsB));
				}
				else {
					description = Lang.localize("match.result.wickets", match.teamA.name, currentInnings.getRemainingWickets());
				}
			}
		}
		else {
			if (match.finishedBattingB) {
				description = Lang.localize("match.result.runs", match.teamB.name, Math.abs(runsA - runsB));
			}
			else {
				Innings currentInnings = match.getCurrentInnings();
				if (currentInnings == null) {
					description = Lang.localize("match.result.innings", match.teamB.name, Math.abs(runsA - runsB));
				}
				else {
					description = Lang.localize("match.result.wickets", match.teamB.name, currentInnings.getRemainingWickets());
				}
			}
		}
	}
}
