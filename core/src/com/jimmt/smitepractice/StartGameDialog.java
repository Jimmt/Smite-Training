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

	public StartGameDialog(final SmitePractice smiteGame, final int players) {
		super("", UI.skin);     

		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());

		objectiveSelect = new SelectButton(Prefs.prefs.getInteger("objective"), "Dragon", "Baron");
		roundsSelect = new SelectButton(Prefs.prefs.getInteger("rounds"), "1", "3", "5", "10", "Infinite");
		
		objectiveSelect.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Prefs.prefs.putInteger("objective", objectiveSelect.getSelectionIndex());
				Prefs.prefs.flush();
			}
		});
		roundsSelect.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Prefs.prefs.putInteger("rounds", roundsSelect.getSelectionIndex());
				Prefs.prefs.flush();
			}
		});

		objective = new Label("Objective", UI.smallLabelStyle);
		rounds = new Label("Rounds", UI.smallLabelStyle);

		TextButton startButton = new TextButton("Start", UI.startButtonStyle);
		startButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				GameConfiguration config = new GameConfiguration(objectiveSelect.getSelectionName(), roundsSelect.getSelectionName(), players);
				smiteGame.setScreen(new GameScreen(smiteGame, config));
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
