package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderEntityItem.class)
public abstract class RenderEntityItemMixin {

    @Redirect(method = "transformDrop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V"))
    private void cancelRotation(float angle, float x, float y, float z) {
        if (!Cloud.INSTANCE.optionManager.getOptionByName("Fast Dropped Items").isCheckToggled()) {
            GlStateManager.rotate(angle, x, y, z);
        }
    }
}
