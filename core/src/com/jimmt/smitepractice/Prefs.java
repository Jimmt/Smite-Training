package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
	public static Preferences prefs;

	public static void initialize() {
		prefs = Gdx.app.getPreferences("settings");

		if (!prefs.contains("sound")) {
			prefs.putBoolean("sound", true);
		}
		if(!prefs.contains("objective")){
			prefs.putInteger("objective", 0);
			prefs.putInteger("rounds", 0);
		}

		prefs.flush();
	}
}
