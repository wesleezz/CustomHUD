package com.minenash.customhud.HudElements.interfaces;

import com.minenash.customhud.data.Flags;
import net.minecraft.resources.ResourceLocation;

public interface IdElement {

    ResourceLocation getIdentifier();

    static String getString(ResourceLocation id, Flags.IdPart part) {
        if (id == null) return "-";
        return switch (part) {
            case FULL -> id.toString();
            case NAMESPACE -> id.getNamespace();
            case PATH -> id.getPath();
        };
    }

}
