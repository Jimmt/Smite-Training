package com.jimmt.smitepractice;

public class GameConfiguration {
	String objective;
	int rounds;

	public GameConfiguration(String objectiveStr, String roundsStr) {
		objective = objectiveStr;
		if (roundsStr.equals("Infinite")) {
			this.rounds = -1;
		} else {
			this.rounds = Integer.valueOf(roundsStr);
		}
	}
}

