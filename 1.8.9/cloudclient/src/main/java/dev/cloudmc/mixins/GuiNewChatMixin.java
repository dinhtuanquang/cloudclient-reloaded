package dev.cloudmc.mixins;

import dev.cloudmc.Cloud;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin {

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawChatBackground(int left, int top, int right, int bottom, int color) {
        if (!Cloud.INSTANCE.optionManager.getOptionByName("Fast Chat").isCheckToggled()) {
            Gui.drawRect(left, top, right, bottom, color);
        }
    }
}
