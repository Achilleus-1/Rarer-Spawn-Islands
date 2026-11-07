package io.github.achilleus1.rarerislands.mixin;

import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructurePlacement.class)
public interface StructurePlacementAccessor {
    @Accessor(value = "frequency", remap = false)
    float rarerSpawnIslands$getFrequency();

    @Mutable
    @Accessor(value = "frequency", remap = false)
    void rarerSpawnIslands$setFrequency(float frequency);
}
