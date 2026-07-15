package com.yourname.modnamehere;

import com.yourname.modnamehere.item.ShrinkBombItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShrinkBombItems {
	public static final Item SHRINK_BOMB = new ShrinkBombItem(new Item.Settings());

	public static void registerItems() {
		Registry.register(
			Registries.ITEM,
			new Identifier(ShrinkBomb.MOD_ID, "shrink_bomb"),
			SHRINK_BOMB
		);
	}
}