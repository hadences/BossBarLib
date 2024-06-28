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
		CustomBossBarManager.initServer();

		CustomBossBarRegistry.getInstance().registerBossBar(EntityType.PIG, new CustomBossBar(0x970300, BossBar.Style.PROGRESS,
				false, false, false));

		CustomBossBarRegistry.getInstance().registerBossBar(EntityType.SHEEP, new CustomBossBar(0x63c74d, BossBar.Style.PROGRESS,
				false, false, false,
				30.0, new Identifier(BossBarLib.MOD_ID, "textures/boss_bars/template"), 1));
	}
}
