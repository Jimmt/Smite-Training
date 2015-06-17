package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SmiteButton extends ImageButton {
	Label damageText;
	Container wrapper;
	boolean alreadySmited;
	

	public SmiteButton(ImageButtonStyle style) {
		super(style);

		setTransform(true);
		setOrigin(getWidth() / 2, getHeight() / 2);

		damageText = new Label("", UI.smiteLabelStyle);
		wrapper = new Container(damageText);
		wrapper.setTransform(true);

		wrapper.setOrigin(-5 - getWidth() / 2 - getWidth() / 2 - damageText.getPrefWidth() / 2, 0);
	}

	public void setRotation(float angle) {
		super.setRotation(angle);
		wrapper.setRotation(angle);
	}

	public void rotateBy(float angle) {
		super.rotateBy(angle);
		wrapper.rotateBy(angle);
	}

	public void setDamage(int damage) {
		damageText.setText(String.valueOf(damage));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		wrapper.setPosition(getX() + getWidth() + getWidth() / 2 + 5, getY() + getHeight() / 2);
		wrapper.draw(batch, parentAlpha);

	}

	@Override
	public void act(float delta) {
		super.act(delta);

// rotateBy(1);

		damageText.addAction(Actions.rotateBy(10f));
		damageText.act(delta);
	}
}
