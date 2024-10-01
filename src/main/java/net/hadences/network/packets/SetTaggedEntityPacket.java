package net.hadences.network.packets;

import net.hadences.BossBarLib;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SetTaggedEntityPacket(int entityID) implements CustomPayload {

    public static final CustomPayload.Id<SetTaggedEntityPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of(BossBarLib.MOD_ID, "set_tagged_entity"));
    public static final PacketCodec<RegistryByteBuf, SetTaggedEntityPacket> PACKET_CODEC = PacketCodecs.VAR_INT.xmap(SetTaggedEntityPacket::new, SetTaggedEntityPacket::entityID).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
