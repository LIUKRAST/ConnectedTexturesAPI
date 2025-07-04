package net.liukrast.connected.textures.api.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.liukrast.connected.textures.api.mixinExtra.IConnectionData;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Arrays;

@Mixin(FaceBakery.class)
public class FaceBakeryMixin {
    @ModifyVariable(method = "bakeQuad", at = @At("STORE"))
    private BlockFaceUV bakeQuad(BlockFaceUV uv, @Local(argsOnly = true) TextureAtlasSprite sprite) {
        int size = ((IConnectionData)sprite.contents()).connected_textures_api$getCMS().getSize();
        float[] uv1 = Arrays.copyOf(uv.uvs, 4);
        uv1[0] /= size;
        uv1[1] /= size;
        uv1[2] /= size;
        uv1[3] /= size;
        return new BlockFaceUV(uv1, uv.rotation);
    }
}
