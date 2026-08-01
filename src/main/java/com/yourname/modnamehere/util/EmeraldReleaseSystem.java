package com.yourname.modnamehere.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EmeraldReleaseSystem {
	private static final int RELEASE_TIME = 3600; // 3 minutes (3600 ticks)
	private static final int EMERALD_COUNT = 5;

	public static void scheduleEmeraldRelease(World world, BlockPos boxCenter) {
		// TODO: Use a proper scheduler or BlockEntity for persistence
		// For now, we'll spawn emeralds immediately as placeholder
		releaseEmeralds(world, boxCenter);
	}

	public static void releaseEmeralds(World world, BlockPos boxCenter) {
		// Spawn emeralds at the center of the box
		for (int i = 0; i < EMERALD_COUNT; i++) {
			double offsetX = (Math.random() - 0.5) * 5;
			double offsetZ = (Math.random() - 0.5) * 5;
			double offsetY = Math.random() * 3;

			double x = boxCenter.getX() + offsetX;
			double y = boxCenter.getY() + offsetY;
			double z = boxCenter.getZ() + offsetZ;

			ItemStack emeraldStack = new ItemStack(Items.EMERALD, 1);
			ItemEntity emeraldEntity = new ItemEntity(world, x, y, z, emeraldStack);
			world.spawnEntity(emeraldEntity);
		}
	}

	public static void removeGlassBoxAfterRelease(World world, BlockPos boxCenter) {
		GlassBoxBuilder.removeGlassBox(world, boxCenter);
	}
}
