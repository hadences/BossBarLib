package net.hadences;

import net.fabricmc.api.ClientModInitializer;
import net.hadences.common.CustomBossBarManager;

public class BossBarLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BossBarLib.LOGGER.info("BossBarLibClient initialized");
        CustomBossBarManager.initClient();
    }
}
