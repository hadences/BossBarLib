package net.hadences.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hadences.BossBarLib;
import net.hadences.network.packets.S2C.SetTaggedEnttiyS2CPacket;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier SET_TAGGED_ENTTIY = new Identifier(BossBarLib.MOD_ID, "set_tagged_entity");

    public static void registerC2SPackets(){

    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SET_TAGGED_ENTTIY, SetTaggedEnttiyS2CPacket::receive);
    }
}
