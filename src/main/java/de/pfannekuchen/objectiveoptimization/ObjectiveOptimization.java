package de.pfannekuchen.objectiveoptimization;

import de.pfannekuchen.objectiveoptimization.blocks.CreativeStorageCubeBlock;
import de.pfannekuchen.objectiveoptimization.blocks.entity.CreativeStorageCubeBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Initializes the Fabric Mod for the server and client. 
 * @author Pancake
 */
public class ObjectiveOptimization implements ModInitializer {

	/**
	 * Register of the Creative Storage Cube
	 */
	public static final CreativeStorageCubeBlock CREATIVE_STORAGE_CUBE = new CreativeStorageCubeBlock(FabricBlockSettings.of(Material.METAL).resistance(1200.0f).hardness(4f));
	
	/**
	 * Register of the Creative Storage Cube Entity
	 * (The data holder for the {@link #CREATIVE_STORAGE_CUBE})
	 */
	public static BlockEntityType<CreativeStorageCubeBlockEntity> CREATIVE_STORAGE_CUBE_ENTITY;
	
	/**
	 * The Mod ID for this mod
	 */
	public static final String MODID = "objectiveoptimization";
	
	@Override
	public void onInitialize() {
		// Register all Block Entities for the mod
		CREATIVE_STORAGE_CUBE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MODID + ":creative_storage_cube_entity", FabricBlockEntityTypeBuilder.create(CreativeStorageCubeBlockEntity::new, CREATIVE_STORAGE_CUBE).build(null));

		// Registers all blocks for the mod
		Registry.register(Registry.BLOCK, new Identifier(MODID, "creative_storage_cube"), CREATIVE_STORAGE_CUBE);
		
		// Register all items for specific blocks
		Registry.register(Registry.ITEM, new Identifier(MODID, "creative_storage_cube"), new BlockItem(CREATIVE_STORAGE_CUBE, new FabricItemSettings().group(ItemGroup.MISC)));
	
	}

}
