package com.jimmt.smitepractice;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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

	public void reset() {
		startTime = 20;
		endTime = 50;
		time = MathUtils.random(startTime, endTime);

		calculateDamage();
		calculateObjectiveHealth();
		calculateSmiteDamage();
		health = maxHealth / 3;
	}

	public void doSmite(Image beam) {
		health -= smiteDamage;
		beam.addAction(Actions.sequence(Actions.alpha(1f),
				Actions.moveBy(0, -Constants.HEIGHT / 4, 0.04f, Interpolation.exp10In), Actions.alpha(0f, 0.11f))); // 0.230
		SmitePractice.soundManager.play("smite");
	}

	public void update(float delta) {
		float damage = damageRate * MathUtils.random(0.5f, 1.5f);
		health -= damage * delta;
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

	public abstract float getSmiteX(float bgWidth);

	public abstract float getSmiteY(float bgHeight);
}
