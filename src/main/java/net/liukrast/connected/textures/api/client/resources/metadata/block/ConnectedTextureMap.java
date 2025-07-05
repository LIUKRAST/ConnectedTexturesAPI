package net.liukrast.connected.textures.api.client.resources.metadata.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.datafixers.util.Pair;
import net.liukrast.connected.textures.api.ConnectedTexturesAPIConstants;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.CreateFullCTM;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.FullCTM;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.VerticalCTM;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class ConnectedTextureMap {
    private static final BiMap<String, ConnectedTextureMap> REGISTERED = HashBiMap.create();
    private static boolean initialized = false;

    public static final ConnectedTextureMap EMPTY = new ConnectedTextureMap() {

        @Override
        public @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id) {
            return Pair.of(0,0);
        }
    };

    public int getSize() {
        return 1;
    }

    private static void init() {
        if(initialized) throw new IllegalCallerException("Connected texture map init called twice");
        initialized = true;
        REGISTERED.put(ResourceLocation.withDefaultNamespace("full").toString(), new FullCTM());
        REGISTERED.put(ResourceLocation.withDefaultNamespace("vertical").toString(), new VerticalCTM());
        REGISTERED.put(ResourceLocation.parse("create:full").toString(), new CreateFullCTM());
        //TODO: MAKE MAPS CLIENT SIDE RESOURCES!
    }

    public synchronized static ConnectedTextureMap fromId(ResourceLocation id) {
        if(!initialized) init();
        if(!REGISTERED.containsKey(id.toString()))
            ConnectedTexturesAPIConstants.LOGGER.error("Unable to find connected map id {}", id);
        return REGISTERED.getOrDefault(id.toString(), EMPTY);
    }

    @Override
    public String toString() {
        return REGISTERED.inverse().get(this);
    }

    public abstract @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id);
}
