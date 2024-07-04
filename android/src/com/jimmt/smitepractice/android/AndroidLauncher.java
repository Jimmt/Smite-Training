package com.jimmt.smitepractice.android;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.LoggingLevel;
import com.chartboost.sdk.ads.Interstitial;
import com.chartboost.sdk.ads.Rewarded;
import com.chartboost.sdk.ads.Banner;
//import com.google.example.games.basegameutils.GameHelper;
//import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.chartboost.sdk.callbacks.InterstitialCallback;
import com.chartboost.sdk.events.CacheError;
import com.chartboost.sdk.events.CacheEvent;
import com.chartboost.sdk.events.ClickError;
import com.chartboost.sdk.events.ClickEvent;
import com.chartboost.sdk.events.DismissEvent;
import com.chartboost.sdk.events.ImpressionEvent;
import com.chartboost.sdk.events.ShowError;
import com.chartboost.sdk.events.ShowEvent;
import com.jimmt.smitepractice.GameScreen;
import com.jimmt.smitepractice.IGoogleServices;
import com.jimmt.smitepractice.SmitePractice;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {
    //	private GameHelper gameHelper;
    private final int REQUEST_CODE_UNUSED = 1001;

    protected View gameView;
    protected RelativeLayout layout;

    Interstitial chartboostInterstitial;
    private SmitePractice smitePractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
//		gameHelper.enableDebugLog(false);
//
//		GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
//			@Override
//			public void onSignInSucceeded() {
//			}
//
//			@Override
//			public void onSignInFailed() {
//			}
//		};
//		gameHelper.setup(gameHelperListener);

// Chartboost.startWithAppId(this, "55a7f56204b0162f38a2fa4a",
// "1b5177b465f66376edd140ddd31a0dde810ccb10");
// Chartboost.onCreate(this);

        initChartboost();

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
    }

    private void initChartboost() {
        Chartboost.startWithAppId(this, "55a7f56204b0162f38a2fa4a",
                "1b5177b465f66376edd140ddd31a0dde810ccb10", startError -> {
                    if (startError == null) {
                        Log.d(AndroidLauncher.class.getName(), "Chartboost SDK initialized");
                        chartboostInterstitial = new Interstitial("interstitial", new InterstitialCallback() {
                            @Override
                            public void onAdDismiss(DismissEvent dismissEvent) {
                                System.out.println("onAdDismiss");
                            }

                            @Override
                            public void onAdLoaded(CacheEvent cacheEvent, @Nullable CacheError cacheError) {
                                System.out.println("onAdLoaded " + cacheError);
                                // after this is successful ad can be shown
                                chartboostInterstitial.show();
                            }

                            @Override
                            public void onAdRequestedToShow(ShowEvent showEvent) {
                                System.out.println("onAdRequestedToShow ");
                            }

                            @Override
                            public void onAdShown(ShowEvent showEvent, @Nullable ShowError showError) {
                                System.out.println("onAdShown " + showError);
                            }

                            @Override
                            public void onAdClicked(ClickEvent clickEvent, @Nullable ClickError clickError) {
                                System.out.println("onAdClicked " + clickError);
                            }

                            @Override
                            public void onImpressionRecorded(ImpressionEvent impressionEvent) {
                                System.out.println("onImpressionRecorded");
                            }
                        }, null);
                    } else {
                        Log.d(AndroidLauncher.class.getName(), "Chartboost SDK initialization error: " + startError.getCode().name());
                    }
                });
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        this.smitePractice = new SmitePractice(this, new AndroidFloatFormatter());
        gameView = initializeForView(smitePractice, cfg);
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
//		gameHelper.onStart(this);
//		Chartboost.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//		gameHelper.onStop();
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
//		 If an interstitial is on screen, close it.
//		if (Chartboost.onBackPressed())
//			return;
//		else
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//		gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void signIn() {
//		try {
//			runOnUiThread(new Runnable() {
//				// @Override
//				public void run() {
//					gameHelper.beginUserInitiatedSignIn();
//				}
//			});
//		} catch (Exception e) {
//			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
//		}
    }

    @Override
    public void signOut() {
//		try {
//			runOnUiThread(new Runnable() {
//				// @Override
//				public void run() {
//					gameHelper.signOut();
//				}
//			});
//		} catch (Exception e) {
//			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
//		}
    }

    @Override
    public void rateGame() {
        String str = "https://play.google.com/store/apps/details?id=com.jimmt.smitepractice.android";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void submitScore5(long score) {
//		if (isSignedIn()) {
//			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
//					getString(R.string.leaderboard_id_5), score);
//		} else {
//			signIn();
//		}
    }

    @Override
    public void submitScore1(long score) {
//		if (isSignedIn()) {
//			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
//					getString(R.string.leaderboard_id_1), score);
//		} else {
//			signIn();
//		}
    }

    @Override
    public boolean isSignedIn() {
//		return gameHelper.isSignedIn();
        return false;
    }

    @Override
    public void showScores() {
//
//		if (isSignedIn()) {
//			startActivityForResult(
//					Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()),
//					REQUEST_CODE_UNUSED);
//		} else {
//			signIn();
//		}
    }

    @Override
    public void showAd() {
        if (chartboostInterstitial == null) {
            return;
        }
        System.out.println("showAd " + chartboostInterstitial.isCached());
        chartboostInterstitial.cache();

        Screen screen = smitePractice.getScreen();
        if (screen instanceof GameScreen) {
            ((GameScreen) screen).dialog.hideButtonsTemporarily();
        }
    }
}
