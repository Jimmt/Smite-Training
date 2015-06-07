package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SmiteButton extends ImageButton {
	Label damageText;

	public SmiteButton() {
		super(UI.smiteStyle);
		
		damageText = new Label("", UI.smiteLabelStyle);
		
		addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				
			}
		});
	}
	
	public void setDamage(int damage){
		damageText.setText(String.valueOf(damage));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		damageText.setPosition(getX() + getWidth() + 5, getY() + getHeight() / 2);
		damageText.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		damageText.act(delta);
	}
}
