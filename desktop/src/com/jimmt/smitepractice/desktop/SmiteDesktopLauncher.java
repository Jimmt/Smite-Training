package com.jimmt.smitepractice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jimmt.smitepractice.DesktopGoogleServices;
import com.jimmt.smitepractice.SmitePractice;

public class SmiteDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 720;
		new LwjglApplication(new SmitePractice(new DesktopGoogleServices(), new DesktopFloatFormatter()), config);
	}
}
