package com.jimmt.smitepractice;

public interface IGoogleServices {
	public void signIn();

	public void signOut();

	public void rateGame();

	public void submitScore5(long score);
	
	public void submitScore1(long score);

	public void showScores5();
	
	public void showScores1();

	public boolean isSignedIn();
}
