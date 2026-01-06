package com.imt.pokemon.core.pokemon.attack;

public enum AttackResult {
    SUCCESS(true, "Attack success."),
    CRITICAL(true, "Attack critique !"),
    BURNED(true,"The target is burned."),
    ABSORBED(true, "Enemy life absorbed"),
    MISSED(false, "Attack missed."),
    FAIL(false, "Attack failed"),
    NO_EFFECT_ON_GHOST(false, "No effect on GHOST");

    public final boolean isSuccessful;
    public final String message;

    AttackResult(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
