package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SmiteButton extends Table {
	ImageButton button;
	Label damageText;
	Container wrapper;
	boolean alreadySmited;

	public SmiteButton(ImageButtonStyle style) {
		button = new ImageButton(style);

		button.setTransform(true);
		setTransform(true);

		damageText = new Label("", UI.smiteLabelStyle);
		wrapper = new Container(damageText);
		wrapper.setTransform(true);
		add(button).size(button.getWidth() * 2f, button.getHeight() * 2f);
		button.setOrigin(button.getWidth() * 2f / 2f, button.getHeight() * 2f / 2f);
		wrapper.setOrigin(getX() - button.getX() - button.getWidth() - button.getWidth() / 2, 0);
	}

	public void setRotation(float angle) {
		button.setRotation(angle);
		wrapper.setRotation(angle);
	}

	public void rotateBy(float angle) {
		button.rotateBy(angle);
		wrapper.rotateBy(angle);
	}

	public void setDamage(int damage) {
		damageText.setText(String.valueOf(damage));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		wrapper.setPosition(getX() + button.getX() + button.getWidth() + button.getWidth() / 2,
				getY() + button.getY() + button.getHeight() / 2);
		wrapper.draw(batch, parentAlpha);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);

// rotateBy(1);

// damageText.addAction(Actions.rotateBy(10f));
		damageText.act(delta);
	}
}
