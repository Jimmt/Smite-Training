package com.jimmt.smitepractice.android;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//import com.chartboost.sdk.CBLocation;
//import com.chartboost.sdk.Chartboost;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.jimmt.smitepractice.IGoogleServices;
import com.jimmt.smitepractice.SmitePractice;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {
	private GameHelper gameHelper;
	private final int REQUEST_CODE_UNUSED = 1001;

	private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-1538406962874141/4749295311";
	public InterstitialAd interstitialAd;
	protected AdView admobView;
	protected View gameView;
	protected RelativeLayout layout;

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

// Chartboost.startWithAppId(this, "55a7f56204b0162f38a2fa4a",
// "1b5177b465f66376edd140ddd31a0dde810ccb10");
// Chartboost.onCreate(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
// initialize(new SmitePractice(this, new AndroidFloatFormatter()), config);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);

		View gameView = createGameView(config);
		layout.addView(gameView);

		setContentView(layout);

		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
			}

			@Override
			public void onAdClosed() {
			}
		});
		
		 
	}


	private View createGameView(AndroidApplicationConfiguration cfg) {
		gameView = initializeForView(new SmitePractice(this, new AndroidFloatFormatter()), cfg);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		gameView.setLayoutParams(params);
		return gameView;
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
//		Chartboost.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
//		Chartboost.onStop(this);
	}

	@Override
	public void onResume() {
		super.onResume();
//		Chartboost.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
//		Chartboost.onPause(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		Chartboost.onDestroy(this);
	}

	@Override
	public void onBackPressed() {
		// If an interstitial is on screen, close it.
//		if (Chartboost.onBackPressed())
//			return;
//		else
			super.onBackPressed();
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
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_id_5), score);
		} else {
			signIn();
		}
	}

	@Override
	public void submitScore1(long score) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_id_1), score);
		} else {
			signIn();
		}
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void showScores() {

		if (isSignedIn()) {
			startActivityForResult(
					Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()),
					REQUEST_CODE_UNUSED);
		} else {
			signIn();
		}
	}

	@Override
	public void showAd() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					if (interstitialAd.isLoaded()) {
						interstitialAd.show();
// Toast.makeText(launcher.getApplicationContext(), "Showing Interstitial",
// Toast.LENGTH_SHORT).show();
					} else {
						//new AdRequest.Builder().addTestDevice("473C39947446CF314412681C567E98A7").build()
						AdRequest interstitialRequest = new AdRequest.Builder().build();
						interstitialAd.loadAd(interstitialRequest);
// Toast.makeText(launcher.getApplicationContext(), "Loading Interstitial",
// Toast.LENGTH_SHORT).show();
					}
				}
			});
		} catch (Exception e) {
		}
	}
}
