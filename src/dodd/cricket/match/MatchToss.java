package dodd.cricket.match;

import dodd.Lang;

public class MatchToss {
	
	protected final Match match;
	
	/** true: A, false: B. */
	public boolean winner;
	/** true: bat, false: bowl. */
	public boolean batOrBowl;
	
	public String description;
	
	public MatchToss(Match match) {
		this.match = match;
	}
	
	public void setDescription() {
		if (batOrBowl) {
			description = Lang.localize("match.toss.bat", match.getBattingTeam().name);
		}
		else {
			description = Lang.localize("match.toss.bowl", match.getBowlingTeam().name);
		}
	}
}
