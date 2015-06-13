package com.jimmt.smitepractice;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionsDialog extends Dialog {

	public OptionsDialog() {
		super("", UI.skin);

		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());

		boolean enableSound = Prefs.prefs.getBoolean("sound");
		int index = enableSound ? 0 : 1;
		SmitePractice.soundManager.playSound = enableSound;
		
		final SelectButton soundSelect = new SelectButton(index, "On", "Off");

		for (int i = 0; i < soundSelect.buttons.size; i++) {
			final int temp = i;

			soundSelect.buttons.get(i).addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					boolean playSound = String.valueOf(soundSelect.buttons.get(temp).getText()).equals("On");
					Prefs.prefs.putBoolean("sound",
							playSound);
					Prefs.prefs.flush();
					
					SmitePractice.soundManager.playSound = playSound;
				}
			});
		}

		Label sound = new Label("Sound: ", UI.smallLabelStyle);

		TextButton backButton = new TextButton("Back", UI.startButtonStyle);
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				hide();
			}
		});

		Table table = getContentTable();
		table.add(sound).padRight(20f);
		table.add(soundSelect).row();
		table.add(backButton).padTop(50f).colspan(2).width(backButton.getWidth() / 2)
				.height(backButton.getHeight() / 2);

	}
}
