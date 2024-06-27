package net.hadences.common;

import net.minecraft.entity.boss.BossBar;

public class CustomBossBar {
    private BossBar.Color color;
    private BossBar.Style style;
    private boolean darkenSky;
    private boolean playBossMusic;
    private boolean createWorldFog;
    private double visibleDistance;

    private String overlayID;
    private int overlayFrames;
    private boolean hasOverlay;

    public CustomBossBar(BossBar.Color color, BossBar.Style style, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog) {
        this.color = color;
        this.style = style;
        this.darkenSky = darkenScreen;
        this.playBossMusic = playBossMusic;
        this.createWorldFog = createWorldFog;
        this.visibleDistance = 30.0D;
        this.hasOverlay = false;
    }

    public CustomBossBar(BossBar.Color color, BossBar.Style style, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog, double visibleDistance) {
        this.color = color;
        this.style = style;
        this.darkenSky = darkenScreen;
        this.playBossMusic = playBossMusic;
        this.createWorldFog = createWorldFog;
        this.visibleDistance = visibleDistance;
        this.hasOverlay = false;
    }

    public CustomBossBar(BossBar.Color color, BossBar.Style style, boolean darkenScreen,
                         boolean playBossMusic, boolean createWorldFog, double visibleDistance,
                         String overlayID, int overlayFrames) {
        this.color = color;
        this.style = style;
        this.darkenSky = darkenScreen;
        this.playBossMusic = playBossMusic;
        this.createWorldFog = createWorldFog;
        this.visibleDistance = visibleDistance;
        this.overlayID = overlayID;
        this.overlayFrames = overlayFrames;
        this.hasOverlay = true;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    public String getOverlayID() {
        return overlayID;
    }

    public int getOverlayFrames() {
        return overlayFrames;
    }

    public Double getVisibleDistance() {
        return visibleDistance;
    }

    public void setVisibleDistance(Double visibleDistance) {
        this.visibleDistance = visibleDistance;
    }

    public BossBar.Color getColor() {
        return color;
    }

    public void setColor(BossBar.Color color) {
        this.color = color;
    }

    public BossBar.Style getStyle() {
        return style;
    }

    public void setStyle(BossBar.Style style) {
        this.style = style;
    }

    public boolean isDarkenSky() {
        return darkenSky;
    }

    public void setDarkenSky(boolean darkenSky) {
        this.darkenSky = darkenSky;
    }

    public boolean isPlayBossMusic() {
        return playBossMusic;
    }

    public void setPlayBossMusic(boolean playBossMusic) {
        this.playBossMusic = playBossMusic;
    }

    public boolean isCreateWorldFog() {
        return createWorldFog;
    }

    public void setCreateWorldFog(boolean createWorldFog) {
        this.createWorldFog = createWorldFog;
    }

}
