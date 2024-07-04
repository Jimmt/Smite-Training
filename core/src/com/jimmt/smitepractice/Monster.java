package com.jimmt.smitepractice;

import static com.jimmt.smitepractice.DamageAgent.*;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {
	int health, maxHealth;
	int startTime, endTime, time;
	int smiteDamage;
	float leagueGameTimeRatio;

	private DamageAgent top = new DamageAgent(
			/* autoAttackDamage= */ new DamageAgent.Range(65f, 80f),
			/* autoDelaySeconds= */ new DamageAgent.Range(0.6f, 0.7f),
			/* spellDamage= */ new DamageAgent.Range(65f, 65f),
			/* spellDelaySeconds= */ new DamageAgent.Range(0.5f, 1f)
	);
	private DamageAgent jungle = new DamageAgent(
			/* autoAttackDamage= */ new DamageAgent.Range(65f, 85f),
			/* autoDelaySeconds= */ new DamageAgent.Range(0.5f, 0.7f),
			/* spellDamage= */ new DamageAgent.Range(65f, 65f),
			/* spellDelaySeconds= */ new DamageAgent.Range(0.5f, 1f)
	);
	private DamageAgent mid = new DamageAgent(
			/* autoAttackDamage= */ new DamageAgent.Range(55f, 65f),
			/* autoDelaySeconds= */ new DamageAgent.Range(0.7f, 0.8f),
			/* spellDamage= */ new DamageAgent.Range(75f, 200f),
			/* spellDelaySeconds= */ new DamageAgent.Range(1f, 2f)
	);
	private DamageAgent adc = new DamageAgent(
			/* autoAttackDamage= */ new DamageAgent.Range(75f, 95f),
			/* autoDelaySeconds= */ new DamageAgent.Range(0.5f, 0.6f),
			/* spellDamage= */ new DamageAgent.Range(0f, 0f),
			/* spellDelaySeconds= */ new DamageAgent.Range(1f, 1f)
	);
	private DamageAgent support = new DamageAgent(
			/* autoAttackDamage= */ new DamageAgent.Range(50f, 65f),
			/* autoDelaySeconds= */ new DamageAgent.Range(0.7f, 0.8f),
			/* spellDamage= */ new DamageAgent.Range(40f, 40f),
			/* spellDelaySeconds= */ new DamageAgent.Range(1.5f, 2.5f)
	);

	private List<DamageAgent> damageAgents = new ArrayList<>();

	public Monster() {
		startTime = 20;
		endTime = 50;
		time = MathUtils.random(startTime, endTime);

		calculateObjectiveHealth();
		smiteDamage = calculateSmiteDamage();
		health = calculateInitialHealth();
		leagueGameTimeRatio = ((float) (time - startTime)) / (endTime - startTime);

		damageAgents.add(top);
		damageAgents.add(jungle);
		damageAgents.add(mid);
		damageAgents.add(adc);
		damageAgents.add(support);
	}

	public void reset() {
		startTime = 20;
		endTime = 50;
		time = MathUtils.random(startTime, endTime);

		calculateObjectiveHealth();
		smiteDamage = calculateSmiteDamage();
		health = calculateInitialHealth();
	}

	public void doSmite(Image beam) {
		health -= smiteDamage;
		beam.addAction(Actions.sequence(Actions.alpha(1f),
				Actions.parallel(Actions.moveBy(0, -Constants.HEIGHT / 4, 0.04f, Interpolation.exp10In),
				Actions.alpha(0f, 0.2f)),
				// Move back up while invisible.
				Actions.moveBy(0, Constants.HEIGHT / 4, 0.01f))); // 0.230
		SmitePractice.soundManager.play("smite");
	}

	public void update(float delta) {
		float damage = 0f;
		for (DamageAgent agent: damageAgents) {
			damage += agent.getDamage(delta);
		}
		health -= damage * getDamageMultiplier();
		health = Math.max(health, 0);
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getSmiteDamage() {
		return smiteDamage;
	}

	public abstract int calculateSmiteDamage();

	public abstract int calculateInitialHealth();

	public abstract void calculateObjectiveHealth();

	public abstract float getCenterX(float bgWidth);

	public abstract float getCenterY(float bgHeight);

	public abstract float getSmiteX(float bgWidth);

	public abstract float getSmiteY(float bgHeight);

	public abstract float getDamageMultiplier();
}
