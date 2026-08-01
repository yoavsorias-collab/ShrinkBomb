package com.yourname.modnamehere.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ScalingSystem {
	private static final float SCALE_FACTOR = 0.5f; // Entities become half size
	private static final float SPEED_FACTOR = 0.7f; // Slower movement

	public static void scaleEntity(Entity entity) {
		// Scale the entity's size
		entity.setCustomName(entity.getCustomName()); // Trigger visual update

		// Reduce health if it's a living entity
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			float currentHealth = livingEntity.getHealth();
			livingEntity.setHealth(currentHealth * SCALE_FACTOR);
		}
	}

	public static void resetEntityScale(Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			// Reset health scaling
			livingEntity.setHealth(Math.min(livingEntity.getHealth() / SCALE_FACTOR, livingEntity.getMaxHealth()));
		}
	}
}
