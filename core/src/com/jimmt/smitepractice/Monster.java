package com.jimmt.smitepractice;

import com.badlogic.gdx.math.MathUtils;

public abstract class Monster {
	int health, maxHealth, damageRate;
	int startTime, endTime, time;
	int smiteDamage;
	int[] damages = { 390, 410, 450, 480, 510, 540, 570, 600, 640, 680, 720, 760, 800, 850, 900,
			950, 1000 };

	public Monster() {

		startTime = 20;
		endTime = 50;
		time = MathUtils.random(startTime, endTime);

		calculateDamage();
		calculateObjectiveHealth();
		calculateSmiteDamage();
		health = maxHealth / 3;
	}
	
	public void reset(){
		startTime = 20;
		endTime = 50;
		time = MathUtils.random(startTime, endTime);

		calculateDamage();
		calculateObjectiveHealth();
		calculateSmiteDamage();
		health = maxHealth / 3;
	}
	
	public void doSmite(){
		health -= smiteDamage;
	}

	public void update(float delta) {
		health -= damageRate * delta;
		health = Math.max(health, 0);
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void calculateDamage() {
		damageRate = (int) (300 + 600 * ((float) (time - startTime)) / (endTime - startTime));
	}

	public void calculateSmiteDamage() {
		float ratio = ((float) (time - startTime)) / (endTime - startTime);
		smiteDamage = damages[(int) (ratio * (damages.length - 1))];
	}

	public int getSmiteDamage() {
		return smiteDamage;
	}

	public int getDamageRate() {
		return damageRate;
	}

	public abstract void calculateObjectiveHealth();

	public abstract float getCenterX(float bgWidth);

	public abstract float getCenterY(float bgHeight);
}
