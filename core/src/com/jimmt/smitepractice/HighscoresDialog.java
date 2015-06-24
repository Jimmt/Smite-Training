package com.jimmt.smitepractice;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HighscoresDialog extends Dialog {
	SelectButton select;
	TextButton view, back;
	Label title;

	public HighscoresDialog() {
		super("", UI.skin);

		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());

		title = new Label("View Highscores", UI.smallLabelStyle);
		select = new SelectButton(0, "1 round", "5+ rounds");
		view = new TextButton("View", UI.startButtonStyle);
		view.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (select.getSelectionIndex() == 0) {
					SmitePractice.services.showScores1();
				} else if (select.getSelectionIndex() == 1) {
					SmitePractice.services.showScores5();
				}
			}
		});
		back = new TextButton("Back", UI.startButtonStyle);
		back.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				hide();
			}
		});

		getContentTable().add(title).colspan(2).row();
		getContentTable().add(select).colspan(2).row();
		getContentTable().add(back).width(back.getWidth() / 2)
		.height(back.getHeight() / 2).padTop(50f);
		getContentTable().add(view).width(back.getWidth() / 2)
		.height(back.getHeight() / 2).padTop(50f);
	}
}
