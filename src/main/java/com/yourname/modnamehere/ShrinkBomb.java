package com.yourname.modnamehere;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yourname.modnamehere.entity.ShrinkBombEntityType;

public class ShrinkBomb implements ModInitializer {
	public static final String MOD_ID = "shrinkbomb";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("ShrinkBomb mod initialized!");
		ShrinkBombItems.registerItems();
		ShrinkBombBlocks.registerBlocks();
		ShrinkBombEntityType.registerEntityTypes();
	}
}
