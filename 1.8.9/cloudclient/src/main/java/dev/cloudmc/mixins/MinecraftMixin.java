/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package dev.cloudmc.mixins;

import dev.cloudmc.helpers.animation.DeltaTime;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    
    long lastFrame = Sys.getTime();

    @Shadow private int leftClickCounter;

    /**
     Calculates the delta time which is used for Animations
     */
    @Inject(method = "runGameLoop", at = @At("RETURN"))
    public void runGameLoop(CallbackInfo ci){
        long currentTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
        int deltaTime = (int) (currentTime - lastFrame);
        lastFrame = currentTime;
        DeltaTime.setDeltaTime(deltaTime);
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    private void onClickMouse(CallbackInfo ci) {
        if (dev.cloudmc.Cloud.INSTANCE.optionManager.getOptionByName("Hit Delay Fix").isCheckToggled()) {
            this.leftClickCounter = 0;
        }
    }
}
