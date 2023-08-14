package dodd.cricket.match;

public enum MatchPosition {
	A_LEADING,
	B_LEADING,
	A_TRAILING,
	B_TRAILING,
	LEVEL;
	
	public boolean isAWinning() {
		switch(this) {
			case A_LEADING:
			case B_TRAILING:
				return true;
			default:
				return false;
		}
	}
	
	public boolean isBWinning() {
		switch(this) {
			case B_LEADING:
			case A_TRAILING:
				return true;
			default:
				return false;
		}
	}
}
