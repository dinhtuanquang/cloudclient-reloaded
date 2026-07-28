package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityThrowable.class)
public abstract class EntityThrowableMixin {

    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnParticle(Lnet/minecraft/util/EnumParticleTypes;DDDDDD[I)V"))
    private void redirectSpawnParticle(World world, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int[] p_175688_14_) {
        try {
            if (Cloud.INSTANCE.optionManager.getOptionByName("Fast Trails") != null && 
                Cloud.INSTANCE.optionManager.getOptionByName("Fast Trails").isCheckToggled()) {
                return;
            }
        } catch (Exception e) {}
        
        world.spawnParticle(particleType, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, p_175688_14_);
    }
}
