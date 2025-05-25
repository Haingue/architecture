package com.imt.pokemon.core;

import com.imt.pokemon.core.duel.Duel;
import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.PokemonFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		// SpringApplication.run(CoreApplication.class, args);
		Pokemon pika = PokemonFactory.generatePokemon(PokemonFactory.NAME.PIKACHU);
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
	}

}
