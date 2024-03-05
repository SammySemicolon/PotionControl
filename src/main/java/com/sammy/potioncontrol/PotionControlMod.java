package com.sammy.potioncontrol;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Mod("potion_control")
public class PotionControlMod
{
    public static final Logger LOGGER = LogManager.getLogger();

    public PotionControlMod() {
        MinecraftForge.EVENT_BUS.addListener(PotionControlMod::registerListeners);
        MinecraftForge.EVENT_BUS.addListener(PotionControlMod::manipulatePotionEffect);
    }

    public static void registerListeners(AddReloadListenerEvent event) {
        LOGGER.info("wawawawawaaw");
        event.addListener(new PotionControlReloadListener());
    }

    public static void manipulatePotionEffect(PotionEvent.PotionAddedEvent event) {
        final EffectInstance potionEffect = event.getPotionEffect();
        final ResourceLocation registryName = potionEffect.getEffect().getRegistryName();
        final HashMap<ResourceLocation, PotionModifier> potionModifiers = PotionControlReloadListener.POTION_MODIFIERS;
        if (potionModifiers.containsKey(registryName)) {
            final PotionModifier potionModifier = potionModifiers.get(registryName);
            if (potionModifier.forceNoHudDisplay) {
                potionEffect.showIcon = false;
            }
            if (potionModifier.forceNoParticles) {
                potionEffect.visible = false;
            }
            if (potionModifier.forceNoCounter) {
                potionEffect.noCounter = true;
            }
        }
    }
}
