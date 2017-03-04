package com.anodev.cdots;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements PlayServices {
	private GameHelper gameHelper;
	private final static int requestCode = 1;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new DotsGame(this), config);
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override public void onSignInFailed() {
			}

			@Override public void onSignInSucceeded() {
			}
		};

		gameHelper.setup(gameHelperListener);
	}

	@Override protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				@Override public void run() {
					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override public void rateGame() {
		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override public void unlockAchievement(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}

	@Override public void submitScore(int highScore, String leaderboardId) {
		if (isSignedIn() == true) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), leaderboardId, highScore);
		}
	}

	@Override public void showAchievement() {
		if (isSignedIn() == true) {
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()),
					requestCode);
		} else {
			signIn();
		}
	}

	@Override public void showScore(String leaderboardId) {
		if (isSignedIn() == true) {
			startActivityForResult(
					Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), leaderboardId),
					requestCode);
		} else {
			signIn();
		}
	}

	@Override public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}
}
