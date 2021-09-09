package de.pfannekuchen.objectiveoptimization.blocks.entity;

import java.util.ArrayList;

import de.pfannekuchen.objectiveoptimization.ObjectiveOptimization;
import de.pfannekuchen.objectiveoptimization.blocks.CreativeStorageCubeBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

/**
 * This is the Block Entity for the {@link CreativeStorageCubeBlock} which holds all of its data and can convert it into Nbt
 * @author Pancake
 */
public class CreativeStorageCubeBlockEntity extends BlockEntity {

	public ArrayList<ItemStack> stack = new ArrayList<>();
	
	/**
	 * Constructor for the Registry
	 * @param pos Position of the Block Entity
	 * @param state State of the Block linked to the Block Entity
	 */
	public CreativeStorageCubeBlockEntity(BlockPos pos, BlockState state) {
		super(ObjectiveOptimization.CREATIVE_STORAGE_CUBE_ENTITY, pos, state);
	}

	/**
	 * Writes the Block Entity into an NbtCompound for serialization
	 * ItemCount -> Item Count
	 * Item1 -> Serialized Item Stack
	 * Item2 
	 * Item3..
	 */
	public NbtCompound writeCustomNbt(NbtCompound nbt) {
		nbt.putLong("ItemCount", stack.size());
		for (int i = 0; i < stack.size(); i++) {
			NbtCompound item = stack.get(i).writeNbt(new NbtCompound());
			nbt.put("Item" + i, item);
		}
		return nbt;
	}
	
	/**
	 * WARNING: THIS IS FOR LOADING BLOCKS 
	 */
	@Override
	public void readNbt(NbtCompound nbt) {
		readCustomNbt(nbt);
		super.readNbt(nbt);
	}
	
	/**
	 * WARNING: THIS IS FOR SAVING BLOCKS 
	 */
	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt = writeCustomNbt(nbt);
		return super.writeNbt(nbt);
	}
	
	/**
	 * Reads the Block Entity from an NbtCompound for deserialization
	 * Structure shown in {@link #writeNbt(NbtCompound)}
	 */
	public void readCustomNbt(NbtCompound nbt) {
		stack = new ArrayList<>(nbt.getInt("ItemCount"));
		for (int i = 0; i < stack.size(); i++) {
			stack.set(i, ItemStack.fromNbt(nbt.getCompound("Item" + i)));
		}
	}
	
}
