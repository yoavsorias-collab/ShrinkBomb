package com.yourname.modnamehere;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShrinkBombBlocks {
	// Unbreakable glass block
	public static final Block SHRINK_GLASS = new Block(Block.Settings.copy(Blocks.GLASS)
		.strength(-1.0f) // Unbreakable
	);

	public static void registerBlocks() {
		Registry.register(
			Registries.BLOCK,
			new Identifier(ShrinkBomb.MOD_ID, "shrink_glass"),
			SHRINK_GLASS
		);
	}
}