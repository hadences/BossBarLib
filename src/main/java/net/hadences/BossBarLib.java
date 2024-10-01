package net.hadences;

import net.fabricmc.api.ModInitializer;

import net.hadences.common.CustomBossBar;
import net.hadences.common.CustomBossBarManager;
import net.hadences.common.CustomBossBarRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BossBarLib implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("bossbarlib");
	public static final String MOD_ID = "bossbarlib";

	@Override
	public void onInitialize() {
		LOGGER.info("BossBarLib initialized");
//		CustomBossBarManager.initServer();
	}

}
