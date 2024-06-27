package net.hadences.common;

import net.minecraft.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class CustomBossBarRegistry {
    private static final CustomBossBarRegistry INSTANCE = new CustomBossBarRegistry();
    private final Map<EntityType<?>, CustomBossBar> bossBarMap = new HashMap<>();

    public static CustomBossBarRegistry getInstance() {
        return INSTANCE;
    }

    public void registerBossBar(EntityType<?> entityType, CustomBossBar bossBar) {
        bossBarMap.put(entityType, bossBar);
    }

    public CustomBossBar getBossBarMap(EntityType<?> entityType) {
        return bossBarMap.get(entityType);
    }
}
