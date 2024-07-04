package com.jimmt.smitepractice;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class SmiteResult extends Image {
    Image icon;
    Label text, popup;
    Container wrapper, popupWrapper;
    boolean successful;
    int border = 20;

    public SmiteResult(String objective) {
        super(Textures.getTex("result/smiteresult.png"));
        icon = new Image(Textures.getTex("result/" + objective + ".png"));
        icon.setSize(64, 64);
        text = new Label("99.9%", UI.rankLabelStyle);
        text.setAlignment(Align.center);
        popup = new Label("Too early", UI.rankLabelStyle);
        popup.setColor(1, 1, 1, 0);
        popup.setAlignment(Align.center);
        this.setColor(1, 1, 1, 0);
        icon.setColor(1, 1, 1, 0);
        text.setColor(1, 1, 1, 0);

        setOrigin(getWidth() / 2, getHeight() / 2);
        icon.setOrigin(getWidth() / 2 - border, icon.getHeight() / 2);

        wrapper = new Container(text);
        wrapper.setTransform(true);
        popupWrapper = new Container(popup);
        popupWrapper.setTransform(true);
        popup.setOrigin(0, 0);
    }

    public void setRotation(float angle) {
        super.setRotation(angle);
        wrapper.setRotation(angle);
        icon.setRotation(angle);
        popupWrapper.setRotation(angle);
    }

    public void rotateBy(float angle) {
        super.rotateBy(angle);
        wrapper.rotateBy(angle);
        icon.rotateBy(angle);
        popupWrapper.rotateBy(angle);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        icon.draw(batch, parentAlpha);
        wrapper.draw(batch, parentAlpha);
        popupWrapper.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        wrapper.setOrigin(getX() + getWidth() / 2, getY() + getHeight() / 2);
        popupWrapper.setOrigin(getX() + getWidth() / 2, getY() + getHeight() / 2);
        icon.act(delta);
        icon.setPosition(getX() + border, getY() + getHeight() / 2 - icon.getHeight() / 2);
        text.act(delta);
        text.setPosition(icon.getX() + icon.getWidth() + border,
                getY() + getHeight() / 2 - text.getPrefHeight() / 2);
        popup.act(delta);
        popup.setPosition(getX() + getWidth() / 2 - popup.getPrefWidth() / 2,
                getY() - popup.getPrefHeight());
    }

    public void update(Monster monster, int smiteHealth) {
        int maxDifference = monster.getSmiteDamage();

        float ratio = smiteHealth / (float) maxDifference * 100f;

        String formatted = SmitePractice.formatter.getFormattedString(ratio) + "%";
        text.setText(formatted);

        setColors(ratio);

        displayPopup();

    }

    public void setColors(float ratio) {
        if (ratio > 100) {
            setColor(1, 0, 0, 1f);
            popup.setText("Too early");
        } else if (ratio >= 95) {
            setColor(0, 1, 0, 1f);
            popup.setText("Faker");
        } else if (ratio > 90) {
            setColor(0, 1, 0.25f, 1f);
            popup.setText("Perfect");
        } else if (ratio > 80) {
            setColor(1, 0.8f, 0, 1f);
            popup.setText("Good");
        } else if (ratio > 70) {
            popup.setText("Bronze");
        } else {
            setColor(1, 0, 0, 1f);
            popup.setText("Cardboard");
        }
    }

    public void displayPopup() {
        popup.addAction(Actions.sequence(Actions.fadeIn(0.2f), Actions.delay(0.5f),
                Actions.fadeOut(0.2f)));
    }

    public void display() {
        float alpha = 1.0f;
        addAction(Actions.alpha(alpha, 0.5f, Interpolation.exp10Out));
        icon.addAction(Actions.alpha(1.0f, 0.5f, Interpolation.exp10Out));
        text.addAction(Actions.alpha(1.0f, 0.5f, Interpolation.exp10Out));

    }

    public void hide() {
        addAction(Actions.sequence(Actions.delay(1.0f), Actions.alpha(0, 0.5f)));
        icon.addAction(Actions.sequence(Actions.delay(1.0f), Actions.alpha(0, 0.5f)));
        text.addAction(Actions.sequence(Actions.delay(1.0f), Actions.alpha(0, 0.5f)));
    }

    public void hideImmediate() {
        addAction(Actions.alpha(0));
        icon.addAction(Actions.alpha(0));
        text.addAction(Actions.alpha(0));
    }

}
