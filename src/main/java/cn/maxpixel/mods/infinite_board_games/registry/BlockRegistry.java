package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.block.GameTableBlock;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfiniteBoardGames.MODID);

    public static final DeferredBlock<GameTableBlock> GAME_TABLE = BLOCKS.registerBlock(GameTableBlock.ID, GameTableBlock::new,
            p -> p.strength(2.0F, 3.0F)
                    .noOcclusion()
                    .sound(SoundType.WOOD));

}
