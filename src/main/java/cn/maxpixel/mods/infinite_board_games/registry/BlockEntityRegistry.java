package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.block.GameTableBlock;
import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityRegistry {
    static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, InfiniteBoardGames.MODID);

    public static final Supplier<BlockEntityType<GameTableBlockEntity>> GAME_TABLE = BLOCK_ENTITY_TYPES.register(
            GameTableBlock.ID, () -> new BlockEntityType<>(GameTableBlockEntity::new, BlockRegistry.GAME_TABLE.get()));
}