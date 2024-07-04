package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameFinishedDialog extends Dialog {
    TextButton retryButton;
    TextButton backButton;
    float showButtonsTimer = 0f;

    public GameFinishedDialog(final SmitePractice smiteGame, final GameConfiguration config,
                              final Stats stats1, final Stats stats2) {
        super("", UI.skin);

        Image background = new Image(Textures.getTex("dialogBackground.png"));
        background(background.getDrawable());

        Table table = getContentTable();

        Label averageLabel = new Label("Average Percent:", UI.smallLabelStyle);
        Label percentLabel = new Label("", UI.rankLabelStyle_black);
        Label redPercentLabel = new Label(SmitePractice.formatter.getFormattedString(stats1.getAveragePercent()) + "%",
                UI.rankLabelStyle_blue);
        Label bluePercentLabel = new Label(SmitePractice.formatter.getFormattedString(stats2.getAveragePercent()) + "%",
                UI.rankLabelStyle_red);
        String formatted = "";
        formatted = SmitePractice.formatter.getFormattedString(stats1.getAveragePercent()) + "%";

        percentLabel.setText(formatted.trim());
        Label averageDiffLabel = new Label("", UI.smallLabelStyle);

        if (stats1.getAverageDiff() > 0) {
            averageDiffLabel.setText((int) stats1.getAverageDiff() + " damage late (average)");
        } else {
            averageDiffLabel.setText((int) Math.abs(stats1.getAverageDiff())
                    + " damage early (average)");
        }

        Label averageDiff1Label = new Label("", UI.smallLabelStyle_blue);
        Label averageDiff2Label = new Label("", UI.smallLabelStyle_red);
        if (stats1.getAverageDiff() > 0) {
            averageDiff1Label.setText((int) stats1.getAverageDiff() + " damage late (average)");
        } else {
            averageDiff1Label.setText((int) Math.abs(stats1.getAverageDiff())
                    + " damage early (average)");
        }
        if (stats2.getAverageDiff() > 0) {
            averageDiff2Label.setText((int) stats1.getAverageDiff() + " damage late (average)");
        } else {
            averageDiff2Label.setText((int) Math.abs(stats2.getAverageDiff())
                    + " damage early (average)");
        }

        Label smitesHit = new Label("Smites hit: " + stats1.smitesHit + " / " + stats1.totalSmites,
                UI.smallLabelStyle);
        retryButton = new TextButton("Start", UI.startButtonStyle);
        retryButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                smiteGame.setScreen(new GameScreen(smiteGame, config));
            }
        });
        backButton = new TextButton("Back", UI.startButtonStyle);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                smiteGame.setScreen(new MenuScreen(smiteGame));
            }
        });
        Label smitesHitLabel = new Label("Smites hit: ", UI.smallLabelStyle);
        Label hit1Label = new Label(stats1.smitesHit + " / " + stats1.totalSmites,
                UI.smallLabelStyle_blue);
        Label hit2Label = new Label(stats2.smitesHit + " / " + stats2.totalSmites,
                UI.smallLabelStyle_red);

        if (config.players == 1) {
            table.add(smitesHit).colspan(2).padBottom(20f);
        } else {
            Table hitTable = new Table();
            hitTable.add(hit1Label).row();
            hitTable.add(hit2Label);
            table.add(smitesHitLabel);
            table.add(hitTable);
        }
        table.row();
        table.add(averageLabel).padBottom(20f);

        if (config.players == 1) {
            table.add(percentLabel).padBottom(20f);
        } else {
            Table percents = new Table();
            percents.add(redPercentLabel).row();
            percents.add(bluePercentLabel);
            table.add(percents).padBottom(20f);
        }

        table.row();
        if (config.players == 1) {
            table.add(averageDiffLabel).colspan(2);
        } else {
            table.add(averageDiff1Label).colspan(2);
            table.row();
            table.add(averageDiff2Label).colspan(2).padBottom(20f);
        }
        table.row().padTop(40f);
        table.add(retryButton).width(retryButton.getWidth() / 2)
                .height(retryButton.getHeight() / 2);
        table.add(backButton).width(retryButton.getWidth() / 2).height(retryButton.getHeight() / 2);

        if (config.rounds >= 5 && stats1.getAveragePercent() <= 100) {
            SmitePractice.services.submitScore5((long) stats1.getAveragePercent());
        } else {
            SmitePractice.services.submitScore1((long) stats1.getAveragePercent());
        }
    }

    public void hideButtonsTemporarily() {
        retryButton.setVisible(false);
        backButton.setVisible(false);
        showButtonsTimer = 1.5f;
        this.invalidate();
    }

    public void update(float delta) {
        if (showButtonsTimer <= 0) {
            retryButton.setVisible(true);
            backButton.setVisible(true);
            this.invalidate();
            showButtonsTimer = 0f;
        } else {
            showButtonsTimer -= delta;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }

}
