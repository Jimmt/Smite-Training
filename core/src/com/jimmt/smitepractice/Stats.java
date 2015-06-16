package com.jimmt.smitepractice;

import java.util.ArrayList;

public class Stats {
	int smitesHit, totalSmites;
	float totalPercent, totalDiff;

	public Stats(int totalSmites) {
		this.totalSmites = totalSmites;
	}

	public void logSmite(boolean hit, float percent, float diff) {
		if (hit) {
			smitesHit++;
		}
		totalPercent += percent;
		totalDiff += diff;
	}

	public float getAverageDiff() {
		return totalDiff / totalSmites;
	}

	public float getAveragePercent() {
		return totalPercent / (int) totalSmites;
	}

}
