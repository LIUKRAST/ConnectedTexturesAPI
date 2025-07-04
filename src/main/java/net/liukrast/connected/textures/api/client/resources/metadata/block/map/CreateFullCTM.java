package net.liukrast.connected.textures.api.client.resources.metadata.block.map;

import com.mojang.datafixers.util.Pair;
import net.liukrast.connected.textures.api.client.resources.metadata.block.ConnectedTextureMap;
import org.jetbrains.annotations.NotNull;

public class CreateFullCTM extends ConnectedTextureMap {

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public @NotNull Pair<@NotNull Integer, @NotNull Integer> getOffset(int id) {
        return switch (id) {
            case 1  -> Pair.of(0,2);
            case 2  -> Pair.of(0,3);
            case 3  -> Pair.of(0,1);
            case 4  -> Pair.of(2,2);
            case 5  -> Pair.of(2,1);
            case 6  -> Pair.of(3,2);
            case 7  -> Pair.of(2,3);
            case 8  -> Pair.of(2,0);
            case 9  -> Pair.of(5,2);
            case 10 -> Pair.of(2,7);
            case 11 -> Pair.of(5,1);
            case 12 -> Pair.of(1,2);
            case 13 -> Pair.of(1,1);
            case 14 -> Pair.of(1,3);
            case 15 -> Pair.of(3,1);
            case 16 -> Pair.of(3,0);
            case 17 -> Pair.of(2,5);
            case 18 -> Pair.of(6,6);
            case 19 -> Pair.of(2,4);
            case 20 -> Pair.of(0,5);
            case 21 -> Pair.of(0,7);
            case 22 -> Pair.of(1,5);
            case 23 -> Pair.of(1,7);
            case 24 -> Pair.of(1,0);
            case 25 -> Pair.of(4,2);
            case 26 -> Pair.of(2,6);
            case 27 -> Pair.of(4,1);
            case 28 -> Pair.of(1,6);
            case 29 -> Pair.of(1,4);
            case 30 -> Pair.of(0,6);
            case 31 -> Pair.of(0,4);
            case 32 -> Pair.of(4,3);
            case 33 -> Pair.of(3,5);
            case 34 -> Pair.of(5,4);
            case 35 -> Pair.of(6,3);
            case 36 -> Pair.of(5,3);
            case 37 -> Pair.of(3,4);
            case 38 -> Pair.of(3,6);
            case 39 -> Pair.of(4,5);
            case 40 -> Pair.of(6,4);
            case 41 -> Pair.of(6,5);
            case 42 -> Pair.of(4,4);
            case 43 -> Pair.of(5,5);
            case 44 -> Pair.of(5,6);
            case 45 -> Pair.of(4,6);
            case 46 -> Pair.of(3,3);
            default -> Pair.of(0,0);
        };
    }
}
