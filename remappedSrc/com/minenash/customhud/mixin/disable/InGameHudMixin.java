package com.minenash.customhud.mixin.disable;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minenash.customhud.CustomHud;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.minenash.customhud.data.DisableElement.*;

@Mixin(value = Gui.class, priority = 10000)
public abstract class InGameHudMixin {

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    public void customhud$disableHotbar(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(HOTBAR))
            ci.cancel();
    }

    @Inject(method = "renderBossBarHud", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableBossBar(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(BOSSBARS))
            ci.cancel();
    }

    @Inject(method = "renderStatusBars", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableStatusBars(GuiGraphics context, CallbackInfo ci) {
        if (CustomHud.isDisabled(STATUS_BARS))
            ci.cancel();
    }

    @WrapOperation(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V"))
    public void customhud$disableArmor(GuiGraphics context, Player player, int i, int j, int k, int x, Operation<Void> original) {
        if (CustomHud.isNotDisabled(ARMOR))
            original.call(context, player, i, j, k, x);
    }

    @WrapOperation(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V"))
    public void customhud$disableHealthBar(Gui instance, GuiGraphics context, Player player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, Operation<Void> original) {
        if (CustomHud.isNotDisabled(HEALTH))
            original.call(instance, context, player, x, y, lines, regeneratingHeartIndex, maxHealth, lastHealth, health, absorption, blinking);
    }

    @WrapOperation(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V"))
    public void customhud$disableHunger(Gui instance, GuiGraphics context, Player player, int top, int right, Operation<Void> original) {
        if (CustomHud.isNotDisabled(HUNGER))
            original.call(instance, context, player, top, right);
    }

    @WrapOperation(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderAirBubbles(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;III)V"))
    public void customhud$disableAir(Gui instance, GuiGraphics context, Player player, int heartCount, int top, int left, Operation<Void> original) {
        if (CustomHud.isNotDisabled(AIR))
            original.call(instance, context, player, heartCount, top, left);
    }

    @Inject(method = "renderMountHealth", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableHorseHealth(GuiGraphics context, CallbackInfo ci) {
        if (CustomHud.isDisabled(HORSE) || CustomHud.isDisabled(HORSE_HEALTH) || CustomHud.isDisabled(STATUS_BARS))
            ci.cancel();
    }

    @WrapWithCondition(method = "renderMainHud", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/bar/Bar;drawExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/font/TextRenderer;I)V"))
    public boolean customhud$disableXPLvl(GuiGraphics context, Font textRenderer, int level) {
        return CustomHud.isNotDisabled(XP);
    }

    @Inject(method = "renderHeldItemTooltip", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableHotbar(GuiGraphics context, CallbackInfo ci) {
        if (CustomHud.isDisabled(ITEM_TOOLTIP))
            ci.cancel();
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableStatusEffects(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(STATUS_EFFECTS))
            ci.cancel();
    }

    @Inject(method = "renderSubtitlesHud", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableSubtitles(GuiGraphics context, boolean defer, CallbackInfo ci) {
        if (CustomHud.isDisabled(SUBTITLES))
            ci.cancel();
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableScoreboard(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(SCOREBOARD))
            ci.cancel();
    }

    @Inject(method = "renderChat", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableChat(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(CHAT))
            ci.cancel();
    }

    @Inject(method = "renderTitleAndSubtitle", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableTitles(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(TITLES))
            ci.cancel();
    }

    @Inject(method = "renderOverlayMessage", at = @At(value = "HEAD"), cancellable = true)
    public void customhud$disableActionbarMsg(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (CustomHud.isDisabled(ACTIONBAR))
            ci.cancel();
    }

}
