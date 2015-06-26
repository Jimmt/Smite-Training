package com.jimmt.smitepractice;

public class Dragon extends Monster {
	
	@Override
	public void calculateSmiteDamage() {
		float ratio = ((float) (time - startTime)) / (endTime - startTime);
		smiteDamage = damages[4 + (int) (ratio * (damages.length - 5))];
	}

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
	
	@Override
	public float getSmiteX(float bgWidth) {
		return 810 / bgWidth * Constants.WIDTH;
	}

	@Override
	public float getSmiteY(float bgHeight) {
		return 461 / bgHeight * Constants.HEIGHT;
	}

}
