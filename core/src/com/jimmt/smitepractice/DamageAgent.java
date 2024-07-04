package com.jimmt.smitepractice;

import com.badlogic.gdx.math.MathUtils;

public class DamageAgent {
    Range autoAttackDamage;
    Range autoDelaySeconds;
    Range spellDamage;
    Range spellDelaySeconds;

    private float nextAttackTimer = 0;
    private float nextSpellTimer = 0;

    public static class Range {
        float low, high;

        public Range(float low, float high) {
            this.low = low;
            this.high = high;
        }
    }

    public DamageAgent(Range autoAttackDamage, Range autoDelaySeconds, Range spellDamage, Range spellDelaySeconds) {
        this.autoAttackDamage = autoAttackDamage;
        this.autoDelaySeconds = autoDelaySeconds;
        this.spellDamage = spellDamage;
        this.spellDelaySeconds = spellDelaySeconds;
    }

    public float getDamage(float delta) {
        float damage = 0f;
        if (nextAttackTimer <= 0) {
            nextAttackTimer = MathUtils.random(autoDelaySeconds.low, autoDelaySeconds.high);
            damage += MathUtils.random(autoAttackDamage.low, autoAttackDamage.high);
        } else {
            nextAttackTimer -= delta;
        }
        if (nextSpellTimer <= 0) {
            nextSpellTimer = MathUtils.random(spellDelaySeconds.low, spellDelaySeconds.high);
            damage += MathUtils.random(spellDamage.low, spellDamage.high);
        } else {
            nextSpellTimer -= delta;
        }
        return damage;
    }
}
