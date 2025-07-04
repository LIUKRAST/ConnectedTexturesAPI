package net.liukrast.connected.textures.api.client.resources.metadata.block.map;

import com.mojang.datafixers.util.Pair;
import net.liukrast.connected.textures.api.client.resources.metadata.block.ConnectedTextureMap;
import org.jetbrains.annotations.NotNull;

public class FullCTM extends ConnectedTextureMap {

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id) {
        if(id <= 31) {
            int y = id / 8;
            int x = id - y * 8;
            return Pair.of(x, y);
        } else if(id <= 43) {
            id -= 32;
            int y = id / 4;
            int x = id - y * 4;
            return Pair.of(x, y + 4);
        } else {
            id -= 44;
            int y = id /3;
            int x = id - y * 3;
            return Pair.of(x, y + 7);
        }
    }
}
