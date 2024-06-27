package net.hadences.common;

import net.hadences.network.ModPackets;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class CustomBossBarManager {
    public static final CustomBossBarManager INSTANCE = new CustomBossBarManager();
    private LivingEntity clientTaggedEntity = null;
    private static ClientBossBar clientBossBar = null;

    // This method is called in the onInitialize method of the main mod class - Server side only
    public static void initServer() {
        ModPackets.registerC2SPackets();
    }

    // This method is called in the onInitializeClient method of the client mod class - Client side only
    public static void initClient() {
        ModPackets.registerS2CPackets();
        clientBossBar = new ClientBossBar(MathHelper.randomUuid(), Text.empty(), 100,
                BossBar.Color.YELLOW, BossBar.Style.PROGRESS, false, false, false);
    }

    public CustomBossBarManager() {

    }

    public void setClientTaggedEntity(LivingEntity entity) {
        this.clientTaggedEntity = entity;
    }

    public LivingEntity getClientTaggedEntity() {
        return this.clientTaggedEntity;
    }

    public static ClientBossBar getClientBossBar() {
        return clientBossBar;
    }

}
