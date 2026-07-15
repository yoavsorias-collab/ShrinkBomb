package com.yourname.modnamehere.item;

import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class ShrinkBombItem extends Item {

	public ShrinkBombItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity playerEntity, Hand hand) {
		if (!world.isClient && playerEntity.isCreative()) {
			// TODO: Implement bomb throwing logic
			playerEntity.sendMessage(net.minecraft.text.Text.literal("ShrinkBomb thrown!"), false);
		}
		return ActionResult.SUCCESS;
	}
}