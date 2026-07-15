package com.yourname.modnamehere.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ShrinkBombEntity extends ThrownItemEntity {

	public static final int FUSE_TIME = 40; // 2 seconds (40 ticks)
	private int fuseTimer = FUSE_TIME;

	public ShrinkBombEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public ShrinkBombEntity(World world, double x, double y, double z) {
		super(ShrinkBombEntityType.SHRINK_BOMB_ENTITY_TYPE, x, y, z, world);
		this.fuseTimer = FUSE_TIME;
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.getWorld().isClient) {
			this.fuseTimer--;

			// Explosion happens when fuse reaches 0
			if (this.fuseTimer <= 0) {
				this.explode();
				this.discard();
			}
		}
	}

	private void explode() {
		World world = this.getWorld();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();

		// TODO: Create the glass box and shrinking mechanism
		// For now, just a placeholder explosion
		world.createExplosion(
			this,
			x, y, z,
			0f, // radius (no block damage)
			false
		);
	}

	@Override
	protected Item getDefaultItem() {
		return ShrinkBombItems.SHRINK_BOMB;
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		// Bomb explodes on collision
		if (!this.getWorld().isClient) {
			this.explode();
			this.discard();
		}
	}

	public int getFuseTimer() {
		return this.fuseTimer;
	}
}
