package net.hadences.mixin;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hadences.BossBarLib;
import net.hadences.network.ModPackets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method="damage", at=@At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.getAttacker() instanceof ServerPlayerEntity attacker) {
            int entityID = ((LivingEntity)(Object)this).getId();
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeInt(entityID);
            ServerPlayNetworking.send(attacker, ModPackets.SET_TAGGED_ENTTIY, buffer);
        }
    }

}

