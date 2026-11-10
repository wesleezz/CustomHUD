package com.minenash.customhud.mixin.disable;

import com.minenash.customhud.CustomHud;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.minenash.customhud.data.DisableElement.*;

@Mixin(value = Hud.class, priority = 10000)
public abstract class InGameHudMixin {

    @Inject(method = "extractHotbar", at = @At("HEAD"), cancellable = true)
    public void customhud$disableHotbar(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(HOTBAR))
            ci.cancel();
    }

    @Inject(method = "extractBossOverlay", at = @At("HEAD"), cancellable = true)
    public void customhud$disableBossBar(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(BOSSBARS))
            ci.cancel();
    }

    @Inject(method = "extractArmor", at = @At("HEAD"), cancellable = true)
    private static void customhud$disableArmor(GuiGraphicsExtractor context, Player player, int i, int j, int k, int x, CallbackInfo ci) {
        if (CustomHud.isDisabled(ARMOR))
            ci.cancel();
    }

    @Inject(method = "extractHearts", at = @At("HEAD"), cancellable = true)
    public void customhud$disableHealthBar(GuiGraphicsExtractor context, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean b, CallbackInfo ci) {
        if (CustomHud.isDisabled(HEALTH))
            ci.cancel();
    }

    @Inject(method = "extractFood", at = @At("HEAD"), cancellable = true)
    public void customhud$disableHunger(GuiGraphicsExtractor context, Player player, int top, int right, CallbackInfo ci) {
        if (CustomHud.isDisabled(HUNGER))
            ci.cancel();
    }

    @Inject(method = "extractAirBubbles", at = @At("HEAD"), cancellable = true)
    public void customhud$disableAir(GuiGraphicsExtractor context, Player player, int heartCount, int top, int left, CallbackInfo ci) {
        if (CustomHud.isDisabled(AIR))
            ci.cancel();
    }

    @Inject(method = "extractVehicleHealth", at = @At("HEAD"), cancellable = true)
    public void customhud$disableHorseHealth(GuiGraphicsExtractor context, CallbackInfo ci) {
        if (CustomHud.isDisabled(HORSE) || CustomHud.isDisabled(HORSE_HEALTH) || CustomHud.isDisabled(STATUS_BARS))
            ci.cancel();
    }

    @Inject(method = "extractSelectedItemName(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V", at = @At("HEAD"), cancellable = true)
    public void customhud$disableItemTooltip(GuiGraphicsExtractor context, CallbackInfo ci) {
        if (CustomHud.isDisabled(ITEM_TOOLTIP))
            ci.cancel();
    }

    @Inject(method = "extractEffects", at = @At("HEAD"), cancellable = true)
    public void customhud$disableStatusEffects(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(STATUS_EFFECTS))
            ci.cancel();
    }

    @Inject(method = "extractSubtitleOverlay", at = @At("HEAD"), cancellable = true)
    public void customhud$disableSubtitles(GuiGraphicsExtractor context, boolean defer, CallbackInfo ci) {
        if (CustomHud.isDisabled(SUBTITLES))
            ci.cancel();
    }

    @Inject(method = "extractScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    public void customhud$disableScoreboard(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(SCOREBOARD))
            ci.cancel();
    }

    @Inject(method = "extractChat", at = @At("HEAD"), cancellable = true)
    public void customhud$disableChat(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(CHAT))
            ci.cancel();
    }

    @Inject(method = "extractTitle", at = @At("HEAD"), cancellable = true)
    public void customhud$disableTitles(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(TITLES))
            ci.cancel();
    }

    @Inject(method = "extractOverlayMessage", at = @At("HEAD"), cancellable = true)
    public void customhud$disableActionbarMsg(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(ACTIONBAR))
            ci.cancel();
    }

}
