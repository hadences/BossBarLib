package net.hadences.mixin;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hadences.BossBarLib;
import net.hadences.common.CustomBossBarRegistry;
import net.hadences.network.ModPackets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method="damage", at=@At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.getAttacker() instanceof ServerPlayerEntity attacker) {
            if(!hasBossBar(((LivingEntity)(Object)this).getType())) return;
            int entityID = ((LivingEntity)(Object)this).getId();
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(entityID);
            ServerPlayNetworking.send(attacker, ModPackets.SET_TAGGED_ENTTIY, buffer);
        }
    }

    @Unique
    private boolean hasBossBar(EntityType<?> entityType){
        return CustomBossBarRegistry.getInstance().getBossBarMap(entityType) != null;
    }

}

