package dodd.cricket.match;

import dodd.Helpers;
import dodd.cricket.*;
import dodd.cricket.event.*;
import dodd.cricket.event.runs.ShotEvent;
import dodd.cricket.event.runs.extras.*;

public class StandardMatch extends Match {
	
	public static final BallEvent DOT = new DotEvent(),
			WICKET = new WicketEvent(),
			SINGLE = new ShotEvent(1),
			DOUBLE = new ShotEvent(2),
			TRIPLE = new ShotEvent(3),
			FOUR = new ShotEvent(4),
			FIVE = new ShotEvent(5),
			SIX = new ShotEvent(6),
			BYE = new ByesEvent(1),
			FOUR_BYES = new ByesEvent(4),
			LEG_BYE = new LegByesEvent(1),
			FOUR_LEG_BYES = new LegByesEvent(4),
			WIDE = new WideEvent(1),
			FOUR_WIDES = new WideEvent(4),
			NO_BALL = new NoBallEvent(1),
			FOUR_NO_BALLS = new NoBallEvent(4),
			FIVE_PENALTY = new PenaltyEvent(5);
	
	public final boolean modifiers;
	
	public StandardMatch(String name, boolean modifiers) {
		super(name);
		this.modifiers = modifiers;
	}
	
	@Override
	public BallEvent nextBallEvent(CarType car) {
		Innings innings = getCurrentInnings();
		if (modifiers) {
			double modifier = getBallModifier(innings, innings.getCurrentBatter(), innings.bowler);
			switch (car) {
				case WHITE:
					if (modifier > 0) {
						return SINGLE;
					}
					else {
						return DOT;
					}
				case BLACK:
					return DOT;
				case RED:
					if (modifier > 0) {
						double d = rand.nextDouble() * modifier;
						if (d > 0.5) {
							return FOUR;
						}
						else if (d > 0.25) {
							return DOT;
						}
					}
					return WICKET;
				case BLUE:
					if (modifier > 0) {
						return FOUR;
					}
					else {
						return SINGLE;
					}
				case GREEN:
					if (modifier > 0) {
						return DOUBLE;
					}
					else {
						return SINGLE;
					}
				case ORANGE:
					if (modifier > 0) {
						return TRIPLE;
					}
					else {
						return DOUBLE;
					}
				case YELLOW:
					if (modifier > 0) {
						return SIX;
					}
					else {
						return FOUR;
					}
				default:
					return otherBallEvent();
			}
		}
		else {
			switch (car) {
				case WHITE:
					return SINGLE;
				case BLACK:
					return SINGLE;
				case RED:
					return WICKET;
				case BLUE:
					return FOUR;
				case GREEN:
					return DOUBLE;
				case ORANGE:
					return TRIPLE;
				case YELLOW:
					return SIX;
				default:
					return otherBallEvent();
			}
		}
	}
	
	public BallEvent otherBallEvent() {
		int i = rand.nextInt(100);
		if (i < 25) {
			return BYE;
		}
		else if (i < 50) {
			return LEG_BYE;
		}
		else if (i < 70) {
			return WIDE;
		}
		else if (i < 90) {
			return NO_BALL;
		}
		else if (i < 93) {
			return FOUR_BYES;
		}
		else if (i < 95) {
			return FOUR_LEG_BYES;
		}
		else if (i < 97) {
			return FOUR_WIDES;
		}
		else if (i < 99) {
			return FOUR_NO_BALLS;
		}
		else {
			return FIVE_PENALTY;
		}
	}
	
	@Override
	public double bowlerEnergyLossRate(Player bowler) {
		return 0.0125 + 0.0075 * bowler.aggression;
	}
	
	@Override
	public double bowlerEnergyRecoveryRate(Player bowler) {
		return 0.0025 * settings.ballsPerOver;
	}
	
	@Override
	public double getBallModifier(Innings innings, Player batter, Player bowler) {
		return Helpers.clamp(0.845 - 0.84 * rand.nextDouble() + batter.getBattingAbility(innings) - (0.95 + 0.1 * bowler.aggression * rand.nextDouble()) * bowler.getBowlingAbility(innings), -1, 1);
	}
}
