package net.liukrast.connected.textures.api.mixin;

import com.google.common.collect.Streams;
import net.liukrast.connected.textures.api.client.resources.metadata.block.BlockMetadataSection;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(SpriteLoader.class)
public class SpriteLoaderMixin {
    @Mutable
    @Final
    @Shadow
    public static Set<MetadataSectionSerializer<?>> DEFAULT_METADATA_SECTIONS;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        DEFAULT_METADATA_SECTIONS = Streams.concat(DEFAULT_METADATA_SECTIONS.stream(), Stream.of(BlockMetadataSection.SERIALIZER)).collect(Collectors.toSet());
    }
}