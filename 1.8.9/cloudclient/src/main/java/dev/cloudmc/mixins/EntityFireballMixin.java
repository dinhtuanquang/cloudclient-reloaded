package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFireball.class)
public abstract class EntityFireballMixin extends Entity {

    public EntityFireballMixin(net.minecraft.world.World worldIn) {
        super(worldIn);
    }

    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void onUpdateReturn(CallbackInfo ci) {
        try {
            if (Cloud.INSTANCE.optionManager.getOptionByName("Fast Fireball") != null && 
                Cloud.INSTANCE.optionManager.getOptionByName("Fast Fireball").isCheckToggled()) {
                this.rotationPitch = this.prevRotationPitch;
                this.rotationYaw = this.prevRotationYaw;
            }
        } catch (Exception e) {}
    }
}
