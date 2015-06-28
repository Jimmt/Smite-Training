package com.jimmt.smitepractice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SmitePractice extends Game {
	public static SoundManager soundManager;
	public static IGoogleServices services;
	public static int adHeight;
	
	public SmitePractice(IGoogleServices services, int adHeight){
		this.services = services;
		this.adHeight = adHeight;
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
