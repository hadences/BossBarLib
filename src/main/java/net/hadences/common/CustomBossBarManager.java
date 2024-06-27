package net.hadences.common;

import net.hadences.network.ModPackets;
import net.minecraft.entity.LivingEntity;

public class CustomBossBarManager {
    public static final CustomBossBarManager INSTANCE = new CustomBossBarManager();
    private LivingEntity clientTaggedEntity = null;

    // This method is called in the onInitialize method of the main mod class - Server side only
    public static void initServer() {
        ModPackets.registerC2SPackets();
    }

    // This method is called in the onInitializeClient method of the client mod class - Client side only
    public static void initClient() {
        ModPackets.registerS2CPackets();
    }

    public CustomBossBarManager() {

    }

    public void setClientTaggedEntity(LivingEntity entity) {
        this.clientTaggedEntity = entity;
    }

    public LivingEntity getClientTaggedEntity() {
        return this.clientTaggedEntity;
    }


}
