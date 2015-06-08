package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartGameDialog extends Dialog {
	SelectButton objectiveSelect, roundsSelect;
	Label objective, rounds;

	public StartGameDialog(Skin skin, final SmitePractice smiteGame) {
		super("", skin);

		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());

		objectiveSelect = new SelectButton("Dragon", "Baron");
		roundsSelect = new SelectButton("1", "5", "Infinite");

		objective = new Label("Objective", UI.smallLabelStyle);
		rounds = new Label("Rounds", UI.smallLabelStyle);

		TextButton startButton = new TextButton("Start", UI.startButtonStyle);
		startButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

				int rounds = 0;
				if (roundsSelect.getSelectionName().equals("Infinite")) {
					rounds = 999;
				} else {
					rounds = Integer.valueOf(roundsSelect.getSelectionName());
				}
				smiteGame.setScreen(new GameScreen(smiteGame, 1,
						objectiveSelect.getSelectionName(), rounds));
			}
		});
		TextButton backButton = new TextButton("Back", UI.startButtonStyle);
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				hide();
			}
		});

		Table table = getContentTable();
		table.add(objective).colspan(2).row();
		table.add(objectiveSelect).colspan(2).padBottom(20f).row();
		table.row().padTop(20f);
		table.add(rounds).colspan(2).row();
		table.add(roundsSelect).colspan(2).padBottom(20f).row();
		table.row().padTop(20f);
		table.add(startButton).width(startButton.getWidth() / 2)
				.height(startButton.getHeight() / 2);
		table.add(backButton).width(startButton.getWidth() / 2).height(startButton.getHeight() / 2);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

	}

}
