package com.minenash.customhud.mixin.fonts;

import com.minenash.customhud.render.CustomHudRenderer3;
import net.minecraft.client.StringSplitter;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StringSplitter.class)
public class TextHandlerMixin {

    @ModifyArg(method = "getWidth(Ljava/lang/String;)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"), index = 1)
    public Style changeStyle(Style old) {
        return CustomHudRenderer3.font == null ? old : old.withFont(CustomHudRenderer3.font);
    }

}
