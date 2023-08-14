package dodd.cricket.match;

import dodd.Lang;

public class MatchStatus {
	
	protected final Match match;
	
	public MatchPosition position = MatchPosition.LEVEL;
	public String description;
	
	public MatchStatus(Match match) {
		this.match = match;
	}
	
	public void updatePosition() {
		long runsBatting = match.getBattingTeamTotalRuns(), runsBowling = match.getBowlingTeamTotalRuns();
		if (runsBatting == runsBowling) {
			position = MatchPosition.LEVEL;
		}
		else if (runsBatting > runsBowling) {
			position = match.batting ? MatchPosition.A_LEADING : MatchPosition.B_LEADING;
		}
		else {
			position = match.batting ? MatchPosition.A_TRAILING : MatchPosition.B_TRAILING;
		}
	}
	
	public void updateDescription() {
		long runsBatting = match.getBattingTeamTotalRuns(), runsBowling = match.getBowlingTeamTotalRuns();
		
		if (match.isFinalMatchInnings()) {
			description = Lang.localize("match.status.require", (match.batting ? match.teamA : match.teamB).name, runsBowling - runsBatting + 1);
		}
		else {
			if (runsBatting == runsBowling) {
				description = Lang.localize("match.status.level");
			}
			else if (runsBatting > runsBowling) {
				description = Lang.localize("match.status.lead", (match.batting ? match.teamA : match.teamB).name, runsBatting - runsBowling);
			}
			else {
				description = Lang.localize("match.status.trail", (match.batting ? match.teamA : match.teamB).name, runsBowling - runsBatting);
			}
		}
	}
}
