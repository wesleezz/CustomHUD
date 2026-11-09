package com.minenash.customhud.mixin;

import com.minenash.customhud.ducks.SubtitleEntryDuck;
import net.minecraft.client.gui.components.SubtitleOverlay;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SubtitleOverlay.Subtitle.class)
public class SubtitleEntryMixin implements SubtitleEntryDuck {

    @Unique public ResourceLocation soundId;

    @Override
    public ResourceLocation customhud$getSoundID() {
        return soundId;
    }

    @Override
    public void customhud$setSoundID(ResourceLocation id) {
        soundId = id;
    }
}
