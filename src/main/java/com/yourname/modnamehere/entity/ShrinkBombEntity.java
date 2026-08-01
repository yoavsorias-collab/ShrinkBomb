package com.yourname.modnamehere.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.yourname.modnamehere.util.GlassBoxBuilder;
import com.yourname.modnamehere.util.ScalingSystem;
import com.yourname.modnamehere.util.EmeraldReleaseSystem;
import com.yourname.modnamehere.ShrinkBombItems;

public class ShrinkBombEntity extends ThrownItemEntity {

	public static final int FUSE_TIME = 40; // 2 seconds (40 ticks)
	private int fuseTimer = FUSE_TIME;
	private boolean hasExploded = false;
	private BlockPos explosionCenter;

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
			if (this.fuseTimer <= 0 && !this.hasExploded) {
				this.explode();
				this.hasExploded = true;
				this.discard();
			}
		}
	}

	private void explode() {
		World world = this.getWorld();
		this.explosionCenter = this.getBlockPos();

		// Create glass box
		GlassBoxBuilder.buildGlassBox(world, this.explosionCenter);

		// Scale all entities inside the box
		world.getEntitiesByClass(
			net.minecraft.entity.Entity.class,
			this.getBoundingBox().expand(15),
			entity -> entity != this && GlassBoxBuilder.isInsideBox(entity.getBlockPos(), this.explosionCenter)
		).forEach(ScalingSystem::scaleEntity);

		// Schedule emerald release (3 minutes later)
		EmeraldReleaseSystem.scheduleEmeraldRelease(world, this.explosionCenter);

		// TODO: Add particles and sounds for explosion effect
	}

	@Override
	protected Item getDefaultItem() {
		return ShrinkBombItems.SHRINK_BOMB;
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		// Bomb explodes on collision
		if (!this.getWorld().isClient && !this.hasExploded) {
			this.explode();
			this.hasExploded = true;
			this.discard();
		}
	}

	public int getFuseTimer() {
		return this.fuseTimer;
	}

	public BlockPos getExplosionCenter() {
		return this.explosionCenter;
	}
}
