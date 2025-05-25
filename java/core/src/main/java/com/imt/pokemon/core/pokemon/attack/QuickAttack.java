package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class QuickAttack extends BaseAttack {

    public QuickAttack() {
        super(Integer.MAX_VALUE, "Quick attack",  true, PokemonType.NORMAL,  40, 100, 30, PokemonEffect.NORMAL, 0.0, PokemonEffect.NORMAL, 0.0);
    }

    @Override
    public boolean isBefore(Pokemon selfPokemon, Pokemon targetPokemon, PokemonAttack targetAttack) {
        if (targetAttack.equals(this)) return selfPokemon.getLevel() > targetPokemon.getLevel();
        return true;
    }

}
