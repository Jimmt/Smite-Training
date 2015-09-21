package com.jimmt.smitepractice;

public interface IGoogleServices {
	public void signIn();

	public void signOut();

	public void rateGame();

	public void submitScore5(long score);
	
	public void submitScore1(long score);

	public void showScores();
	
	public void showAd();

	public boolean isSignedIn();
}
