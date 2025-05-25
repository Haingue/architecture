package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class Swift extends PokemonAttack {

    public Swift() {
        super(1, "Swift", false, PokemonType.NORMAL, 60, -1, 20, PokemonEffect.NORMAL, 0.0, PokemonEffect.NORMAL, 0.0);
    }

    @Override
    public boolean isMoveAccurate(Pokemon selfPokemon, Pokemon targetPokemon) {
        return true;
    }

}
