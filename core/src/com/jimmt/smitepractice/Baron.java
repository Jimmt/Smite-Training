package com.jimmt.smitepractice;

public class Baron extends Monster {

	@Override
	public void calculateObjectiveHealth() {
		maxHealth = (int) (6400 + 180 * time / 2);

	}

	@Override
	public float getCenterX(float bgWidth) {
		return 862 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getCenterY(float bgHeight) {
		return 988 / bgHeight * Constants.HEIGHT;
	}
}
