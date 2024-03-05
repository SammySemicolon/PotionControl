package com.sammy.potioncontrol.mixin;

import com.mojang.blaze3d.matrix.*;
import com.sammy.potioncontrol.*;
import net.minecraft.client.gui.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.*;

@Mixin(DisplayEffectsScreen.class)
public class DisplayEffectsScreenMixin {

    @ModifyVariable(method = "renderEffects", at = @At("STORE"), index = 3)
    private Collection<EffectInstance> potionControl$renderEffects(Collection<EffectInstance> value) {
        Collection<EffectInstance> newCollection = new ArrayList<>(value);
        for (EffectInstance effectInstance : value) {
            final ResourceLocation registryName = effectInstance.getEffect().getRegistryName();
            final HashMap<ResourceLocation, PotionModifier> potionModifiers = PotionControlReloadListener.POTION_MODIFIERS;
            if (potionModifiers.containsKey(registryName)) {

                final PotionModifier potionModifier = potionModifiers.get(registryName);
                if (potionModifier.forceNoInventoryDisplay) {
                    newCollection.remove(effectInstance);
                }
            }
        }
        return newCollection;
    }
}
