package com.jimmt.smitepractice;

import com.badlogic.gdx.math.MathUtils;

public class Baron extends Monster {

	@Override
	public int calculateSmiteDamage() {
		return 1200;
	}

	@Override
	public int calculateInitialHealth() {
		return calculateSmiteDamage() + MathUtils.random(3500, 4000);
	}

	@Override
	public void calculateObjectiveHealth() {
		maxHealth = (int) (11400 + 180 * time);
	}

	@Override
	public float getCenterX(float bgWidth) {
		return 862 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getCenterY(float bgHeight) {
		return 988 / bgHeight * Constants.HEIGHT;
	}

	@Override
	public float getSmiteX(float bgWidth) {
		return 839 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getSmiteY(float bgHeight) {
		return 545 / bgHeight * Constants.HEIGHT;
	}

	@Override
	public float getDamageMultiplier() {
		return 1f;
	}
}
