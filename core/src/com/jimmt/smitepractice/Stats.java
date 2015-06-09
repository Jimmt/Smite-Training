package com.jimmt.smitepractice;

public class Stats {
	int smitesHit, totalSmites;

	public Stats(int totalSmites) {
		this.totalSmites = totalSmites;
	}
	
	public void logSmiteHit(){
		smitesHit++;
	}

}
