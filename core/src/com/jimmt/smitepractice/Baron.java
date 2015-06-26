package com.jimmt.smitepractice;

public class Baron extends Monster {

	@Override
	public void calculateSmiteDamage() {
		float ratio = ((float) (time - startTime)) / (endTime - startTime);
		smiteDamage = damages[9 + (int) (ratio * (damages.length - 10))];
	}

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

	@Override
	public float getSmiteX(float bgWidth) {
		return 839 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getSmiteY(float bgHeight) {
		return 545 / bgHeight * Constants.HEIGHT;
	}
}
