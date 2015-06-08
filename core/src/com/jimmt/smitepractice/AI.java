package com.jimmt.smitepractice;

import com.badlogic.gdx.math.MathUtils;

public class AI {
	Monster monster;
	float smiteHealth;
	boolean alreadySmited;

	public AI(Monster monster) {
		this.monster = monster;
		calculateSmiteHealth(monster);
	}
	
	public void calculateSmiteHealth(Monster monster){
		float ratio = (float) monster.getDamageRate() / 900;
		smiteHealth = monster.getSmiteDamage();
	}

	public void update(float delta) {

		if (!alreadySmited && monster.getHealth() <= smiteHealth) {
			smite();
		}

	}

	public void smite() {

		alreadySmited = true;

		if (monster.getHealth() > 0) {
			
			monster.doSmite();
		}
	}
}
