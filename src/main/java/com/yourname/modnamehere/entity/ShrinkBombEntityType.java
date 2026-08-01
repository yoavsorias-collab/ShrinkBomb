package com.yourname.modnamehere.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.yourname.modnamehere.ShrinkBomb;

public class ShrinkBombEntityType {
	public static final EntityType<ShrinkBombEntity> SHRINK_BOMB_ENTITY_TYPE = EntityType.Builder
		.<ShrinkBombEntity>create(ShrinkBombEntity::new, SpawnGroup.MISC)
		.dimensions(0.25f, 0.25f)
		.maxTrackingRange(4)
		.trackingTickInterval(10)
		.build();

	public static void registerEntityTypes() {
		Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(ShrinkBomb.MOD_ID, "shrink_bomb"),
			SHRINK_BOMB_ENTITY_TYPE
		);
	}
}
