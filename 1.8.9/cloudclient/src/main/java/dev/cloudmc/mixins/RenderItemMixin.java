package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin {
    
    @Redirect(method = "renderEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"))
    private long staticItemGlint() {
        if (Cloud.INSTANCE.optionManager.getOptionByName("Static Enchantment Glint").isCheckToggled()) {
            return 0L; // Always return 0 to freeze the animation
        }
        return Minecraft.getSystemTime();
    }
}
