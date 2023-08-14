package dodd.cricket.over;

import it.unimi.dsi.fastutil.objects.*;

public class Over {
	
	public final ObjectList<BallInfo> ballInfos = new ObjectArrayList<>();
	
	public boolean maiden = true;
	
	public Over() {
		
	}
	
	public void nextBallInfo(BallInfo info) {
		ballInfos.add(info);
		
		if (info.event.bowlerRunsConceded() > 0) {
			maiden = false;
		}
	}
}
