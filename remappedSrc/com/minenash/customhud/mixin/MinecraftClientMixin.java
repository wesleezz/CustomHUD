package com.minenash.customhud.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minenash.customhud.ProfileManager;
import com.minenash.customhud.complex.ComplexData;
import com.minenash.customhud.CustomHud;
import com.minenash.customhud.data.DebugCharts;
import com.minenash.customhud.data.Profile;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.gui.components.debug.DebugScreenEntryList;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Shadow @Final public Options options;
    @Shadow private double gpuUtilizationPercentage;

    @Shadow @Final public Gui inGameHud;

    @Shadow @Nullable public ClientLevel world;

    @WrapOperation(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;wasPressed()Z"))
    public boolean readClick(KeyMapping instance, Operation<Boolean> original) {
        boolean p = original.call(instance);

        if (p && instance == options.keyAttack)
            ComplexData.clicksSoFar[0]++;
        if (p && instance == options.keyUse)
            ComplexData.clicksSoFar[1]++;

        return p;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(GameConfig args, CallbackInfo ci) {
        CustomHud.delayedInitialize();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getFramebuffer()Lnet/minecraft/client/gl/Framebuffer;"))
    public void getGpuUsage(boolean tick, CallbackInfo ci) {
        ComplexData.gpuUsage = gpuUtilizationPercentage > 100 ? 100 : gpuUtilizationPercentage;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/debug/DebugHudProfile;isEntryVisible(Lnet/minecraft/util/Identifier;)Z"))
    public boolean getGpuUsageAndOtherPerformanceMetrics(DebugScreenEntryList instance, ResourceLocation entryId, Operation<Boolean> original) {
        return original.call(instance, entryId) || (ProfileManager.getActive() != null && ProfileManager.getActive().enabled.gpuMetrics);
    }


    @Unique private static boolean isFirst = true;
    @Inject(method = "onFinishedLoading", at = @At("RETURN"))
    public void reloadProfiles(Minecraft.GameLoadCookie loadingContext, CallbackInfo ci) {
        if (isFirst) {
            isFirst = false;
            return;
        }

        CustomHud.resourceTriggeredReload();
    }

    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;shouldShowRenderingChart()Z"))
    public boolean activateProfiler(DebugScreenOverlay instance, Operation<Boolean> original) {
        if (ComplexData.refreshTimings) {
            ComplexData.refreshTimings = false;
            return false;
        }

        Profile p = ProfileManager.getActive();
        return original.call(instance) ||
                (!options.hideGui && !inGameHud.getDebugOverlay().showDebugScreen() && world != null
                        && p != null && (p.enabled.profilerTimings || p.leftChart == DebugCharts.PROFILER || p.rightChart == DebugCharts.PROFILER) );
    }
}
