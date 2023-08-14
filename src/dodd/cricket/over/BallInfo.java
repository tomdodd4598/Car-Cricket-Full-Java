package dodd.cricket.over;

import dodd.cricket.Player;
import dodd.cricket.event.BallEvent;

public class BallInfo {
	
	public final Player batter, bowler;
	public final BallEvent event;
	
	public BallInfo(Player batter, Player bowler, BallEvent event) {
		this.batter = batter;
		this.bowler = bowler;
		this.event = event;
	}
}
