package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.Pokemon;

public interface PokemonAttack {

    boolean isBefore (Pokemon selfPokemon, Pokemon targetPokemon, PokemonAttack targetAttack);

    boolean isMoveAccurate (Pokemon selfPokemon, Pokemon targetPokemon);

    AttackResult invoke (Pokemon selfPokemon, Pokemon targetPokemon);
}
