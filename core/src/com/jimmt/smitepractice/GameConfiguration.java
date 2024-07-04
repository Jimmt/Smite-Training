package com.jimmt.smitepractice;

public class GameConfiguration {
	String objective;
	int rounds, players;

	public GameConfiguration(String objectiveStr, String roundsStr, int players) {
		objective = objectiveStr;
		if (roundsStr.equals("Infinite")) {
			this.rounds = -1;
		} else {
			this.rounds = Integer.valueOf(roundsStr);
		}
		this.players = players;
	}
}

