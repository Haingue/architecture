package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

import java.util.Random;

public abstract class PokemonAttack {

    private final int priority;
    private final String name;
    private final boolean isPhysical;
    private final PokemonType type;
    private final int basePower;
    private final int baseAccuracy;
    private final int basePowerPoint;
    private final PokemonEffect targetEffect;
    private final double targetEffectSuccessRate;
    private final PokemonEffect selfEffect;
    private final double selfEffectSuccessRate;

    protected PokemonAttack(int priority, String name, boolean isPhysical, PokemonType type, int basePower, int baseAccuracy, int basePowerPoint, PokemonEffect targetEffect, double targetEffectSuccessRate, PokemonEffect selfEffect, double selfEffectSuccessRate) {
        this.priority = priority;
        this.name = name;
        this.isPhysical = isPhysical;
        this.type = type;
        this.basePower = basePower;
        this.baseAccuracy = baseAccuracy;
        this.basePowerPoint = basePowerPoint;
        this.targetEffect = targetEffect;
        this.targetEffectSuccessRate = targetEffectSuccessRate;
        this.selfEffect = selfEffect;
        this.selfEffectSuccessRate = selfEffectSuccessRate;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public boolean isPhysical() {
        return isPhysical;
    }

    public PokemonType getType() {
        return type;
    }

    public int getBasePower() {
        return basePower;
    }

    public int getBaseAccuracy() {
        return baseAccuracy;
    }

    public int getBasePowerPoint() {
        return basePowerPoint;
    }

    public PokemonEffect getTargetEffect() {
        return targetEffect;
    }

    public double getTargetEffectSuccessRate() {
        return targetEffectSuccessRate;
    }

    public PokemonEffect getSelfEffect() {
        return selfEffect;
    }

    public double getSelfEffectSuccessRate() {
        return selfEffectSuccessRate;
    }

    public boolean isBefore (Pokemon selfPokemon, Pokemon targetPokemon, PokemonAttack targetAttack) {
        return selfPokemon.getSpeedPoint() > targetPokemon.getSpeedPoint();
    }

    public boolean isMoveAccurate (Pokemon selfPokemon, Pokemon targetPokemon) {
        double accuracyThreshold = selfPokemon.getAccuracyPoint() * (1.0 + targetPokemon.getEvasionPoint());
        return (new Random()).nextDouble() < accuracyThreshold;
    }

    public AttackResult invoke (Pokemon selfPokemon, Pokemon targetPokemon){
        if (this.isPhysical && targetPokemon.isType(PokemonType.GHOST)) return AttackResult.NO_EFFECT_ON_GHOST;
        if (!isMoveAccurate(selfPokemon, targetPokemon)) return AttackResult.MISSED;
        double damage = (((2.0 * selfPokemon.getLevel() / 5 + 2) * this.getBasePower() * selfPokemon.getPhysicalAttackPoint() / targetPokemon.getDefensePoint()) / 50.0) + 2;
        damage *= targetPokemon.getType().getBonusAgainst(this.getType());
        targetPokemon.setDamagePoint(targetPokemon.getDamagePoint() + (int) Math.round(damage));

        double threshold = Math.random();
        if (threshold < this.targetEffectSuccessRate) targetPokemon.setEffect(this.targetEffect);
        threshold = Math.random();
        if (threshold < this.selfEffectSuccessRate) selfPokemon.setEffect(this.selfEffect);
        return AttackResult.SUCCESS;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PokemonAttack attack)
            return this.name.equals(attack.name);
        return super.equals(obj);
    }
}
