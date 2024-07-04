package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class MenuScreen implements Screen {
	Stage uiStage;
	Label titleLabel;
	TextButton play1Button, play2Button, optionsButton;
//	ImageButton highscoresButton;
//	ImageButton gpgsButton;
	Image background;

	public MenuScreen(final SmitePractice smiteGame) {
		uiStage = new Stage(new FillViewport(Constants.WIDTH, Constants.HEIGHT));

		background = new Image(Textures.getTex("background/dragon.png"));
		uiStage.addActor(background);
		background.setSize(Constants.WIDTH, Constants.HEIGHT);
		titleLabel = new Label("SMITE TRAINING", UI.largeLabelStyle);

		play1Button = new TextButton("1 PLAYER", UI.buttonStyle);
		play1Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				StartGameDialog dialog = new StartGameDialog(smiteGame, 1);
				dialog.show(uiStage);

			}
		});
		play2Button = new TextButton("2 PLAYERS", UI.buttonStyle);
		play2Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				StartGameDialog dialog = new StartGameDialog(smiteGame, 2);
				dialog.show(uiStage);
			}
		});
		optionsButton = new TextButton("OPTIONS", UI.buttonStyle);
		optionsButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				OptionsDialog dialog = new OptionsDialog();
				dialog.show(uiStage);
			}
		});

		float margin = 20;
		Table table = new Table();
		table.add(titleLabel).padBottom(titleLabel.getHeight()).row();
		table.add(play1Button).padBottom(margin / 2).row();
		table.add(play2Button).padBottom(margin / 2).row();
		table.add(optionsButton).padTop(margin / 2);
		uiStage.addActor(table);
		table.setFillParent(true);

//		float aspectRatio = Gdx.graphics.getWidth() / Constants.WIDTH;
		
//		gpgsButton = new ImageButton(UI.gpgsStyle);
//		uiStage.addActor(gpgsButton);
//		Vector2 temp = new Vector2(margin, margin + gpgsButton.getHeight() * aspectRatio);
//		uiStage.getViewport().unproject(temp);
//		gpgsButton.setPosition(temp.x, temp.y);
//		gpgsButton.addListener(new ClickListener() {
//			public void clicked(InputEvent event, float x, float y) {
//				SmitePractice.services.signIn();
//			}
//		});
//		highscoresButton = new ImageButton(UI.highscoresStyle);
//		highscoresButton.addListener(new ClickListener() {
//			public void clicked(InputEvent event, float x, float y) {
//				SmitePractice.services.showScores();
//			}
//		});
//		uiStage.addActor(highscoresButton);
//		Vector2 temp1 = new Vector2(Gdx.graphics.getWidth() - highscoresButton.getWidth() * aspectRatio - margin,
//				highscoresButton.getHeight() * aspectRatio + margin);
//		uiStage.getViewport().unproject(temp1);
//		highscoresButton.setPosition(temp1.x, temp1.y);

		Gdx.input.setInputProcessor(uiStage);

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		uiStage.act(delta);
		uiStage.draw();

// uiStage.getViewport().update(Gdx.graphics.getWidth(),
// Gdx.graphics.getHeight());

	}

	@Override
	public void resize(int width, int height) {
		uiStage.getViewport().update(width, height);
		uiStage.getViewport().apply();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void dispose() {

	}

}
