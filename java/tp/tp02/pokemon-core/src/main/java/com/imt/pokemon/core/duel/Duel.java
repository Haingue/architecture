package com.imt.pokemon.core.duel;

import com.imt.pokemon.core.pokemon.Pokemon;
import com.imt.pokemon.core.pokemon.attack.AttackResult;
import com.imt.pokemon.core.pokemon.attack.PokemonAttack;

import java.util.Observable;
import java.util.Random;

public class Duel<P extends Pokemon> extends Observable {

    private P pokemon1;
    private P pokemon2;
    private int turnCounter;
    private final Random rd;

    public Duel(P pokemon1, P pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.turnCounter = 0;
        this.rd = new Random();
    }

    public boolean isfinish () {
        return !pokemon1.isAlive() || !pokemon2.isAlive();
    }

    public P getWinner () {
        assert(isfinish());
        if (!pokemon1.isAlive()) return pokemon2;
        if (!pokemon2.isAlive()) return pokemon1;
        throw new RuntimeException("No pokemon KO");
    }

    public Pokemon[] getPlayers () {
        if (turnCounter % 2 == 0) {
            return new Pokemon[]{pokemon1, pokemon2};
        } else {
            return new Pokemon[]{pokemon2, pokemon1};
        }
    }

    public void play() {
        if (isfinish()) throw new RuntimeException("The game is finished");

        // Choose player
        Pokemon[] players = getPlayers();
        Pokemon firstPlayer = players[0];
        Pokemon secondPlayer = players[1];

        // Prioritize attack
        PokemonAttack firstAttack = firstPlayer.getAttackList().get(rd.nextInt(firstPlayer.getAttackList().size()));
        PokemonAttack secondAttack = secondPlayer.getAttackList().get(rd.nextInt(secondPlayer.getAttackList().size()));
        if (!firstAttack.isBefore(firstPlayer, secondPlayer, secondAttack)) {
            PokemonAttack temp = firstAttack;
            firstAttack = secondAttack;
            secondAttack = temp;
            firstPlayer = players[1];
            secondPlayer = players[0];
        }

        // Invoke attack for first player
        AttackResult firstResult = firstAttack.invoke(firstPlayer, secondPlayer);
        System.out.println(firstPlayer.getName()+" invoke "+firstAttack+":"+firstResult);
        // Invoke attack for second player
        AttackResult secondResult = secondAttack.invoke(secondPlayer, firstPlayer);
        System.out.println(secondPlayer.getName()+" invoke "+secondAttack+":"+secondResult);
        this.turnCounter++;
        this.notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        P firstPlayer, secondPlayer;
        if (turnCounter % 2 == 0) {
            firstPlayer = pokemon1;
            secondPlayer = pokemon2;
        } else {
            firstPlayer = pokemon2;
            secondPlayer = pokemon1;
        }

        builder.append("==========================================================\n");
        builder.append(firstPlayer.getName()).append(" (Lvl:").append(firstPlayer.getLevel()).append(")\t\tPV:").append(firstPlayer.getHealthPoint());
        builder.append("\n");
        builder.append(secondPlayer.getName()).append(" (Lvl:").append(secondPlayer.getLevel()).append(")\t\tPV:").append(secondPlayer.getHealthPoint());
        builder.append("\n==========================================================\n");
        return builder.toString();
    }
}
