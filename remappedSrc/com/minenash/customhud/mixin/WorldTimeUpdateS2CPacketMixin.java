package com.minenash.customhud.mixin;

import com.minenash.customhud.complex.EstimatedTick;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientboundSetTimePacket.class)
public class WorldTimeUpdateS2CPacketMixin {

    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"))
    public void recordTick(ClientGamePacketListener clientPlayPacketListener, CallbackInfo ci) {
        if (RenderSystem.isOnRenderThread())
            EstimatedTick.record();
    }

}
