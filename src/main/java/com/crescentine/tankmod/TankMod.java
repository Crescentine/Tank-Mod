package com.crescentine.tankmod;

import com.crescentine.tankmod.shell.ShellEntity;
import com.crescentine.tankmod.shell.ShellItem;
import com.crescentine.tankmod.tank.TankEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.GeckoLib;

public class TankMod implements ModInitializer {
	public static final EntityType<ShellEntity> ShellEntityType = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(TankMod.MOD_ID, "tank_shell"),
			FabricEntityTypeBuilder.<ShellEntity>create(SpawnGroup.MISC, ShellEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build()
	);

	public static final Item ShellEntityItem = new ShellItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
	public static final Item TANK_CONTROLLER = new Item(new FabricItemSettings().group(ItemGroup.COMBAT));
	public static final Item NETHERITE_WHEEL = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static String MOD_ID = "trajanstanks";
	public static Identifier TANK_ENTITY = new Identifier(MOD_ID,"tank");
	public static final EntityType<TankEntity> TANK_ENTITY_TYPE = FabricEntityTypeBuilder.create(SpawnGroup.MISC,TankEntity::new).dimensions(EntityDimensions.fixed(4.0f,2.0f)).trackRangeBlocks(8).build();
	public static final Item TANK_ITEM = new SpawnEggItem(TANK_ENTITY_TYPE, 12895428, 11382189, new Item.Settings().group(ItemGroup.MISC));
	public static final ItemGroup TANKS_GROUP = FabricItemGroupBuilder.create(
			new Identifier("trajanstanks", "tanks_groups"))
			.icon(() -> new ItemStack(TANK_ITEM)).
					appendItems(stacks -> {
						stacks.add(new ItemStack(TANK_ITEM));
						stacks.add(new ItemStack(TANK_CONTROLLER));
						stacks.add(new ItemStack(NETHERITE_WHEEL)); }).build();

	//public static final Item TANK_ITEM = new SpawnEggItem(EntityType.TANK_ENTITY, 12895428, 11382189, new Item.Settings().group(ItemGroup.COMBAT));//
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("trajanstanks", "tank_controller"), TANK_CONTROLLER);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "shell_item"), ShellEntityItem);
		GeckoLib.initialize();
		Registry.register(Registry.ENTITY_TYPE, TANK_ENTITY, TANK_ENTITY_TYPE);
		FabricDefaultAttributeRegistry.register(TANK_ENTITY_TYPE, TankEntity.createTankAttributes());
		Registry.register(Registry.ITEM, new Identifier("trajanstanks", "tank_item"), TANK_ITEM);
		Registry.register(Registry.ITEM, new Identifier("trajanstanks", "netherite_wheel"), NETHERITE_WHEEL);

		ServerPlayNetworking.registerGlobalReceiver(new Identifier("shoot"), (server, player, handler, buf, sender) -> {
			
			if(player.getVehicle() instanceof TankEntity) {
				
				TankEntity tank = (TankEntity) player.getVehicle();
				tank.shoot(player);
				
			}
			
		});

	}
}
