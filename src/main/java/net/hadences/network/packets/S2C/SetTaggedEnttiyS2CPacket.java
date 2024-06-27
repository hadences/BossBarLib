package net.hadences.network.packets.S2C;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.hadences.BossBarLib;
import net.hadences.common.CustomBossBarManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

import java.util.UUID;

public class SetTaggedEnttiyS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
                               PacketSender responseSender){
        int entityID = buf.readInt();
        client.execute(() -> {
            if(client.world == null) return;
            Entity entity = client.world.getEntityById(entityID);
            if (entity instanceof LivingEntity le) {
                CustomBossBarManager.INSTANCE.setClientTaggedEntity(le);
            }
        });

    }
}
