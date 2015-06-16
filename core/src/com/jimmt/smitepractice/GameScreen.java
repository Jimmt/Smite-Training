package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
	SmitePractice smiteGame;
	Stage uiStage;
	StretchViewport viewport;
	Stats stats;
	Monster monster;
	GameConfiguration config;

	Image beam = new Image(Textures.getTex("smite/smiteBeam.png"));
	Image black = new Image(Textures.getTex("black.png"));
	HealthBar healthBar;
	SmiteButton[] smiteButtons;
	Image background;
	ImageButton homeButton;
	SmiteResult result;

	int currentRound = 1;
	boolean alreadySmited, infinite, dialogShown;
	float delayTimer, totalPercent;

	public GameScreen(SmitePractice smiteGame, GameConfiguration config) {
		this.smiteGame = smiteGame;
		this.config = config;
		stats = new Stats(config.rounds);

		viewport = new StretchViewport(Constants.WIDTH, Constants.HEIGHT);
		uiStage = new Stage(viewport);

		background = new Image(Textures.getTex("background/"
				+ config.objective.toString().toLowerCase() + ".png"));
		result = new SmiteResult(config.objective.toLowerCase());
		uiStage.addActor(background);
		uiStage.addActor(result);
		result.setPosition(Constants.WIDTH - result.getWidth(),
				Constants.HEIGHT / 2 - result.getHeight() / 2);

		if (config.objective.equals("Baron")) {
			monster = new Baron();
		} else if (config.objective.equals("Dragon")) {
			monster = new Dragon();
		}

		healthBar = new HealthBar(monster.getMaxHealth() / 3, monster.getMaxHealth());
		healthBar.centerPosition(monster.getCenterX(background.getWidth()),
				monster.getCenterY(background.getHeight()));

		background.addAction(Actions.sizeTo(Constants.WIDTH, Constants.HEIGHT));
		uiStage.addActor(healthBar);
		uiStage.addActor(beam);
		beam.setPosition(monster.getSmiteX(background.getWidth()) - beam.getWidth() / 2,
				monster.getSmiteY(background.getHeight()));
		beam.setColor(1, 1, 1, 0f);
		black.setSize(Constants.WIDTH, Constants.HEIGHT);
		black.setColor(1, 1, 1, 0f);
		uiStage.addActor(black);

		smiteButtons = new SmiteButton[1];
		setupUI();

	}

	public void setupUI() {
		Gdx.input.setInputProcessor(uiStage);

		SmiteButton button = new SmiteButton();
		smiteButtons[0] = button;
		uiStage.addActor(button);
		button.setPosition(Constants.WIDTH / 2 - button.getWidth() / 2,
				monster.getCenterY(background.getHeight()) / 3 - button.getHeight() / 2);
		button.setDamage(monster.getSmiteDamage());

		button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {

				if (!alreadySmited) {
					int health = monster.getHealth();
					boolean smiteHit = smite();
					result.update(monster, health);

					stats.logSmite(smiteHit, health / (float) monster.getSmiteDamage() * 100f, monster.getSmiteDamage() - health);

					result.display();
					result.addAction(Actions.fadeIn(0.25f));

					alreadySmited = true;
				}

			}
		});

		homeButton = new ImageButton(UI.homeStyle);
		homeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				smiteGame.setScreen(new MenuScreen(smiteGame));
			}
		});
		uiStage.addActor(homeButton);
		homeButton.setPosition(20, Constants.HEIGHT - homeButton.getHeight() - 20);

	}

	public boolean smite() {

		if (monster.getSmiteDamage() >= monster.getHealth()) {
			if (monster.getHealth() == 0) {
				return false;
			}
			monster.doSmite(beam);
			return true;
		}
		monster.doSmite(beam);
		return false;
	}

	@Override
	public void show() {

	}

	class NewRoundAction extends Action {
		boolean done;

		public boolean act(float delta) {
			if (!done) {
				done = true;
				currentRound++;
				monster.reset();
				result.hide();
				alreadySmited = false;

				for (SmiteButton sb : smiteButtons) {
					sb.damageText.setText(String.valueOf(monster.getDamageRate()));
				}
			}
			return true;
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		monster.update(delta);
// ai.update(delta);
		if (monster.getHealth() <= 0) {
			if (currentRound < config.rounds || config.rounds == -1) {

				if (black.getActions().size == 0) {
					black.toFront();
					black.setTouchable(Touchable.disabled);
					black.addAction(Actions.sequence(Actions.delay(1.4f), Actions.alpha(1, 0.2f),
							Actions.delay(0.1f), Actions.alpha(0, 0.2f), new NewRoundAction()));
				}
// ai.alreadySmited = false;
// ai.calculateSmiteHealth(monster);

			} else {
				if (!dialogShown) {
					if (delayTimer >= 1) {
						result.hide();

						GameFinishedDialog dialog = new GameFinishedDialog(smiteGame, config, stats);
						dialog.show(uiStage);
						dialogShown = true;
					} else {
						delayTimer += delta;
					}
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
