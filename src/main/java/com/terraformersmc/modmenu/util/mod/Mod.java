package com.terraformersmc.modmenu.util.mod;

import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Mod {
    String getId();
    String getName();
    String getTranslatedName();
    String getSummary();
    String getTranslatedDescription();
    String getVersion();
    String getPrefixedVersion();
    String getSha512Hash() throws IOException;
    Set<Badge> getBadges();
    String getParent();
    boolean isHidden();
    List<String> getAuthors();
    Map<String, List<String>> getContributors();
    Map<String, List<String>> getCredits();
    Set<String> getLicense();
    DynamicTexture getIcon(FabricIconHandler handler, int size);

    enum Badge {
        LIBRARY("Library", 0xFF6B7280, 0x80374151),
        CLIENT("Client", 0xFF3B82F6, 0x801D4ED8),
        DEPRECATED("Deprecated", 0xFFEF4444, 0x80991B1B),
        PATCHWORK_FORGE("Forge", 0xFFF59E0B, 0x80B45309),
        MODPACK("Modpack", 0xFF8B5CF6, 0x805B21B6),
        MINECRAFT("Minecraft", 0xFF10B981, 0x80047857);

        private final Component text;
        private final int outlineColor;
        private final int fillColor;

        Badge(String name, int outlineColor, int fillColor) {
            this.text = Component.literal(name);
            this.outlineColor = outlineColor;
            this.fillColor = fillColor;
        }

        public Component getText() {
            return text;
        }

        public int getOutlineColor() {
            return outlineColor;
        }

        public int getFillColor() {
            return fillColor;
        }
    }
}
