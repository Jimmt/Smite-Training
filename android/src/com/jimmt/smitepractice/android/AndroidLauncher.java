package com.jimmt.smitepractice.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.jimmt.smitepractice.IGoogleServices;
import com.jimmt.smitepractice.SmitePractice;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {
	private GameHelper gameHelper;
	private int REQUEST_CODE_UNUSED = 1001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInSucceeded() {
			}

			@Override
			public void onSignInFailed() {
			}
		};
		gameHelper.setup(gameHelperListener);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SmitePractice(this), config);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				// @Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				// @Override
				public void run() {
					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame() {
		String str = "https://play.google.com/store/apps/details?id=com.jimmt.smitepractice.android";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void submitScore5(long score) {
		if (isSignedIn() == true) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_id_5), score);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(), getString(R.string.leaderboard_id_5)),
					REQUEST_CODE_UNUSED);
		} else {
			signIn();
		}
	}

	@Override
	public void submitScore1(long score) {
		if (isSignedIn() == true) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_id_1), score);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(), getString(R.string.leaderboard_id_1)),
					REQUEST_CODE_UNUSED);
		} else {
			signIn();
		}
	}


	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void showScores5() {
		if (isSignedIn() == true)
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(), getString(R.string.leaderboard_id_5)),
					REQUEST_CODE_UNUSED);
		else {
			signIn();
		}		
	}

	@Override
	public void showScores1() {
		if (isSignedIn() == true)
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(), getString(R.string.leaderboard_id_1)),
					REQUEST_CODE_UNUSED);
		else {
			signIn();
		}
	}
}
