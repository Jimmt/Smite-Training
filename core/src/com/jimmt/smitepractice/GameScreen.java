package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
	Stage uiStage;
	StretchViewport viewport;
	AI ai;
	Monster monster;
	HealthBar healthBar;
	String objective;
	SmiteButton[] smiteButtons;
	Image background;
	int currentRound, rounds;
	int players;
	boolean alreadySmited, infinite;

	public GameScreen(SmitePractice smiteGame, int players, String objective, int rounds) {
		viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		uiStage = new Stage(viewport);

		this.objective = objective;
		this.rounds = rounds;
		currentRound = 1;
		this.players = players;

		background = new Image(Textures.getTex(objective.toLowerCase() + ".png"));

		uiStage.addActor(background);

		if (objective.equals("Baron")) {
			monster = new Baron();
		} else if (objective.equals("Dragon")) {
			monster = new Dragon();
		}
		ai = new AI(monster);

		healthBar = new HealthBar(monster.getMaxHealth() / 3, monster.getMaxHealth());
		healthBar.centerPosition(monster.getCenterX(background.getWidth()),
				monster.getCenterY(background.getHeight()));

		background.addAction(Actions.sizeTo(Constants.WIDTH, Constants.HEIGHT));
		uiStage.addActor(healthBar);

		smiteButtons = new SmiteButton[players];
		setupUI();

	}

	public void setupUI() {
		Gdx.input.setInputProcessor(uiStage);

		if (players == 1) {
			SmiteButton button = new SmiteButton();
			uiStage.addActor(button);
			button.setPosition(Constants.WIDTH / 2 - button.getWidth() / 2,
					monster.getCenterY(background.getHeight()) / 3 - button.getHeight() / 2);
			button.setDamage(monster.getSmiteDamage());

			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {

					if (!alreadySmited) {
						System.out.println(smite());
						alreadySmited = true;
					}

				}
			});
		}
		if (players == 2) {

		}

	}

	public boolean smite() {

		if (monster.getSmiteDamage() >= monster.getHealth()) {
			if (monster.getHealth() == 0) {
				return false;
			}
			monster.doSmite();
			return true;
		}
		monster.doSmite();
		return false;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		monster.update(delta);
		ai.update(delta);

		if (monster.getHealth() <= 0) {
			if (currentRound < rounds) {
				currentRound++;
				monster.reset();

				alreadySmited = false;
				ai.alreadySmited = false;
				ai.calculateSmiteHealth(monster);

			} else {

			}

		}
		healthBar.health = monster.getHealth();

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
