package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

public class HealthBar extends Actor {
	Image background, bar;
	Label text;
	Array<Image> dividers;
	int health, startingHealth, healthMax;
	float divWidth, barWidth;

	public HealthBar(int startingHealth, int maxHealth) {
		this.startingHealth = startingHealth;
		this.health = startingHealth;
		this.healthMax = maxHealth;

		dividers = new Array<Image>();
		background = new Image(Textures.getTex("healthbar/healthbar.png"));
		bar = new Image(Textures.getTex("healthbar/redbar.png"));
		LabelStyle style = new LabelStyle();
		style.font = UI.arial;
		text = new Label(String.valueOf(startingHealth), style);

		for (int i = 0; i < startingHealth / 1000; i++) {
			Image divider = new Image(Textures.getTex("healthbar/divider.png"));
			dividers.add(divider);
		}
		divWidth = (float) bar.getWidth() / (maxHealth / 1000);
		barWidth = bar.getWidth() * startingHealth / maxHealth;
	}

	public void updateHealth(int hp) {
		health = hp;
	}

	public void centerPosition(float x, float y) {
		setPosition(x - background.getWidth() / 2, y - background.getHeight() / 2);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		background.setPosition(getX(), getY());
		bar.setPosition(getX() + 22f, getY() + 22f);

		bar.setWidth(Math.max(health / ((float) startingHealth) * barWidth, 0));
		text.setPosition(getX() + 118 - text.getPrefWidth() / 2, getY() + 47 - text.getPrefHeight()
				/ 2 + 1);
		text.setText(String.valueOf(Math.max(health, 0)));

		for (int i = 0; i < dividers.size; i++) {
			dividers.get(i).setPosition(bar.getX() + i * divWidth, bar.getY());
		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		background.draw(batch, parentAlpha);
		bar.draw(batch, parentAlpha);
		text.draw(batch, parentAlpha);

		for (Image divider : dividers) {
			divider.draw(batch, parentAlpha);
		}
	}

}
