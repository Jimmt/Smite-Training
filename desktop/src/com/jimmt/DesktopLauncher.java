package com.jimmt;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.jimmt.smitepractice.DesktopGoogleServices;
import com.jimmt.smitepractice.SmitePractice;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Smite Training");
		new Lwjgl3Application(new SmitePractice(new DesktopGoogleServices(), new DesktopFloatFormatter()), config);
	}
}
