package com.jimmt.smitepractice;

import com.badlogic.gdx.math.MathUtils;

public class Dragon extends Monster {
	int[] damages = { 900, 1200 };

	@Override
	public int calculateSmiteDamage() {
		float ratio = ((float) (time - startTime)) / (endTime - startTime);
		return damages[ratio < 0.5f ? 0 : 1];
	}

	@Override
	public int calculateInitialHealth() {
		return calculateSmiteDamage() + MathUtils.random(2500, 3000);
	}

	@Override
	public void calculateObjectiveHealth() {
		maxHealth = (int) (3500 + 240 * time);
	}

	@Override
	public float getCenterX(float bgWidth) {
		return 790 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getCenterY(float bgHeight) {
		return 610 / bgHeight * Constants.HEIGHT;
	}
	
	@Override
	public float getSmiteX(float bgWidth) {
		return 810 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getSmiteY(float bgHeight) {
		return 461 / bgHeight * Constants.HEIGHT;
	}

	@Override
	public float getDamageMultiplier() {
		return 0.66f;
	}

}
