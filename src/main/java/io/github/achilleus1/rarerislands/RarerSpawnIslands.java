package io.github.achilleus1.rarerislands;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import io.github.achilleus1.rarerislands.mixin.StructurePlacementAccessor;
import org.slf4j.Logger;

@Mod(RarerSpawnIslands.MOD_ID)
public final class RarerSpawnIslands {
    public static final String MOD_ID = "rarer_spawn_islands";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfigSpec.DoubleValue TROPICAL_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue VOLCANIC_MULTIPLIER;
    public static final ModConfigSpec CONFIG;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        TROPICAL_MULTIPLIER = builder
                .comment("Fraction of Spawn tropical island placement candidates to keep. 0.1 is about 10x rarer; 1.0 restores the original rate. Restart the world after changing.")
                .defineInRange("tropicalIslandFrequencyMultiplier", 0.10, 0.0, 1.0);
        VOLCANIC_MULTIPLIER = builder
                .comment("Fraction of Spawn volcanic island placement candidates to keep. 0.02 is about 50x rarer; 1.0 restores the original rate. Restart the world after changing.")
                .defineInRange("volcanicIslandFrequencyMultiplier", 0.02, 0.0, 1.0);
        CONFIG = builder.build();
    }

    public RarerSpawnIslands(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, CONFIG, MOD_ID + "-common.toml");
        NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, RarerSpawnIslands::beforeWorldGeneration);
    }

    private static void beforeWorldGeneration(ServerAboutToStartEvent event) {
        var sets = event.getServer().registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
        for (String name : new String[]{"tropical_island", "volcanic_island"}) {
            var id = ResourceLocation.fromNamespaceAndPath("spawn", name);
            var set = sets.get(id);
            if (set == null) {
                LOGGER.warn("Rarer Spawn Islands: structure set {} is missing; skipping it", id);
                continue;
            }
            double multiplier = name.equals("tropical_island")
                    ? TROPICAL_MULTIPLIER.get() : VOLCANIC_MULTIPLIER.get();
            var placement = (StructurePlacementAccessor) set.placement();
            float previous = placement.rarerSpawnIslands$getFrequency();
            float frequency = (float) (previous * multiplier);
            placement.rarerSpawnIslands$setFrequency(frequency);
            LOGGER.info("Rarer Spawn Islands: {} placement frequency {} -> {} (multiplier {})",
                    id, previous, frequency, multiplier);
        }
    }
}
