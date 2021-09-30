package com.crescentine.tankmod.mixin;

import com.crescentine.tankmod.TankMod;
import com.crescentine.tankmod.shell.ShellEntity;
import com.crescentine.tankmod.tank.TankEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.Tag;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.example.registry.ItemRegistry;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

   /* @Nullable
    private Entity trajanstanks$vehicle;
    private ShellEntity trajanstanks$shellEntity;
    @Inject(at = @At("HEAD"), method =
            "tick")
    public void tick(CallbackInfo ci) {
        if ((Entity) (Object) this instanceof PlayerEntity playerEntity) {
            if (trajanstanks$vehicle instanceof TankEntity)
                if (playerEntity.getInventory().contains((Tag<Item>) TankMod.ShellEntityItem)) ;
            {
                playerEntity.getEntityWorld().spawnEntity(trajanstanks$shellEntity);
            }
        }
    }*/
}