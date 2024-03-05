package com.sammy.potioncontrol;

import com.google.gson.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import net.minecraft.profiler.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

import java.util.*;

public class PotionControlReloadListener extends JsonReloadListener {

    public static final HashMap<ResourceLocation, PotionModifier> POTION_MODIFIERS = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();

    public PotionControlReloadListener() {
        super(GSON, "potion_modifiers");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        POTION_MODIFIERS.clear();
        for (int i = 0; i < objectIn.size(); i++) {
            ResourceLocation location = (ResourceLocation) objectIn.keySet().toArray()[i];
            JsonObject object = objectIn.get(location).getAsJsonObject();
            String name = object.getAsJsonPrimitive("potion_registry_name").getAsString();
            ResourceLocation resourceLocation = new ResourceLocation(name);
            boolean forceNoHudDisplay = object.getAsJsonPrimitive("force_no_hud_display").getAsBoolean();
            boolean forceNoInventoryDisplay = object.getAsJsonPrimitive("force_no_inventory_display").getAsBoolean();
            boolean forceNoParticles = object.getAsJsonPrimitive("force_no_particles").getAsBoolean();
            boolean forceNoCounter = object.getAsJsonPrimitive("force_no_counter").getAsBoolean();

            POTION_MODIFIERS.put(resourceLocation, new PotionModifier(forceNoHudDisplay, forceNoInventoryDisplay, forceNoParticles, forceNoCounter));
        }
    }
}
