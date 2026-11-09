package com.minenash.customhud.mixin;

import com.minenash.customhud.ProfileManager;
import com.minenash.customhud.data.Crosshairs;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Hud.class, priority = 900)
public abstract class InGameHudMixin {

    @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
    private void customhud$handleCrosshair(GuiGraphicsExtractor context, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (ProfileManager.getActive() != null && ProfileManager.getActive().crosshair == Crosshairs.NONE) {
            ci.cancel();
        }
    }

}
