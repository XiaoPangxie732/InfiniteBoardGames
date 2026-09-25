package cn.maxpixel.mods.infinite_board_games.client.renderer.blockentity.state;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.AtlasIds;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class GameTableBlockEntityRenderState extends BlockEntityRenderState {
    public TextureAtlasSprite baseTexture = Minecraft.getInstance().getAtlasManager().get(Sheets.BLOCKS_MAPPER
            .apply(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_OAK_LOG)));
    public BlockState blockState = Blocks.AIR.defaultBlockState();
    public @Nullable Level level;
}