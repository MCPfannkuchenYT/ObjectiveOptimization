package de.pfannekuchen.objectiveoptimization.blocks;

import de.pfannekuchen.objectiveoptimization.ObjectiveOptimization;
import de.pfannekuchen.objectiveoptimization.blocks.entity.CreativeStorageCubeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This is the creative storage cube
 * @author Pancake
 */
public class CreativeStorageCubeBlock extends BlockWithEntity {

	/**
	 * Constructor for the Registry
	 * @param settings Settings such as hardness for the block
	 */
	public CreativeStorageCubeBlock(Settings settings) {
		super(settings);
	}

	/**
	 * Creates a block entity for the block
	 */
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CreativeStorageCubeBlockEntity(pos, state);
	}

	/**
	 * Saves the Block Entity when breaking the block
	 */
	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockEntity entity = world.getBlockEntity(pos);
		if (entity instanceof CreativeStorageCubeBlockEntity) {
			if (!world.isClient) {
				ItemStack itemStack = new ItemStack(ObjectiveOptimization.CREATIVE_STORAGE_CUBE);
				itemStack.setSubNbt("BlockEntityTag", ((CreativeStorageCubeBlockEntity) entity).writeCustomNbt(new NbtCompound()));
				
				ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemStack);
				itemEntity.setToDefaultPickupDelay();
				world.spawnEntity(itemEntity);	
			}
		}
		super.onBreak(world, pos, state, player);
	}
	
}
