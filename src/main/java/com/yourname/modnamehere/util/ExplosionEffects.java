package com.yourname.modnamehere.util;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExplosionEffects {
	private static final int PARTICLE_COUNT = 50;
	private static final double PARTICLE_SPREAD = 10.0;

	public static void playExplosionEffects(World world, BlockPos centerPos) {
		if (!world.isClient) {
			// Play explosion sound
			world.playSound(
				null,
				centerPos.getX() + 0.5,
				centerPos.getY() + 0.5,
				centerPos.getZ() + 0.5,
				SoundEvents.ENTITY_GENERIC_EXPLODE,
				net.minecraft.sound.SoundCategory.BLOCKS,
				1.0f,
				1.0f
			);
		}

		// Spawn particles (server-side to sync to all clients)
		for (int i = 0; i < PARTICLE_COUNT; i++) {
			double offsetX = (Math.random() - 0.5) * PARTICLE_SPREAD;
			double offsetY = (Math.random() - 0.5) * PARTICLE_SPREAD;
			double offsetZ = (Math.random() - 0.5) * PARTICLE_SPREAD;

			double x = centerPos.getX() + 0.5 + offsetX;
			double y = centerPos.getY() + 0.5 + offsetY;
			double z = centerPos.getZ() + 0.5 + offsetZ;

			// Velocity for particle movement
			double velX = (Math.random() - 0.5) * 2;
			double velY = (Math.random() - 0.5) * 2;
			double velZ = (Math.random() - 0.5) * 2;

			world.addParticle(
				ParticleTypes.EXPLOSION,
				x, y, z,
				velX, velY, velZ
			);
		}

		// Add flame particles for visual effect
		for (int i = 0; i < PARTICLE_COUNT / 2; i++) {
			double offsetX = (Math.random() - 0.5) * PARTICLE_SPREAD;
			double offsetY = (Math.random() - 0.5) * PARTICLE_SPREAD;
			double offsetZ = (Math.random() - 0.5) * PARTICLE_SPREAD;

			double x = centerPos.getX() + 0.5 + offsetX;
			double y = centerPos.getY() + 0.5 + offsetY;
			double z = centerPos.getZ() + 0.5 + offsetZ;

			world.addParticle(
				ParticleTypes.FLAME,
				x, y, z,
				0, 0.1, 0
			);
		}
	}
}
