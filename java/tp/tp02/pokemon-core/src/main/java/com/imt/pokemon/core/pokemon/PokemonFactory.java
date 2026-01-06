package com.imt.pokemon.core.pokemon;

import com.imt.pokemon.core.pokemon.attack.*;

import java.util.List;
import java.util.UUID;

public class PokemonFactory {
    public enum NAME {PIKACHU, BULBASAUR, CHARMANDER, SQUIRTLE; };
    public static Pokemon generatePokemon (NAME name) {
        return switch (name) {
            case PIKACHU ->
                    new Pokemon(UUID.randomUUID(), "Pikachu", "", PokemonType.ELECTRIC, 5, 35, 55, 50, 30, 40, 90, List.of(new QuickAttack(), new Swift(), new Thunderbolt()));
            case BULBASAUR ->
                    new Pokemon(UUID.randomUUID(), "Bulbasaur", "", PokemonType.GRASS, 5, 45, 49, 65, 49, 5, 45, List.of(new QuickAttack(), new Absorb()));
            case CHARMANDER ->
                    new Pokemon(UUID.randomUUID(), "Charmander", "", PokemonType.FIRE, 5, 39, 52, 50, 43, 15, 65, List.of(new QuickAttack(), new Flamethrower()));
            case SQUIRTLE ->
                    new Pokemon(UUID.randomUUID(), "Squirtle", "", PokemonType.WATER, 5, 44, 48, 50, 65, 10, 42, List.of(new QuickAttack(), new WaterGun()));
        };
    }
}
