package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entries;

public class SoundManager {
	ObjectMap<String, Sound> sounds;
	ObjectMap<String, Music> musics;

	public boolean playSound;

	public SoundManager() {
		sounds = new ObjectMap<String, Sound>();
		musics = new ObjectMap<String, Music>();
	}


	public void play(String name) {
		if (playSound)
			((Sound) sounds.get(name)).play();
	}

	public void play(String name, float volume) {
		if (playSound)
			((Sound) sounds.get(name)).play(volume);
	}

	public void loopMusic(String name, float volume) {
		if (playSound) {
			((Music) musics.get(name)).setLooping(true);
			((Music) musics.get(name)).setVolume(volume);
			((Music) musics.get(name)).play();
		}
	}

	public void loop(String name) {
		if (playSound)
			((Sound) sounds.get(name)).loop();
	}

	public void loadSound(String name, FileHandle file) {
		Sound sound = Gdx.audio.newSound(file);
		sounds.put(name, sound);
	}

	public void loadMusic(String name, FileHandle file) {
		Music music = Gdx.audio.newMusic(file);
		musics.put(name, music);
	}

	public void stopMusic() {
		Entries<String, Music> entries = musics.entries();
		while (entries.hasNext()) {
			entries.next().value.pause();
		}
	}

}
