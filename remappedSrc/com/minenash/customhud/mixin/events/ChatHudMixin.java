package com.minenash.customhud.mixin.events;

import com.minenash.customhud.HudElements.list.ListProvider;
import com.minenash.customhud.ProfileManager;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public class ChatHudMixin {

    @Inject(method = "addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V", at = @At("HEAD"))
    public void test(GuiMessage message, CallbackInfo ci) {
        var profile = ProfileManager.getActive();
        if (profile != null) {
            var list = (ListProvider.EventListProvider<GuiMessage>) profile.listEvents.get("chat_message");
            if (list != null)
                list.add(message);
        }
    }

}
