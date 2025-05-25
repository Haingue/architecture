package com.imt.pokemon.core.pokemon.attack;

import com.imt.pokemon.core.pokemon.PokemonType;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

public class WaterGun extends PokemonAttack {
    public WaterGun() {
        super(1, "Water gun", false, PokemonType.WATER,40, 100, 25, PokemonEffect.NORMAL, 0.0, PokemonEffect.NORMAL, 0.0);
    }
}
