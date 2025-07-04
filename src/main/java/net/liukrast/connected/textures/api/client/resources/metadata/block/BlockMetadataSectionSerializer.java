package net.liukrast.connected.textures.api.client.resources.metadata.block;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.lwjgl.system.NonnullDefault;

@OnlyIn(Dist.CLIENT)
@NonnullDefault
public class BlockMetadataSectionSerializer implements MetadataSectionSerializer<BlockMetadataSection> {
    public static final String NAME = "block";
    private static final String KEY = "connected_texture_map";

    @Override
    public BlockMetadataSection fromJson(JsonObject json) {
        if(json.has("dummy")) System.err.println(json);
        if(!json.has(KEY)) return BlockMetadataSection.EMPTY;
        JsonElement mapData = json.get(KEY);
        ConnectedTextureMap map;
        map = ConnectedTextureMap.fromId(ResourceLocation.parse(mapData.getAsString()));
        return new BlockMetadataSection(map);
    }

    @Override
    public String getMetadataSectionName() {
        return NAME;
    }
}
