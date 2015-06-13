package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen implements Screen {
	Stage uiStage;
	Label titleLabel;
	TextButton play1Button, play2Button, optionsButton;
	StretchViewport viewport;
	Image background;

	public MenuScreen(final SmitePractice smiteGame) {
		viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		uiStage = new Stage(viewport);

		background = new Image(Textures.getTex("background/dragon.png"));
		uiStage.addActor(background);
		background.setSize(Constants.WIDTH, Constants.HEIGHT);
		titleLabel = new Label("SMITE TRAINING", UI.largeLabelStyle);
		
		play1Button = new TextButton("1 PLAYER", UI.buttonStyle);
		play1Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				StartGameDialog dialog = new StartGameDialog(smiteGame);
				dialog.show(uiStage);
				
			}
		});
		play2Button = new TextButton("2 PLAYERS", UI.buttonStyle);
		play2Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				StartGameDialog dialog = new StartGameDialog(smiteGame);
				dialog.show(uiStage);
				
//				GameModeDialog dialog = new GameModeDialog(skin);
//				dialog.show(uiStage);
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

		Gdx.input.setInputProcessor(uiStage);

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		uiStage.act(delta);
		uiStage.draw();


		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
