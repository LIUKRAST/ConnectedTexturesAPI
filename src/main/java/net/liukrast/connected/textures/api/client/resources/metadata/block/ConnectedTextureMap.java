package net.liukrast.connected.textures.api.client.resources.metadata.block;

import com.mojang.datafixers.util.Pair;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.CreateFullCTM;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.FullCTM;
import net.liukrast.connected.textures.api.client.resources.metadata.block.map.VerticalCTM;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class ConnectedTextureMap {
    private static final HashMap<String, ConnectedTextureMap> REGISTERED = new HashMap<>();
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
        initialized = true;
        REGISTERED.put(ResourceLocation.withDefaultNamespace("full").toString(), new FullCTM());
        REGISTERED.put(ResourceLocation.withDefaultNamespace("vertical").toString(), new VerticalCTM());
        REGISTERED.put(ResourceLocation.parse("create:full").toString(), new CreateFullCTM());
        //TODO: MAKE MAPS CLIENT SIDE RESOURCES!
    }

    public static ConnectedTextureMap fromId(ResourceLocation id) {
        if(!initialized) init();
        return REGISTERED.getOrDefault(id.toString(), EMPTY);
    }

    public abstract @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id);
}
