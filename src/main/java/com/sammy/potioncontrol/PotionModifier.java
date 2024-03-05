package com.sammy.potioncontrol;

public class PotionModifier {

    public final boolean forceNoHudDisplay;
    public final boolean forceNoInventoryDisplay;
    public final boolean forceNoParticles;
    public final boolean forceNoCounter;

    public PotionModifier(boolean forceNoHudDisplay, boolean forceNoInventoryDisplay, boolean forceNoParticles, boolean forceNoCounter) {
        this.forceNoHudDisplay = forceNoHudDisplay;
        this.forceNoInventoryDisplay = forceNoInventoryDisplay;
        this.forceNoParticles = forceNoParticles;
        this.forceNoCounter = forceNoCounter;
    }
}
