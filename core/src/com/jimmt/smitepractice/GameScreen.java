package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    SmitePractice smiteGame;
    Stage uiStage;
    Stage healthBarStage;
    Stats stats1;
    Stats stats2;
    Monster monster;
    GameConfiguration config;
    public GameFinishedDialog dialog;

    Image beam = new Image(Textures.getTex("smite/smiteBeam.png"));
    Image black = new Image(Textures.getTex("black.png"));
    HealthBar healthBar;
    SmiteButton[] smiteButtons;
    Image background;
    ImageButton homeButton;
    SmiteResult result1;
    SmiteResult result2;
    SpriteBatch spriteBatch;

    int currentRound = 1;
    int showAdRound = ROUNDS_PER_AD;
    static final int ROUNDS_PER_AD = 6;
    boolean infinite, dialogShown;
    float delayTimer, totalPercent;

    public GameScreen(SmitePractice smiteGame, GameConfiguration config) {
        this.smiteGame = smiteGame;
        this.config = config;
        stats1 = new Stats(config.rounds);
        stats2 = new Stats(config.rounds);

        healthBarStage = new Stage(new ScreenViewport());

        uiStage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT));

        background = new Image(Textures.getTex("background/"
                + config.objective.toString().toLowerCase() + ".png"));
        uiStage.addActor(background);

        result1 = new SmiteResult(config.objective.toLowerCase());
        uiStage.addActor(result1);
        result2 = new SmiteResult(config.objective.toLowerCase());
        if (config.players == 2) {
            uiStage.addActor(result2);
        }

        if (config.objective.equals("Baron")) {
            monster = new Baron();
        } else if (config.objective.equals("Dragon")) {
            monster = new Dragon();
        }

        healthBar = new HealthBar(monster.getMaxHealth() / 3, monster.getMaxHealth());
        Vector2 temp;
        uiStage.stageToScreenCoordinates(temp = new Vector2(monster.getCenterX(background
                .getWidth()), Constants.HEIGHT - monster.getCenterY(background.getHeight())));
        healthBar.centerPosition(temp.x, temp.y);
        healthBarStage.addActor(healthBar);

        background.addAction(Actions.sizeTo(Constants.WIDTH, Constants.HEIGHT));
// uiStage.addActor(healthBar);
        uiStage.addActor(beam);
        resetSmiteBeam();
        black.setSize(healthBarStage.getWidth(), healthBarStage.getHeight());
        black.setColor(1, 1, 1, 0f);
        healthBarStage.addActor(black);

        smiteButtons = new SmiteButton[config.players];
        setupUI();

        spriteBatch = new SpriteBatch();

    }

    private void resetSmiteBeam() {
        beam.setPosition(monster.getSmiteX(background.getWidth()) - beam.getWidth() / 2,
                monster.getSmiteY(background.getHeight()));
        beam.setColor(1, 1, 1, 0f);
    }

    public void setupUI() {
        Gdx.input.setInputProcessor(uiStage);

        if (config.players == 1) {
            final SmiteButton button = new SmiteButton(UI.smiteStyle);
            smiteButtons[0] = button;
            uiStage.addActor(button);
            button.setPosition(Constants.WIDTH / 2 - button.getWidth() / 2,
                    monster.getCenterY(background.getHeight()) / 3 - button.getHeight() / 2);
            button.setDamage(monster.getSmiteDamage());

            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    if (!button.alreadySmited) {
                        int health = monster.getHealth();
                        boolean smiteHit = smite();
                        result1.update(monster, health);

                        stats1.logSmite(smiteHit, health / (float) monster.getSmiteDamage() * 100f,
                                monster.getSmiteDamage() - health);

                        result1.display();
                        result1.addAction(Actions.fadeIn(0.25f));

                        button.alreadySmited = true;
                    }

                }
            });

            result1.setPosition(Constants.WIDTH - result1.getWidth(), Constants.HEIGHT / 2
                    - result1.getHeight() / 2);
        } else if (config.players == 2) {
            final SmiteButton button1 = new SmiteButton(UI.blueSmiteStyle);
            final SmiteButton button2 = new SmiteButton(UI.redSmiteStyle);
            smiteButtons[0] = button1;
            smiteButtons[1] = button2;
            button1.setRotation(90);
            button2.setRotation(-90);
            result1.setRotation(90);
            result2.setRotation(-90);
            uiStage.addActor(button1);
            uiStage.addActor(button2);
            button1.setPosition(Constants.WIDTH - 20 - button1.button.getWidth() * 2f / 2f,
                    Constants.HEIGHT / 2 - button1.button.getHeight() / 2);
            button2.setPosition(20 + button2.button.getWidth() * 2f / 2f, Constants.HEIGHT / 2
                    - button2.button.getHeight() / 2);
            button1.setDamage(monster.getSmiteDamage());
            button2.setDamage(monster.getSmiteDamage());

            button1.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    if (!button1.alreadySmited) {
                        int health = monster.getHealth();
                        boolean smiteHit = smite();
                        result1.update(monster, health);

                        stats1.logSmite(smiteHit, health / (float) monster.getSmiteDamage() * 100f,
                                monster.getSmiteDamage() - health);

                        result1.display();
                        result1.addAction(Actions.fadeIn(0.25f));

                        button1.alreadySmited = true;
                    }

                }
            });

            button2.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    if (!button2.alreadySmited) {
                        int health = monster.getHealth();
                        boolean smiteHit = smite();
                        result2.update(monster, health);

                        stats2.logSmite(smiteHit, health / (float) monster.getSmiteDamage() * 100f,
                                monster.getSmiteDamage() - health);

                        result2.display();
                        result2.addAction(Actions.fadeIn(0.25f));

                        button2.alreadySmited = true;
                    }

                }
            });

            result1.setPosition(Constants.WIDTH / 4 * 3 - result1.getWidth() / 2,
                    result1.icon.getHeight());
            result2.setPosition(Constants.WIDTH / 4 - result2.getWidth() / 2,
                    result1.icon.getHeight());
        }

        float aspectRatio = Gdx.graphics.getWidth() / Constants.WIDTH;

        homeButton = new ImageButton(UI.homeStyle);
        homeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                smiteGame.setScreen(new MenuScreen(smiteGame));
            }
        });
        uiStage.addActor(homeButton);
        homeButton.setPosition(20, Constants.HEIGHT - homeButton.getHeight() - 20);
        Vector2 temp = new Vector2(20, homeButton.getHeight() * aspectRatio + 20);
        uiStage.getViewport().unproject(temp);
        homeButton.setPosition(temp.x, temp.y);

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
                SmitePractice.roundsCount++;
                monster.reset();

                for (SmiteButton button : smiteButtons) {
                    button.alreadySmited = false;
                }

                for (SmiteButton sb : smiteButtons) {
                    sb.damageText.setText(String.valueOf(monster.getSmiteDamage()));
                }
            }
            return true;
        }

    }

    class HideResultAction extends Action {
        boolean done;

        public boolean act(float delta) {
            if (!done) {
                done = true;
                result1.hideImmediate();
                result2.hideImmediate();
            }
            return true;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.F)) {
            if (!smiteButtons[0].alreadySmited) {
                int health = monster.getHealth();
                boolean smiteHit = smite();
                result1.update(monster, health);

                stats1.logSmite(smiteHit, health / (float) monster.getSmiteDamage() * 100f,
                        monster.getSmiteDamage() - health);

                result1.display();
                result1.addAction(Actions.fadeIn(0.25f));

                smiteButtons[0].alreadySmited = true;
            }
        }

        monster.update(delta);
// ai.update(delta);
        boolean alreadySmited = true;
        for (SmiteButton button : smiteButtons) {
            alreadySmited = alreadySmited && button.alreadySmited;
        }
        if (monster.getHealth() <= 0 || alreadySmited) {
            if (currentRound < config.rounds || config.rounds == -1) {
                if (black.getActions().size == 0) {
                    black.toFront();
                    black.setTouchable(Touchable.disabled);
                    black.addAction(Actions.sequence(Actions.delay(1.4f), Actions.alpha(1, 0.2f),
                            Actions.delay(0.1f), new HideResultAction(), Actions.alpha(0, 0.2f),
                            new NewRoundAction()));
                }

            } else {
                if (!dialogShown) {
                    if (delayTimer >= 1) {
                        result1.hide();
                        result2.hide();

                        if (config.players == 1) {
                            dialog = new GameFinishedDialog(smiteGame, config, stats1, new Stats(0));
                        }
                        if (config.players == 2) {
                            dialog = new GameFinishedDialog(smiteGame, config, stats1, stats2);
                        }
                        // This is the final round, which does not go though NewRoundAction.
                        SmitePractice.roundsCount++;
                        if (SmitePractice.roundsCount >= showAdRound) {
                            SmitePractice.services.showAd();
                            showAdRound += ROUNDS_PER_AD;
                        }
                        SmitePractice.gamesCount++;

                        dialog.show(uiStage);
                        homeButton.setVisible(false);
                        dialogShown = true;
                        healthBar.setVisible(false);
                    } else {
                        delayTimer += delta;
                    }
                }
            }

        }
        if (dialog != null) {
            dialog.update(delta);
        }
        healthBar.health = monster.getHealth();

        uiStage.act(delta);
        uiStage.draw();
        healthBarStage.act(delta);
        healthBarStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height);
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
