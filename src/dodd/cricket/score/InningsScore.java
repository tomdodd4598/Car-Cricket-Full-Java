package dodd.cricket.score;

import dodd.Lang;

public class InningsScore {
	
	public long runs;
	public int wickets;
	
	public InningsScore() {
		
	}
	
	@Override
	public String toString() {
		return Lang.localize("innings.score", runs, wickets);
	}
}
