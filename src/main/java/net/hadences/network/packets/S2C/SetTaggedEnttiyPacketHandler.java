package net.hadences.network.packets.S2C;

import net.hadences.BossBarLib;
import net.hadences.common.CustomBossBarManager;
import net.hadences.network.packets.SetTaggedEntityPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class SetTaggedEnttiyPacketHandler {
    public static void receive(SetTaggedEntityPacket packet, MinecraftClient client){
        int entityID = packet.entityID();
        client.execute(() -> {
            if(client.world == null) return;
            Entity entity = client.world.getEntityById(entityID);
            if (entity instanceof LivingEntity le) {
                BossBarLib.LOGGER.info("test");
                CustomBossBarManager.INSTANCE.setClientTaggedEntity(le);
            }
        });

    }
}
