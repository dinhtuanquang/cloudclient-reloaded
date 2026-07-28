package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(World.class)
public abstract class WorldMixin {

    @Redirect(method = "getCelestialAngle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/WorldInfo;getWorldTime()J"))
    public long setCelestialAngle(WorldInfo instance) {
        if (Cloud.INSTANCE.modManager.getMod("TimeChanger").isToggled()) {
            return (long) (instance.getWorldTime() *
                    Cloud.INSTANCE.settingManager.getSettingByModAndName("TimeChanger", "Speed").getCurrentNumber() +
                    Cloud.INSTANCE.settingManager.getSettingByModAndName("TimeChanger", "Offset").getCurrentNumber());
        }
        return instance.getWorldTime();
    }

    @Inject(method = "updateWeather", at = @At("HEAD"), cancellable = true)
    private void onUpdateWeather(org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        if (Cloud.INSTANCE.optionManager.getOptionByName("Disable Weather").isCheckToggled()) {
            ci.cancel();
        }
    }
}
