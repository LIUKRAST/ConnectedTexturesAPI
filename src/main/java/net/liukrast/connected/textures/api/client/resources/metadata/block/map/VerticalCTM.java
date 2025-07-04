package net.liukrast.connected.textures.api.client.resources.metadata.block.map;

import com.mojang.datafixers.util.Pair;
import net.liukrast.connected.textures.api.client.resources.metadata.block.ConnectedTextureMap;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class VerticalCTM extends ConnectedTextureMap {
    @Override
    public @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id) {
        int[] ud = {0, 1, 2, 3};
        int[] u = {4, 5, 7, 8, 9, 10, 11, 21, 23};
        int[] d = {12, 13, 14, 24, 25, 26, 27, 28, 30};
        if(Arrays.stream(ud).anyMatch(i -> i == id)) return Pair.of(0,0);
        if(Arrays.stream(u).anyMatch(i -> i == id)) return Pair.of(0,1);
        if(Arrays.stream(d).anyMatch(i -> i == id)) return Pair.of(1,1);
        return Pair.of(1, 0);
    }

    @Override
    public int getSize() {
        return 2;
    }
}
