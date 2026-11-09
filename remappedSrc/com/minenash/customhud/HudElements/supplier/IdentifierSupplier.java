package com.minenash.customhud.HudElements.supplier;

import com.minenash.customhud.HudElements.interfaces.HudElement;
import com.minenash.customhud.HudElements.interfaces.IdElement;
import com.minenash.customhud.complex.MusicAndRecordTracker;
import com.minenash.customhud.data.Flags;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import static com.minenash.customhud.CustomHud.CLIENT;

public class IdentifierSupplier implements HudElement, IdElement {
    private static BlockPos blockPos() { return CLIENT.getCameraEntity().blockPosition(); }

    public static final Supplier<ResourceLocation> DIMENSION_ID = () -> CLIENT.level.dimension().location();
    public static final Supplier<ResourceLocation> BIOME_ID = () -> CLIENT.level.getBiome(blockPos()).unwrapKey().get().location();
    public static final Supplier<ResourceLocation> MUSIC_ID = () -> MusicAndRecordTracker.isMusicPlaying ? MusicAndRecordTracker.musicId : null;
    public static final Supplier<ResourceLocation> RECORD_ID = () -> MusicAndRecordTracker.isRecordPlaying ? MusicAndRecordTracker.getClosestRecord().id : null;



    private final Supplier<ResourceLocation> supplier;
    private final Flags.IdPart idPart;

    public IdentifierSupplier(Supplier<ResourceLocation> supplier, Flags flags) {
        this.supplier = supplier;
        this.idPart = flags.idPart;
    }

    public ResourceLocation getIdentifier() {
        return sanitize(supplier, null);
    }


    @Override
    public String getString() {
        return IdElement.getString(getIdentifier(), idPart);
    }

    @Override
    public Number getNumber() {
        ResourceLocation id = getIdentifier();
        return id == null ? 0 : id.toString().length();
    }

    @Override
    public boolean getBoolean() {
        ResourceLocation id = getIdentifier();
        return id != null && !id.toString().isEmpty();
    }


}
