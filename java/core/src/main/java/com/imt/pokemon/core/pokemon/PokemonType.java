package com.imt.pokemon.core.pokemon;

public enum PokemonType {
    NORMAL(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.0),
    FIRE(1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0),
    WATER(1.0, 2.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    ELECTRIC(1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    GRASS(1.0, 2.0, 0.5, 0.5, 0.5, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    ICE(1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    FIGHTING(2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0),
    POISON(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    GROUND(1.0, 1.0, 2.0, 0.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    FLYING(1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    PSYCHIC(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    BUG(1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    ROCK(0.5, 2.0, 2.0, 1.0, 2.0, 1.0, 2.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    GHOST(0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0),
    DRAGON(1.0, 0.5, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0),
    DARK(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0),
    STEEL(0.5, 2.0, 1.0, 1.0, 0.5, 0.5, 2.0, 0.0, 2.0, 0.5, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    FAIRY(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0);

    public final double normal;
    public final double fire;
    public final double water;
    public final double electric;
    public final double grass;
    public final double ice;
    public final double fighting;
    public final double poison;
    public final double ground;
    public final double flying;
    public final double psychic;
    public final double bug;
    public final double rock;
    public final double ghost;
    public final double dragon;
    public final double dark;
    public final double steel;
    public final double fairy;

    PokemonType(double normal, double fire, double water, double electric, double grass, double ice,
                double fighting, double poison, double ground, double flying, double psychic,
                double bug, double rock, double ghost, double dragon, double dark,
                double steel, double fairy) {
        this.normal = normal;
        this.fire = fire;
        this.water = water;
        this.electric = electric;
        this.grass = grass;
        this.ice = ice;
        this.fighting = fighting;
        this.poison = poison;
        this.ground = ground;
        this.flying = flying;
        this.psychic = psychic;
        this.bug = bug;
        this.rock = rock;
        this.ghost = ghost;
        this.dragon = dragon;
        this.dark = dark;
        this.steel = steel;
        this.fairy = fairy;
    }

    public double getBonusAgainst(PokemonType targetType) {
        return switch (targetType) {
            case NORMAL -> this.normal;
            case FIRE -> this.fire;
            case WATER -> this.water;
            case ELECTRIC -> this.electric;
            case GRASS -> this.grass;
            case ICE -> this.ice;
            case FIGHTING -> this.fighting;
            case POISON -> this.poison;
            case GROUND -> this.ground;
            case FLYING -> this.flying;
            case PSYCHIC -> this.psychic;
            case BUG -> this.bug;
            case ROCK -> this.rock;
            case GHOST -> this.ghost;
            case DRAGON -> this.dragon;
            case DARK -> this.dark;
            case STEEL -> this.steel;
            case FAIRY -> this.fairy;
        };
    }
}
