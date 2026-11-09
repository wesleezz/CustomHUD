package com.minenash.customhud.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackFormat;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.util.InclusiveRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.WeakHashMap;

@Mixin(Pack.class)
public class ResourcePackProfileMixin {

    public static final Map<Pack, InclusiveRange<PackFormat>> PACK_RANGES = new WeakHashMap<>();
    @Unique private static InclusiveRange<PackFormat> temp = null;

    @WrapOperation(method = "readPackMetadata", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/metadata/pack/PackMetadataSection;supportedFormats()Lnet/minecraft/util/InclusiveRange;"))
    private static InclusiveRange<PackFormat> setPackVersionPart1(PackMetadataSection instance, Operation<InclusiveRange<PackFormat>> original) {
        return temp = original.call(instance);
    }

    @Inject(method = "readMetaAndCreate", at = @At("RETURN"))
    private static void setPackVersionPart2(PackLocationInfo info, Pack.ResourcesSupplier resources, PackType type, PackSelectionConfig config, CallbackInfoReturnable<Pack> cir) {
        Pack pack = cir.getReturnValue();
        if (pack != null && temp != null)
            PACK_RANGES.put(pack, temp);
    }
}
