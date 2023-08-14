package dodd.cricket;

import dodd.Helpers;
import dodd.cricket.match.Match;
import dodd.cricket.over.Over;
import dodd.cricket.score.*;
import it.unimi.dsi.fastutil.objects.*;

public class Innings {
	
	private final Match match;
	
	public InningsScore score = new InningsScore();
	
	public final Team battingTeam, bowlingTeam;
	
	public Player batter1, batter2, bowler;
	public BatterScore batterScore1, batterScore2;
	public BowlerScore bowlerScore;
	public Extras extras = new Extras();
	
	public Over currentOver;
	public ObjectList<Over> overs = new ObjectArrayList<>();
	
	/** true: 1, false: 2. */
	public boolean batterEnds;
	public boolean bowlingEnd;
	public boolean wicket;
	
	public final Object2ObjectMap<Player, BatterScore> batterScores = new Object2ObjectLinkedOpenHashMap<>();
	public final Object2ObjectMap<Player, BowlerScore> bowlerScores = new Object2ObjectLinkedOpenHashMap<>();
	
	public final Object2ObjectMap<Player, BowlerState> bowlerStates = new Object2ObjectOpenHashMap<>();
	
	public Innings(Match match) {
		this.match = match;
		
		battingTeam = match.getBattingTeam();
		batter1 = battingTeam.players[0];
		batter2 = battingTeam.players[1];
		batterScore1 = getBatterScore(batter1);
		batterScore2 = getBatterScore(batter2);
		
		for (Player bowler : (bowlingTeam = match.getBowlingTeam()).players) {
			bowlerStates.put(bowler, new BowlerState());
		}
		setNextBowler();
		
		currentOver = new Over();
	}
	
	public Player getCurrentBatter() {
		return batterEnds ^ bowlingEnd ? batter1 : batter2;
	}
	
	public BatterScore getCurrentBatterScore() {
		return batterEnds ^ bowlingEnd ? batterScore1 : batterScore2;
	}
	
	public void updateBatterEnds(long runs) {
		batterEnds ^= (runs & 1) == 0;
	}
	
	public boolean isEndOfOver() {
		return bowlerScore.balls == match.settings.ballsPerOver;
	}
	
	public void finishBall() {
		BowlerState state = bowlerStates.get(bowler);
		state.energy = Helpers.clamp(state.energy - match.bowlerEnergyLossRate(bowler), 0, 1);
	}
	
	public void finishOver() {
		bowlerScore.finishOver(currentOver);
		setNextBowler();
		
		overs.add(currentOver);
		currentOver = new Over();
		bowlingEnd = !bowlingEnd;
		
		for (Object2ObjectMap.Entry<Player, BowlerState> entry : bowlerStates.object2ObjectEntrySet()) {
			BowlerState state = entry.getValue();
			state.energy = Helpers.clamp(state.energy + match.bowlerEnergyRecoveryRate(entry.getKey()), 0, 1);
		}
	}
	
	public int getRemainingWickets() {
		return match.settings.teamSize - score.wickets - 1;
	}
	
	public boolean isEndOfInnings() {
		return getRemainingWickets() == 0;
	}
	
	public BatterScore getBatterScore(Player batter) {
		BatterScore score = batterScores.get(batter);
		if (score == null) {
			score = new BatterScore(match, batter);
			batterScores.put(batter, score);
		}
		return score;
	}
	
	public BowlerScore getBowlerScore(Player bowler) {
		BowlerScore score = bowlerScores.get(bowler);
		if (score == null) {
			score = new BowlerScore(match, bowler);
			bowlerScores.put(bowler, score);
		}
		return score;
	}
	
	public void setNextBatter() {
		boolean batter1Out = batterScore1.wicketBowler != null, batter2Out = batterScore2.wicketBowler != null;
		assert !batter1Out || !batter2Out;
		
		if (batter1Out) {
			Player next = match.getBattingTeam().getNextBatter(this);
			batter1 = next;
			batterScore1 = getBatterScore(next);
		}
		if (batter2Out) {
			Player next = match.getBattingTeam().getNextBatter(this);
			batter2 = next;
			batterScore2 = getBatterScore(next);
		}
	}
	
	public void setNextBowler() {
		bowler = match.getBowlingTeam().getNextBowler(this);
		bowlerScore = getBowlerScore(bowler);
	}
}
