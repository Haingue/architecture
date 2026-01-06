package com.imt.pokemon.core.pokemon;

import java.time.LocalDate;

public class CaughtPokemon {

    private Pokemon pokemon;
    private String surname;
    private LocalDate caughtDatetime;

    public CaughtPokemon(Pokemon pokemon, String surname, LocalDate caughtDatetime) {
        this.pokemon = pokemon;
        this.surname = surname;
        this.caughtDatetime = caughtDatetime;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getCaughtDatetime() {
        return caughtDatetime;
    }

    public void setCaughtDatetime(LocalDate caughtDatetime) {
        this.caughtDatetime = caughtDatetime;
    }
}
