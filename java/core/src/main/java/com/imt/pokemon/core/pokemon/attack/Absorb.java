package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class Absorb extends PokemonAttack {
    public Absorb () {
        super(1, "Absorb", false, PokemonType.GRASS, 20, 100, 25, PokemonEffect.NORMAL, 0.0, PokemonEffect.NORMAL, 0.0);
    }

    @Override
    public AttackResult invoke(Pokemon selfPokemon, Pokemon targetPokemon) {
        AttackResult attackResult = super.invoke(selfPokemon, targetPokemon);
        if (attackResult.isSuccessful) {
            int healing = (int) Math.round(selfPokemon.getDamagePoint() / 1.8);
            selfPokemon.setDamagePoint(selfPokemon.getDamagePoint() - healing);
            return AttackResult.ABSORBED;
        }
        return attackResult;
    }
}
