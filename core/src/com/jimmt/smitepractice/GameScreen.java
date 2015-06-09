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
	SmitePractice smiteGame;
	Stage uiStage;
	StretchViewport viewport;
	Stats stats;
	AI ai;
	Monster monster;
	GameConfiguration config;

	HealthBar healthBar;
	SmiteButton[] smiteButtons;
	Image background;
	SmiteResult result;

	int currentRound = 1;
	boolean alreadySmited, infinite, dialogShown;

	public GameScreen(SmitePractice smiteGame, GameConfiguration config) {
		this.smiteGame = smiteGame;
		this.config = config;
		stats = new Stats(config.rounds);

		viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		uiStage = new Stage(viewport);

		background = new Image(Textures.getTex(config.objective.toString() + ".png"));
		result = new SmiteResult(config.objective);
		uiStage.addActor(background);
		uiStage.addActor(result);
		result.setPosition(Constants.WIDTH - result.getWidth(), Constants.HEIGHT / 2 - result.getHeight() / 2);
		result.hide();

		if (config.objective.equals("Baron")) {
			monster = new Baron();
		} else if (config.objective.equals("Dragon")) {
			monster = new Dragon();
		}
		ai = new AI(monster);

		healthBar = new HealthBar(monster.getMaxHealth() / 3, monster.getMaxHealth());
		healthBar.centerPosition(monster.getCenterX(background.getWidth()),
				monster.getCenterY(background.getHeight()));

		background.addAction(Actions.sizeTo(Constants.WIDTH, Constants.HEIGHT));
		uiStage.addActor(healthBar);

		smiteButtons = new SmiteButton[1];
		setupUI();

	}

	public void setupUI() {
		Gdx.input.setInputProcessor(uiStage);

		SmiteButton button = new SmiteButton();
		uiStage.addActor(button);
		button.setPosition(Constants.WIDTH / 2 - button.getWidth() / 2,
				monster.getCenterY(background.getHeight()) / 3 - button.getHeight() / 2);
		button.setDamage(monster.getSmiteDamage());

		button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

				if (!alreadySmited) {
					boolean smiteHit = smite();

					result.update(smiteHit);
					
					if (smiteHit) {
						stats.logSmiteHit();
						
					}
					result.display(); //testing
					result.addAction(Actions.fadeIn(0.5f));

					alreadySmited = true;
				}

			}
		});

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
			if (currentRound < config.rounds || config.rounds == -1) {
				currentRound++;
				monster.reset();

				alreadySmited = false;
				ai.alreadySmited = false;
				ai.calculateSmiteHealth(monster);

			} else {
				if (!dialogShown) {
					result.hide();
					
					GameFinishedDialog dialog = new GameFinishedDialog(smiteGame, config, stats);
					dialog.show(uiStage);
					dialogShown = true;
				}
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
