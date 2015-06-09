package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SmiteResult extends Image {
	Image icon;
	Label text;
	Drawable hit, missed;
	boolean successful;
	int border = 20;

	public SmiteResult(String objective) {
		super(Textures.getTex("result/smitehit.png"));
		icon = new Image(Textures.getTex("result/" + objective + ".png"));
		icon.setSize(64, 64);
		hit = new Image(Textures.getTex("result/smitehit.png")).getDrawable();
		missed = new Image(Textures.getTex("result/smitemissed.png")).getDrawable(); // change
		text = new Label("SMITED", UI.smallLabelStyle);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		icon.draw(batch, parentAlpha);
		text.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		icon.act(delta);
		icon.setPosition(getX() + border, getY() + getHeight() / 2 - icon.getHeight() / 2);
		text.act(delta);
		text.setPosition(icon.getX() + icon.getWidth() + border, getY() + getHeight() / 2 - text.getPrefHeight() / 2);
	}

	public void update(boolean successful) {
		if (successful) {
			setDrawable(hit);
			text.setText("SMITED");
		} else {
			setDrawable(missed);
			text.setText("MISSED");
		}
	}

	public void display() {
		float alpha = 1.0f;
		addAction(Actions.alpha(alpha, 0.5f, Interpolation.exp10Out));
		icon.addAction(Actions.alpha(1.0f, 0.5f, Interpolation.exp10Out));
		text.addAction(Actions.alpha(1.0f, 0.5f, Interpolation.exp10Out));

	}

	public void hide() {
		addAction(Actions.alpha(0));
		icon.addAction(Actions.alpha(0));
		text.addAction(Actions.alpha(0));
	}

}
