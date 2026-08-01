package com.yourname.modnamehere.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.yourname.modnamehere.util.EmeraldReleaseSystem;

public class ShrinkBombBlockEntity extends BlockEntity {
	private int releaseTimer = 0;
	private static final int RELEASE_DELAY = 3600; // 3 minutes in ticks (20 ticks = 1 second)
	private boolean hasReleased = false;

	public ShrinkBombBlockEntity(BlockPos pos, BlockState state) {
		super(ShrinkBombBlockEntityType.SHRINK_BOMB_BLOCK_ENTITY, pos, state);
	}

	/**
	 * Initialize the release timer when the bomb explodes
	 */
	public void startReleaseTimer() {
		this.releaseTimer = RELEASE_DELAY;
		this.hasReleased = false;
	}

	/**
	 * Called every tick by the world
	 */
	@Override
	public void tick() {
		if (this.world == null || this.world.isClient) {
			return;
		}

		if (this.releaseTimer > 0 && !this.hasReleased) {
			this.releaseTimer--;

			// Release emeralds when timer reaches 0
			if (this.releaseTimer <= 0) {
				releaseEmeralds();
				this.hasReleased = true;
			}
		}
	}

	/**
	 * Release emeralds at this position
	 */
	private void releaseEmeralds() {
		if (this.world != null && !this.world.isClient) {
			EmeraldReleaseSystem.releaseEmeralds(this.world, this.pos);
		}
	}

	/**
	 * Save data to NBT
	 */
	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		nbt.putInt("ReleaseTimer", this.releaseTimer);
		nbt.putBoolean("HasReleased", this.hasReleased);
	}

	/**
	 * Load data from NBT
	 */
	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		this.releaseTimer = nbt.getInt("ReleaseTimer");
		this.hasReleased = nbt.getBoolean("HasReleased");
	}

	public int getReleaseTimer() {
		return this.releaseTimer;
	}

	public boolean hasReleased() {
		return this.hasReleased;
	}

	public static int getReleaseDelay() {
		return RELEASE_DELAY;
	}
}
