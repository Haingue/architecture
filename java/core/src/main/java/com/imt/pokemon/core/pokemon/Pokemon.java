package com.imt.pokemon.core.pokemon;

import com.imt.pokemon.core.pokemon.attack.PokemonAttack;
import com.imt.pokemon.core.pokemon.effect.PokemonEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Pokemon implements Comparable<Pokemon> {
    private UUID uuid;
    private String name;
    private String sprite;
    private PokemonType type;
    private int level;
    private int initialHealthPoint;
    private int damagePoint;
    private int physicalAttackPoint;
    private int specialAttackPoint;
    private int defensePoint;
    private int evasionPoint;
    private int speedPoint;
    private int accuracyPoint;
    private PokemonEffect effect;
    private List<PokemonAttack> attackList;

    public Pokemon(UUID uuid, String name, String sprite, PokemonType type, int level, int initialHealthPoint, int damagePoint, int physicalAttackPoint, int specialAttackPoint, int defensePoint, int evasionPoint, int speedPoint, int accuracyPoint, PokemonEffect effect) {
        this.uuid = uuid;
        this.name = name;
        this.sprite = sprite;
        this.type = type;
        this.level = level;
        this.initialHealthPoint = initialHealthPoint;
        this.damagePoint = damagePoint;
        this.physicalAttackPoint = physicalAttackPoint;
        this.specialAttackPoint = specialAttackPoint;
        this.defensePoint = defensePoint;
        this.evasionPoint = evasionPoint;
        this.speedPoint = speedPoint;
        this.accuracyPoint = accuracyPoint;
        this.effect = effect;
        this.attackList = new ArrayList<>();
    }

    public Pokemon(UUID uuid, String name, String sprite, PokemonType type, int level, int initialHealthPoint, int physicalAttackPoint, int specialAttackPoint, int defensePoint, int evasionPoint, int speedPoint, List<PokemonAttack> attackList) {
        this(uuid, name, sprite, type, level, initialHealthPoint, 0, physicalAttackPoint, specialAttackPoint, defensePoint, evasionPoint, speedPoint, 100, PokemonEffect.NORMAL);
        this.attackList = attackList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getInitialHealthPoint() {
        return initialHealthPoint;
    }

    public void setInitialHealthPoint(int initialHealthPoint) {
        this.initialHealthPoint = initialHealthPoint;
    }

    public int getDamagePoint() {
        return damagePoint;
    }

    public void setDamagePoint(int damagePoint) {
        this.damagePoint = damagePoint;
    }

    public int getPhysicalAttackPoint() {
        return physicalAttackPoint;
    }

    public void setPhysicalAttackPoint(int physicalAttackPoint) {
        this.physicalAttackPoint = physicalAttackPoint;
    }

    public int getSpecialAttackPoint() {
        return specialAttackPoint;
    }

    public void setSpecialAttackPoint(int specialAttackPoint) {
        this.specialAttackPoint = specialAttackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getEvasionPoint() {
        return evasionPoint;
    }

    public void setEvasionPoint(int evasionPoint) {
        this.evasionPoint = evasionPoint;
    }

    public int getSpeedPoint() {
        return speedPoint;
    }

    public void setSpeedPoint(int speedPoint) {
        this.speedPoint = speedPoint;
    }

    public int getAccuracyPoint() {
        return accuracyPoint;
    }

    public void setAccuracyPoint(int accuracyPoint) {
        this.accuracyPoint = accuracyPoint;
    }

    public PokemonEffect getEffect() {
        return effect;
    }

    public void setEffect(PokemonEffect effect) {
        this.effect = effect;
    }

    public List<PokemonAttack> getAttackList() {
        return attackList;
    }

    public void setAttackList(List<PokemonAttack> attackList) {
        this.attackList = attackList;
    }

    public boolean isType (PokemonType type) {
        return this.type.equals(type);
    }

    public int getHealthPoint () {
        int healthPoint = this.initialHealthPoint - this.damagePoint;
        return Math.max(healthPoint, 0);
    }

    public boolean isAlive () {
        return getHealthPoint() > 0;
    }

    @Override
    public int compareTo(Pokemon o) {
        return this.uuid.compareTo(o.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pokemon pokemon)) return false;
        return Objects.equals(this.uuid, pokemon.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", healthPoint=" + getHealthPoint() +
                '}';
    }

}
