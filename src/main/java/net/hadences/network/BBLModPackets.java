package net.hadences.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.hadences.network.packets.S2C.SetTaggedEnttiyPacketHandler;
import net.hadences.network.packets.SetTaggedEntityPacket;

public class BBLModPackets {
    public static void registerC2SPackets(){

    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SetTaggedEntityPacket.PACKET_ID, ((payload, context) -> SetTaggedEnttiyPacketHandler.receive(payload, context.client())));
    }

    public static void registerC2SPacketCodecs(){
        PayloadTypeRegistry.playC2S().register(SetTaggedEntityPacket.PACKET_ID, SetTaggedEntityPacket.PACKET_CODEC);
    }

    public static void registerS2CPacketCodecs(){
        PayloadTypeRegistry.playS2C().register(SetTaggedEntityPacket.PACKET_ID, SetTaggedEntityPacket.PACKET_CODEC);
    }
}
