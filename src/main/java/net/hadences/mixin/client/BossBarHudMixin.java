package net.hadences.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hadences.BossBarLib;
import net.hadences.common.CustomBossBar;
import net.hadences.common.CustomBossBarManager;
import net.hadences.common.CustomBossBarRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {
    @Unique
    private static final Identifier[] BACKGROUND_TEXTURES = new Identifier[]{new Identifier("boss_bar/pink_background"), new Identifier("boss_bar/blue_background"), new Identifier("boss_bar/red_background"), new Identifier("boss_bar/green_background"), new Identifier("boss_bar/yellow_background"), new Identifier("boss_bar/purple_background"), new Identifier("boss_bar/white_background")};
    @Unique
    private static final Identifier[] PROGRESS_TEXTURES = new Identifier[]{new Identifier("boss_bar/pink_progress"), new Identifier("boss_bar/blue_progress"), new Identifier("boss_bar/red_progress"), new Identifier("boss_bar/green_progress"), new Identifier("boss_bar/yellow_progress"), new Identifier("boss_bar/purple_progress"), new Identifier("boss_bar/white_progress")};
    @Unique
    private static final Identifier[] NOTCHED_BACKGROUND_TEXTURES = new Identifier[]{new Identifier("boss_bar/notched_6_background"), new Identifier("boss_bar/notched_10_background"), new Identifier("boss_bar/notched_12_background"), new Identifier("boss_bar/notched_20_background")};
    @Unique
    private static final Identifier[] NOTCHED_PROGRESS_TEXTURES = new Identifier[]{new Identifier("boss_bar/notched_6_progress"), new Identifier("boss_bar/notched_10_progress"), new Identifier("boss_bar/notched_12_progress"), new Identifier("boss_bar/notched_20_progress")};

    @Shadow @Final private MinecraftClient client;


    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void onRender(DrawContext context, CallbackInfo ci){
        LivingEntity livingEntity = CustomBossBarManager.INSTANCE.getClientTaggedEntity();
        if(livingEntity != null && this.hasBossBar(livingEntity.getType())
                && !livingEntity.isSpectator() && !livingEntity.isInvisible() && livingEntity.isAlive()){
            int i = context.getScaledWindowWidth();
            int j = 12;
            int k = i / 2 - 91;

            CustomBossBar customBossBar = CustomBossBarRegistry.getInstance().getBossBarMap(livingEntity.getType());
            BossBar bossBar = CustomBossBarManager.getClientBossBar();

            if(this.isWithinDistance(livingEntity, customBossBar)) {
                //customize the boss bar
                bossBar.setColor(BossBar.Color.WHITE);
                bossBar.setStyle(customBossBar.getStyle());
                bossBar.setDarkenSky(customBossBar.isDarkenSky());
                bossBar.setDragonMusic(customBossBar.isPlayBossMusic());
                bossBar.setThickenFog(customBossBar.isCreateWorldFog());

                bossBar.setPercent(livingEntity.getHealth() / livingEntity.getMaxHealth());
                if(customBossBar.hasOverlay()){
                    j = 18;
                }
                this.onRenderBossBar(context, k, j, bossBar, customBossBar.getColor());
                Text text = livingEntity.getDisplayName();
                int m = this.client.textRenderer.getWidth(text);
                int n = i / 2 - m / 2 + 2;
                int o = j - (customBossBar.hasOverlay() ? 14 : 9);
                context.drawTextWithShadow(this.client.textRenderer, text, n, o, 0xFFFFFF);
                if(customBossBar.hasOverlay()){
                    this.onRenderOverlay(customBossBar, context, k, 2);
                }

                ci.cancel();
            }
        }
    }

    @Unique
    private void onRenderOverlay(CustomBossBar customBossBar, DrawContext context, int x, int y){
        int frames = customBossBar.getOverlayFrames();
        Identifier[] overlayTextures = customBossBar.getOverlayTextures();

        long millisPerFrame = 100;
        long currentTime = System.currentTimeMillis();
        int frameIndex = (int) ((currentTime / millisPerFrame) % frames);

        RenderSystem.enableBlend();
        context.drawTexture(overlayTextures[frameIndex], x-64, y, 0, 0, 310, 48, 310, 48);
        RenderSystem.disableBlend();
    }

    @Unique
    private boolean isWithinDistance(LivingEntity entity, CustomBossBar cbb){
        return entity.squaredDistanceTo(this.client.player) <= cbb.getVisibleDistance() * cbb.getVisibleDistance();
    }

    @Unique
    private boolean hasBossBar(EntityType<?> entityType){
        return CustomBossBarRegistry.getInstance().getBossBarMap(entityType) != null;
    }

    @Unique
    private void onRenderBossBar(DrawContext context, int x, int y, BossBar bossBar, int color) {
        this.onRenderBossBar(context, x, y, bossBar, 182, BACKGROUND_TEXTURES, NOTCHED_BACKGROUND_TEXTURES, color);
        int i = MathHelper.lerpPositive(bossBar.getPercent(), 0, 182);
        if (i > 0) {
            this.onRenderBossBar(context, x, y, bossBar, i, PROGRESS_TEXTURES, NOTCHED_PROGRESS_TEXTURES, color);
        }
    }

    @Unique
    private void onRenderBossBar(DrawContext context, int x, int y, BossBar bossBar, int width, Identifier[] textures, Identifier[] notchedTextures, int color) {
        Vector3f c = intToNormalizedRGBVector(color);
        RenderSystem.setShaderColor(c.x, c.y, c.z, 1.0F);
        context.drawGuiTexture(textures[BossBar.Color.WHITE.ordinal()], 182, 5, 0, 0, x, y, width, 5);
        if (bossBar.getStyle() != BossBar.Style.PROGRESS) {
            RenderSystem.enableBlend();
            context.drawGuiTexture(notchedTextures[bossBar.getStyle().ordinal() - 1], 182, 5, 0, 0, x, y, width, 5);
            RenderSystem.disableBlend();
        }
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Unique
    public Vector3f intToNormalizedRGBVector(int color) {
        float red = (color >> 16) & 0xFF;
        float green = (color >> 8) & 0xFF;
        float blue = color & 0xFF;

        return new Vector3f(red / 255f, green / 255f, blue / 255f);
    }
}
