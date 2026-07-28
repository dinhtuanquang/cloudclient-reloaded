package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LayerArmorBase.class)
public abstract class LayerArmorBaseMixin {
    
    @Redirect(method = "renderGlint", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"))
    private long staticArmorGlint() {
        if (Cloud.INSTANCE.optionManager.getOptionByName("Static Enchantment Glint").isCheckToggled()) {
            return 0L;
        }
        return Minecraft.getSystemTime();
    }
}
