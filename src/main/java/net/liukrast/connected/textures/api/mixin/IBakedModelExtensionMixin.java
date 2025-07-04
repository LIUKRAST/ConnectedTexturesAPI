package net.liukrast.connected.textures.api.mixin;

import com.google.common.collect.ImmutableList;
import net.liukrast.connected.textures.api.client.resources.metadata.block.BlockMetadataSection;
import net.liukrast.connected.textures.api.client.resources.metadata.block.BlockMetadataSectionContext;
import net.liukrast.connected.textures.api.mixinExtra.IConnectionData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.IBakedModelExtension;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(IBakedModelExtension.class)
public interface IBakedModelExtensionMixin {

    @Inject(method = "getQuads", at = @At("RETURN"), cancellable = true)
    private void getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData data, @Nullable RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir) {
        var context = data.get(BlockMetadataSectionContext.CONTEXT);
        if(state == null || context == null) return;
        BlockAndTintGetter level = context.level();
        BlockPos pos = context.pos();
        if(level == null || pos == null) return;
        List<BakedQuad> currentQuads = cir.getReturnValue();
        var export = ImmutableList.<BakedQuad>builder();

        currentQuads.forEach(quad -> {
            int[] vertices = Arrays.copyOf(quad.getVertices(), 32);
            var sprite = quad.getSprite();
            var direction = quad.getDirection();
            BlockMetadataSection connectionData = ((IConnectionData)sprite.contents()).connected_textures_api$getCMS();
            boolean[] directConnections = new boolean[4];
            boolean[] secondaryConnections = new boolean[4];
            int k = 0;
            int j = 0;
            for(Direction dir : Direction.values()) {
                if(dir.getAxis() == Direction.Axis.Y) continue;
                directConnections[j] = level.getBlockState(pos.relative(BlockMetadataSectionContext.relative(direction, dir))).is(state.getBlock());
                j++;
                if(dir.getAxis() != Direction.Axis.Z) continue;
                secondaryConnections[k] = level.getBlockState(pos.relative(BlockMetadataSectionContext.relative(direction, dir)).relative(BlockMetadataSectionContext.relative(direction, Direction.WEST))).is(state.getBlock());
                secondaryConnections[k+1] = level.getBlockState(pos.relative(BlockMetadataSectionContext.relative(direction, dir)).relative(BlockMetadataSectionContext.relative(direction, Direction.EAST))).is(state.getBlock());
                k+=2;
            }

            byte id = BlockMetadataSectionContext.getId(directConnections, secondaryConnections);
            var map = connectionData.map();

            var pair = map.getOffset(id);
            int x = pair.getFirst();
            int y = pair.getSecond();

            int size = connectionData.getSize();
            for(int i = 0; i < 4; i++) {
                int index = i * 8;
                vertices[index + 4] = Float.floatToRawIntBits(Float.intBitsToFloat(vertices[index + 4]) + (sprite.getU1() - sprite.getU0())/(size)*x);
                vertices[index + 5] = Float.floatToRawIntBits(Float.intBitsToFloat(vertices[index + 5]) + (sprite.getV1() - sprite.getV0())/(size)*y);
            }
            export.add(new BakedQuad(vertices, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade(), quad.hasAmbientOcclusion()));
        });
        cir.setReturnValue(export.build());
    }

    @Inject(method = "getModelData", at = @At("RETURN"), cancellable = true)
    private void getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData, CallbackInfoReturnable<ModelData> cir) {
        cir.setReturnValue(cir.getReturnValue().derive().with(BlockMetadataSectionContext.CONTEXT, new BlockMetadataSectionContext(level, pos)).build());
    }
}
