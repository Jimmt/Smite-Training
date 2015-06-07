package com.jimmt.smitepractice;

public class Dragon extends Monster {

	@Override
	public void calculateObjectiveHealth() {
		maxHealth = (int) (3500 + 240 * time / 2);
	}

	@Override
	public float getCenterX(float bgWidth) {
		return 790 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getCenterY(float bgHeight) {
		return 610 / bgHeight * Constants.HEIGHT;
	}

}
