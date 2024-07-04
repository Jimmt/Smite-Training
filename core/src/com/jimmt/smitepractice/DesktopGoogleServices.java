package com.jimmt.smitepractice;

public class DesktopGoogleServices implements IGoogleServices {
	@Override
	public void signIn() {
		System.out.println("DesktopGoogleServies: signIn()");
	}

	@Override
	public void signOut() {
		System.out.println("DesktopGoogleServies: signOut()");
	}

	@Override
	public void rateGame() {
		System.out.println("DesktopGoogleServices: rateGame()");
	}

	@Override
	public void submitScore5(long score) {
		System.out.println("DesktopGoogleServies: submitScore5(" + score + ")");
	}
	
	@Override
	public void submitScore1(long score) {
		System.out.println("DesktopGoogleServies: submitScore1(" + score + ")");
	}

	@Override
	public void showScores() {
		System.out.println("DesktopGoogleServies: showScores()");
	}

	@Override
	public boolean isSignedIn() {
		System.out.println("DesktopGoogleServies: isSignedIn()");
		return false;
	}

	@Override
	public void showAd() {
		System.out.println("DesktopGoogleServices: showAd()");
		
	}
}
