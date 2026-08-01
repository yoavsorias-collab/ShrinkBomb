package com.yourname.modnamehere.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.yourname.modnamehere.entity.ShrinkBombEntity;

public class ShrinkBombItem extends Item {

	public ShrinkBombItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack itemStack = playerEntity.getStackInHand(hand);

		if (!world.isClient) {
			// Create and throw the bomb entity
			ShrinkBombEntity bombEntity = new ShrinkBombEntity(world, playerEntity.getX(), playerEntity.getEyeY() - 0.1, playerEntity.getZ());

			// Set velocity based on player look direction
			bombEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 1.5f, 1.0f);

			// Add entity to world
			world.spawnEntity(bombEntity);

			// Remove item from player's hand
			if (!playerEntity.getAbilities().creativeMode) {
				itemStack.decrement(1);
			}

			return ActionResult.SUCCESS;
		}

		return ActionResult.SUCCESS;
	}
}
