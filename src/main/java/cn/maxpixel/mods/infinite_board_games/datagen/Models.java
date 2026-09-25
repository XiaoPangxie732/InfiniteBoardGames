package cn.maxpixel.mods.infinite_board_games.datagen;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.registry.BlockRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class Models extends ModelProvider {
    public Models(PackOutput output) {
        super(output, InfiniteBoardGames.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(BlockRegistry.GAME_TABLE.get(),
                BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(
                        BlockRegistry.GAME_TABLE.get(),
                        TextureMapping.defaultTexture(Blocks.STRIPPED_OAK_LOG),
                        blockModels.modelOutput
                ))));

        itemModels.generateFlatItem(ItemRegistry.GAME.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.GAME_CARD.get(), ModelTemplates.FLAT_ITEM);
    }
}
