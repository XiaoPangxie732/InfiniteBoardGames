package cn.maxpixel.mods.infinite_board_games.block;

import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GameTableBlock extends BaseEntityBlock {
    public static final String ID = "game_table";
    public static final MapCodec<GameTableBlock> CODEC = simpleCodec(GameTableBlock::new);
    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 1, 16);

    public GameTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<GameTableBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new GameTableBlockEntity(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof GameTableBlockEntity be) {
            if (player.isCreative() && itemStack.getItem() instanceof BlockItem blockItem) {
                Block itemBlock = blockItem.getBlock();
                BlockState itemState = itemBlock.defaultBlockState();
                if (!itemState.hasBlockEntity() && itemState.isCollisionShapeFullBlock(level, pos)) {
                    if (!level.isClientSide()) {
                        be.setBaseTexture(itemBlock);
                        be.updateClients();
                    }
                    return InteractionResult.SUCCESS;
                }
            } else if (itemStack.is(ItemRegistry.GAME) && be.canOpen(player) && be.getTheItem().isEmpty()) {
                if (be.canOpen(player)) {
                    be.setTheItem(itemStack.split(1));
                } else {
                    BaseContainerBlockEntity.sendChestLockedNotifications(pos.getCenter(), player, be.getName());
                    return InteractionResult.FAIL;
                }
                return InteractionResult.SUCCESS;
            }
        } else return InteractionResult.FAIL;
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof GameTableBlockEntity be) {
            if (player.isShiftKeyDown()) {
                player.openMenu(be);
            } else if (!be.isInGame()) {
                // TODO: open screen
            }
        }
        return InteractionResult.SUCCESS;
    }
}