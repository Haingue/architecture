package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class Thunderbolt extends PokemonAttack {
    public Thunderbolt() {
        super(1, "Thunderbolt", false, PokemonType.ELECTRIC, 90, 100, 15, PokemonEffect.PARALYZED, 0.7, PokemonEffect.NORMAL, 0.0);
    }
}
