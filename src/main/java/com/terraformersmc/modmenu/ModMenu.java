package com.terraformersmc.modmenu;

import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModInfo;

import java.io.IOException;
import java.util.*;

public class ModMenu {
    public static final Map<String, Mod> MODS = new LinkedHashMap<>();
    public static final Map<String, Mod> ROOT_MODS = new LinkedHashMap<>();
    public static final Map<Mod, List<Mod>> PARENT_MAP = new LinkedHashMap<>();

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;
        initialized = true;

        if (ModList.get() != null) {
            for (IModInfo info : ModList.get().getMods()) {
                NeoForgeModWrapper mod = new NeoForgeModWrapper(info);
                MODS.put(mod.getId(), mod);
                ROOT_MODS.put(mod.getId(), mod);
                PARENT_MAP.put(mod, Collections.emptyList());
            }
        }
    }

    static {
        try {
            init();
        } catch (Throwable ignored) {
        }
    }

    public static class NeoForgeModWrapper implements Mod {
        private final IModInfo info;

        public NeoForgeModWrapper(IModInfo info) {
            this.info = info;
        }

        @Override
        public String getId() {
            return info.getModId();
        }

        @Override
        public String getName() {
            return info.getDisplayName();
        }

        @Override
        public String getTranslatedName() {
            return info.getDisplayName();
        }

        @Override
        public String getSummary() {
            return info.getDescription();
        }

        @Override
        public String getTranslatedDescription() {
            return info.getDescription();
        }

        @Override
        public String getVersion() {
            return info.getVersion().toString();
        }

        @Override
        public String getPrefixedVersion() {
            return "v" + info.getVersion().toString();
        }

        @Override
        public String getSha512Hash() throws IOException {
            return "";
        }

        @Override
        public Set<Badge> getBadges() {
            Set<Badge> badges = new HashSet<>();
            if ("minecraft".equals(getId())) {
                badges.add(Badge.MINECRAFT);
            } else if ("neoforge".equals(getId())) {
                badges.add(Badge.LIBRARY);
            }
            return badges;
        }

        @Override
        public String getParent() {
            return null;
        }

        @Override
        public boolean isHidden() {
            return false;
        }

        @Override
        public List<String> getAuthors() {
            return info.getConfig().getConfigElement("authors")
                    .map(a -> List.of(a.toString().split(",")))
                    .orElse(Collections.emptyList());
        }

        @Override
        public Map<String, List<String>> getContributors() {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, List<String>> getCredits() {
            return Collections.emptyMap();
        }

        @Override
        public Set<String> getLicense() {
            String license = info.getOwningFile().getLicense();
            return license == null ? Collections.emptySet() : Set.of(license);
        }

        @Override
        public DynamicTexture getIcon(FabricIconHandler handler, int size) {
            return null;
        }
    }
}
