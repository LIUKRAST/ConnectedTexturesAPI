package net.liukrast.connected.textures.api.client.resources.metadata.block;

public record BlockMetadataSection(ConnectedTextureMap map) {

    public static final BlockMetadataSectionSerializer SERIALIZER = new BlockMetadataSectionSerializer();
    public static final BlockMetadataSection EMPTY = new BlockMetadataSection(ConnectedTextureMap.EMPTY);

    public int getSize() {
        return map.getSize();
    }
}
