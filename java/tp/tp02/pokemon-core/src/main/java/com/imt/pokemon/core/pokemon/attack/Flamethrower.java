package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class Flamethrower extends BaseAttack {
    public Flamethrower() {
        super(1, "Flamethrower", false, PokemonType.FIRE, 90, 100, 15, PokemonEffect.BURNED, 0.5, PokemonEffect.NORMAL, 0.0);
    }
}
