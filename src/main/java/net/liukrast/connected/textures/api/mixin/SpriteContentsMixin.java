package net.liukrast.connected.textures.api.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import net.liukrast.connected.textures.api.client.resources.metadata.block.BlockMetadataSection;
import net.liukrast.connected.textures.api.mixinExtra.IConnectionData;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpriteContents.class)
public class SpriteContentsMixin implements IConnectionData {
    @Unique
    private BlockMetadataSection connected_textures_api$connectionMetadataSection;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ResourceLocation name, FrameSize frameSize, NativeImage originalImage, ResourceMetadata metadata, CallbackInfo ci) {
        this.connected_textures_api$connectionMetadataSection = metadata.getSection(BlockMetadataSection.SERIALIZER).orElse(BlockMetadataSection.EMPTY);
    }

    @Override
    public BlockMetadataSection connected_textures_api$getCMS() {
        return connected_textures_api$connectionMetadataSection;
    }
}
