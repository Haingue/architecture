package com.imt.pokemon.core;

import com.imt.pokemon.core.duel.Duel;
import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CoreApplication {



	public static void main(String[] args) {
		// SpringApplication.run(CoreApplication.class, args);
		Pokemon pika = PokemonFactory.generatePokemon(PokemonFactory.NAME.PIKACHU);
		Pokemon pika2 = PokemonFactory.generatePokemon(PokemonFactory.NAME.PIKACHU);
		pika2.setLevel(50);
		System.out.println(pika);
		Pokemon cha = PokemonFactory.generatePokemon(PokemonFactory.NAME.SQUIRTLE);
		System.out.println(cha);

		Duel<Pokemon> duel = new Duel<>(pika, cha);
		System.out.println(duel);
		while (!duel.isfinish()) {
			duel.play();
			System.out.println(duel);
		}
		System.out.println("The game is finished !");
		System.out.println("The winner is: "+duel.getWinner());
		System.out.println(pika);


		Map<Pokemon, Integer> pokemons = new HashMap<>();
		pokemons.put(pika, 10);
		pokemons.put(cha, 1);
		pokemons.put(pika, 15);
		pokemons.put(pika2, 15);
		System.out.println(pokemons);
	}

}
