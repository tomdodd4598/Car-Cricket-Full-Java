package dodd.cricket.score;

import dodd.cricket.Player;
import dodd.cricket.match.Match;
import it.unimi.dsi.fastutil.longs.*;

abstract class PlayerScore {
	
	public final Match match;
	
	public final Player player;
	
	public long runs;
	public int balls;
	
	public Long2LongMap shotScores = new Long2LongOpenHashMap();
	
	PlayerScore(Match match, Player player) {
		this.match = match;
		this.player = player;
	}
	
	public void addScore(long runs, boolean countTowardsOver, boolean batterScores) {
		this.runs += runs;
		if (countTowardsOver) {
			++balls;
		}
		if (batterScores) {
			shotScores.put(runs, shotScores.get(runs) + 1);
		}
	}
}
