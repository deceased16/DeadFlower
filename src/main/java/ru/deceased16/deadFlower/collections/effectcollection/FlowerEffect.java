package ru.deceased16.deadFlower.collections.effectcollection;

import org.bukkit.potion.PotionEffectType;

public class FlowerEffect {
    private PotionEffectType effectType;
    private int amplifier;

    public FlowerEffect(PotionEffectType _effectType, int _amplifier) {
        this.effectType = _effectType;
        this.amplifier = _amplifier;
    }

    public PotionEffectType getEffectType() {
        return this.effectType;
    }

    public int getAmplifier() {
        return this.amplifier;
    }
}