package net.hadences;

import net.fabricmc.api.ClientModInitializer;

public class BossBarLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BossBarLib.LOGGER.info("BossBarLibClient initialized");
    }
}
