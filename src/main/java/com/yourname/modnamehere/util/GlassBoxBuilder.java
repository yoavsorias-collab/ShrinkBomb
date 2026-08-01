package com.yourname.modnamehere.util;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlassBoxBuilder {
	private static final int BOX_SIZE = 25;
	private static final int BOX_HEIGHT = 25;

	public static void buildGlassBox(World world, BlockPos center) {
		BlockPos minPos = center.add(-BOX_SIZE / 2, 0, -BOX_SIZE / 2);
		BlockPos maxPos = center.add(BOX_SIZE / 2, BOX_HEIGHT, BOX_SIZE / 2);

		// Build walls (glass)
		for (int x = minPos.getX(); x <= maxPos.getX(); x++) {
			for (int z = minPos.getZ(); z <= maxPos.getZ(); z++) {
				// Walls at edges
				if (x == minPos.getX() || x == maxPos.getX() || z == minPos.getZ() || z == maxPos.getZ()) {
					for (int y = minPos.getY(); y <= maxPos.getY(); y++) {
						BlockPos pos = new BlockPos(x, y, z);
						world.setBlockState(pos, Blocks.GLASS.getDefaultState());
					}
				}
			}
		}

		// Build floor
		for (int x = minPos.getX(); x <= maxPos.getX(); x++) {
			for (int z = minPos.getZ(); z <= maxPos.getZ(); z++) {
				BlockPos floorPos = new BlockPos(x, minPos.getY() - 1, z);
				world.setBlockState(floorPos, Blocks.GLASS.getDefaultState());
			}
		}

		// Build ceiling
		for (int x = minPos.getX(); x <= maxPos.getX(); x++) {
			for (int z = minPos.getZ(); z <= maxPos.getZ(); z++) {
				BlockPos ceilingPos = new BlockPos(x, maxPos.getY() + 1, z);
				world.setBlockState(ceilingPos, Blocks.GLASS.getDefaultState());
			}
		}
	}

	public static void removeGlassBox(World world, BlockPos center) {
		BlockPos minPos = center.add(-BOX_SIZE / 2, 0, -BOX_SIZE / 2);
		BlockPos maxPos = center.add(BOX_SIZE / 2, BOX_HEIGHT, BOX_SIZE / 2);

		for (int x = minPos.getX(); x <= maxPos.getX(); x++) {
			for (int y = minPos.getY() - 1; y <= maxPos.getY() + 1; y++) {
				for (int z = minPos.getZ(); z <= maxPos.getZ(); z++) {
					BlockPos pos = new BlockPos(x, y, z);
					if (world.getBlockState(pos).getBlock() == Blocks.GLASS) {
						world.breakBlock(pos, false);
					}
				}
			}
		}
	}

	public static boolean isInsideBox(BlockPos pos, BlockPos center) {
		BlockPos minPos = center.add(-BOX_SIZE / 2, 0, -BOX_SIZE / 2);
		BlockPos maxPos = center.add(BOX_SIZE / 2, BOX_HEIGHT, BOX_SIZE / 2);

		return pos.getX() >= minPos.getX() && pos.getX() <= maxPos.getX() &&
			   pos.getY() >= minPos.getY() && pos.getY() <= maxPos.getY() &&
			   pos.getZ() >= minPos.getZ() && pos.getZ() <= maxPos.getZ();
	}
}
