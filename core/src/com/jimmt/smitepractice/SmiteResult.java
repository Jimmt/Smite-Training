package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class SmiteResult extends Image {
	Image icon;
	Label text, popup;
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
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		icon.draw(batch, parentAlpha);
		text.draw(batch, parentAlpha);
		popup.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		icon.act(delta);
		icon.setPosition(getX() + border, getY() + getHeight() / 2 - icon.getHeight() / 2);
		text.act(delta);
		text.setPosition(icon.getX() + icon.getWidth() + border,
				getY() + getHeight() / 2 - text.getPrefHeight() / 2);
		popup.act(delta);
		popup.setPosition(getX() + getWidth() / 2 - popup.getPrefWidth() / 2,
				getY() - popup.getPrefHeight());
	}

	public void update(boolean successful, Monster monster, int smiteHealth) {
		int maxDifference = monster.getSmiteDamage();

		float ratio = smiteHealth / (float) maxDifference * 100f;
		String formatted = String.format("%.3g", ratio) + "%";
		text.setText(formatted);

		if (ratio > 100) {
			setColor(1, 0, 0, 1f);
			popup.setText("Too early");
		} else if (ratio > 85) {
			setColor(0, 1, 0, 1f);
			popup.setText("Perfect");
		} else if (ratio > 70) {
			setColor(1, 0.8f, 0, 1f);
			popup.setText("Mediocre");
		} else if (ratio > 50) {
			setColor(1, 0.4f, 0, 1f);
			popup.setText("Bronze");
		} else {
			setColor(1, 0, 0, 1f);
			popup.setText("Cardboard");
		}

		displayPopup();

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

}
