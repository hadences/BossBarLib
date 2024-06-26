package net.hadences;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BossBarLib implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("bossbarlib");
	public static final String MOD_ID = "bossbarlib";
	@Override
	public void onInitialize() {
		LOGGER.info("BossBarLib initialized");
	}
}
