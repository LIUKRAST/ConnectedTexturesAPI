package net.liukrast.connected.textures.api.client.resources.metadata.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public record BlockMetadataSectionContext(BlockAndTintGetter level, BlockPos pos) {
    public static final ModelProperty<BlockMetadataSectionContext> CONTEXT = new ModelProperty<>();

    public static Direction relative(Direction a, Direction b) {
        boolean isClockWise = b == Direction.NORTH || b == (a == Direction.DOWN ? Direction.WEST : Direction.EAST);
        if(a.getAxis() == Direction.Axis.Y) {
            if (isClockWise) return a.getClockWise(b.getClockWise().getAxis());
            else return a.getCounterClockWise(b.getClockWise().getAxis());
        } else {
            if(b.getAxis() == Direction.Axis.Z) return b == Direction.NORTH ? Direction.UP : Direction.DOWN;
            else return b == Direction.WEST ? a.getClockWise() : a.getCounterClockWise();
        }
    }
    
    public static byte getId(boolean[] pri, boolean[] sec) {
        boolean n = pri[0];
        boolean s = pri[1];
        boolean w = pri[2];
        boolean e = pri[3];
        boolean nw = sec[0];
        boolean ne = sec[1];
        boolean sw = sec[2];
        boolean se = sec[3];
        byte result = 0;
        if(n) result = 24;
        if(s) result = 8;
        if(e) result = 1;
        if(w) result = 3;
        // linear double
        if(n&&s) result = 16;
        if(e&&w) result = 2;
        // 90 double
        if(n&&w) result = (byte) (nw ? 27 : 13);
        if(n&&e) result = (byte) (ne ? 25 : 12);
        if(s&&w) result = (byte) (sw ? 11 : 5);
        if(s&&e) result = (byte) (se ? 9 : 4);
        // TRIPLE
        if(n&&s&&w) {
            result = 15;
            if(sw) result = 31;
            if(nw) result = 29;
            if(sw&&nw) result = 19;
        }
        if(n&&s&&e) {
            result = 6;
            if(se) result = 20;
            if(ne) result = 22;
            if(se&&ne) result = 17;
        }
        if(e&&w&&n) {
            result = 14;
            if(nw) result = 30;
            if(ne) result = 28;
            if(ne&&nw) result = 26;
        }
        if(e&&w&&s) {
            result = 7;
            if(se) result = 23;
            if(sw) result = 21;
            if(se&&sw) result = 10;
        }
        if(n&&s&&w&&e) {
            result = 46;
            // 1 DOT
            if(nw) result = 36;
            if(ne) result = 32;
            if(sw) result = 37;
            if(se) result = 33;
            // 2 DOTS
            if(nw&&ne) result = 35;
            if(sw&&se) result = 38;
            if(sw&&nw) result = 34;
            if(se&&ne) result = 39;
            if(se&&nw) result = 43;
            if(ne&&sw) result = 42;
            // 3 DOTS
            if(ne&&nw&&se) result = 41;
            if(ne&&nw&&sw) result = 40;
            if(se&&sw&&ne) result = 45;
            if(se&&sw&&nw) result = 44;
            if(se&&sw&&nw&&ne) result = 18;
        }
        return result;
    }
}
