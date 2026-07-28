package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectRenderer.class)
public abstract class EffectRendererMixin {

    @Inject(method = "addBlockHitEffects(Lnet/minecraft/util/BlockPos;Lnet/minecraft/util/EnumFacing;)V", at = @At("HEAD"), cancellable = true)
    private void cancelBlockHitEffects(BlockPos pos, EnumFacing side, CallbackInfo ci) {
        if (Cloud.INSTANCE.optionManager.getOptionByName("No Block Hit Particles").isCheckToggled()) {
            ci.cancel();
        }
    }
}
