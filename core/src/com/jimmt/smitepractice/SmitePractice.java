package com.jimmt.smitepractice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SmitePractice extends Game {
	public static SoundManager soundManager;
	public static IGoogleServices services;
	public static FloatFormatter formatter;
	public static int gamesCount = 0;
	public static int roundsCount = 0;

	public SmitePractice(IGoogleServices services, FloatFormatter formatter){
		SmitePractice.services = services;
		SmitePractice.formatter = formatter;
	}

	@Override
	public void create() {
		soundManager = new SoundManager();
		soundManager.loadSound("smite", Gdx.files.internal("sfx/smite.wav"));
		UI.initialize();
		Prefs.initialize();
		
		boolean enableSound = Prefs.prefs.getBoolean("sound");
		SmitePractice.soundManager.playSound = enableSound;
		
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
