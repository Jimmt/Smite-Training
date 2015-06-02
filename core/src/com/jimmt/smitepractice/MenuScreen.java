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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen implements Screen {
	Stage uiStage;
	Label titleLabel;
	TextButton playButton, optionsButton;
	StretchViewport viewport;
	Image background;

	public MenuScreen(SmitePractice smiteGame) {
		viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		uiStage = new Stage(viewport);

		background = new Image(Textures.getTex("dragon.png"));
		uiStage.addActor(background);
		background.setSize(Constants.WIDTH, Constants.HEIGHT);
		LabelStyle labelStyle = new LabelStyle();
		BitmapFont rajdhaniLarge = new BitmapFont(Gdx.files.internal("fonts/rajdhani_large.fnt"));
		labelStyle.font = rajdhaniLarge;
		labelStyle.fontColor = Color.WHITE;
		titleLabel = new Label("SMITE TRAINING", labelStyle);
		uiStage.addActor(titleLabel);

		TextButtonStyle buttonStyle = new TextButtonStyle();

		Image buttonImage = new Image(Textures.getTex("button.png"));
		buttonStyle.up = buttonImage.getDrawable();
		buttonImage = new Image(Textures.getTex("button_clicked.png"));
		buttonStyle.down = buttonImage.getDrawable();
		BitmapFont rajdhani = new BitmapFont(Gdx.files.internal("fonts/rajdhani.fnt"));
		buttonStyle.font = rajdhani;

		playButton = new TextButton("PLAY", buttonStyle);
		playButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

			}
		});
		optionsButton = new TextButton("OPTIONS", buttonStyle);
		optionsButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

			}
		});
		float margin = 20;
		Table table = new Table();
		table.add(playButton).padBottom(margin / 2).row();
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

		titleLabel.setPosition(Constants.WIDTH / 2 - titleLabel.getWidth() / 2, Constants.HEIGHT
				- (Constants.HEIGHT - playButton.getY() + playButton.getHeight()) / 2 + titleLabel.getHeight() / 2);

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
