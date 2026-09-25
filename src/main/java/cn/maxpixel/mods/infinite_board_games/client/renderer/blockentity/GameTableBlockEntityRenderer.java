package cn.maxpixel.mods.infinite_board_games.client.renderer.blockentity;

import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import cn.maxpixel.mods.infinite_board_games.client.renderer.blockentity.state.GameTableBlockEntityRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

public class GameTableBlockEntityRenderer implements BlockEntityRenderer<GameTableBlockEntity, GameTableBlockEntityRenderState> {
    private static final Direction[] DIRECTIONS = Direction.values();
    private final BlockEntityRendererProvider.Context context;

    public GameTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public GameTableBlockEntityRenderState createRenderState() {
        return new GameTableBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(GameTableBlockEntity blockEntity, GameTableBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.baseTexture = context.sprites().get(Sheets.BLOCKS_MAPPER.apply(blockEntity.getBaseTextureLocation()));
        state.blockState = blockEntity.getBlockState();
        state.level = blockEntity.getLevel();
    }

    @Override
    public void submit(GameTableBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        submitNodeCollector.submitCustomGeometry(poseStack, Sheets.cutoutBlockSheet(), (pose, buffer) -> {
            var wrapped = state.baseTexture.wrap(buffer);

            int renderFaces = 0;
            BlockPos.MutableBlockPos neighbor = new BlockPos.MutableBlockPos();
            if (state.level != null) for (Direction d : DIRECTIONS) {
                renderFaces |= (Block.shouldRenderFace(state.level, state.blockPos, state.blockState, state.level
                        .getBlockState(neighbor.setWithOffset(state.blockPos, d)), d) ? 1 : 0) << d.ordinal();
            }
            BaseRenderer.submitBase(pose, wrapped, state.lightCoords, renderFaces);
        });
    }

    private static class BaseRenderer {
        private static void submitBase(PoseStack.Pose pose, VertexConsumer buffer, int lightCoords, int renderFaces) {
            if ((renderFaces & (1 << Direction.UP.ordinal())) != 0) {
                addUpVertex(pose, buffer, 0, 0, lightCoords);
                addUpVertex(pose, buffer, 0, 16, lightCoords);
                addUpVertex(pose, buffer, 16, 16, lightCoords);
                addUpVertex(pose, buffer, 16, 0, lightCoords);
            }

            if ((renderFaces & (1 << Direction.DOWN.ordinal())) != 0) {
                addDownVertex(pose, buffer, 0, 0, lightCoords);
                addDownVertex(pose, buffer, 16, 0, lightCoords);
                addDownVertex(pose, buffer, 16, 16, lightCoords);
                addDownVertex(pose, buffer, 0, 16, lightCoords);
            }

            if ((renderFaces & (1 << Direction.SOUTH.ordinal())) != 0) {
                addSouthVertex(pose, buffer, 0, 0, lightCoords);
                addSouthVertex(pose, buffer, 16, 0, lightCoords);
                addSouthVertex(pose, buffer, 16, 1, lightCoords);
                addSouthVertex(pose, buffer, 0, 1, lightCoords);
            }

            if ((renderFaces & (1 << Direction.NORTH.ordinal())) != 0) {
                addNorthVertex(pose, buffer, 0, 0, lightCoords);
                addNorthVertex(pose, buffer, 0, 1, lightCoords);
                addNorthVertex(pose, buffer, 16, 1, lightCoords);
                addNorthVertex(pose, buffer, 16, 0, lightCoords);
            }

            if ((renderFaces & (1 << Direction.EAST.ordinal())) != 0) {
                addEastVertex(pose, buffer, 0, 0, lightCoords);
                addEastVertex(pose, buffer, 1, 0, lightCoords);
                addEastVertex(pose, buffer, 1, 16, lightCoords);
                addEastVertex(pose, buffer, 0, 16, lightCoords);
            }

            if ((renderFaces & (1 << Direction.WEST.ordinal())) != 0) {
                addWestVertex(pose, buffer, 0, 0, lightCoords);
                addWestVertex(pose, buffer, 0, 16, lightCoords);
                addWestVertex(pose, buffer, 1, 16, lightCoords);
                addWestVertex(pose, buffer, 1, 0, lightCoords);
            }
        }

        private static void addUpVertex(PoseStack.Pose pose, VertexConsumer buffer, int x, int z, int lightCoords) {
            addVertex(pose, buffer, x / 16.f, 1.f / 16.f, z / 16.f, x / 16.f, z / 16.f, Direction.UP.getUnitVec3f(), lightCoords);
        }

        private static void addDownVertex(PoseStack.Pose pose, VertexConsumer buffer, int x, int z, int lightCoords) {
            addVertex(pose, buffer, x / 16.f, 0, z / 16.f, x / 16.f, z / 16.f, Direction.DOWN.getUnitVec3f(), lightCoords);
        }

        private static void addSouthVertex(PoseStack.Pose pose, VertexConsumer buffer, int x, int y, int lightCoords) {
            addVertex(pose, buffer, x / 16.f, y / 16.f, 1.f, x / 16.f, y / 16.f, Direction.SOUTH.getUnitVec3f(), lightCoords);
        }

        private static void addNorthVertex(PoseStack.Pose pose, VertexConsumer buffer, int x, int y, int lightCoords) {
            addVertex(pose, buffer, x / 16.f, y / 16.f, 0, x / 16.f, y / 16.f, Direction.NORTH.getUnitVec3f(), lightCoords);
        }

        private static void addEastVertex(PoseStack.Pose pose, VertexConsumer buffer, int y, int z, int lightCoords) {
            addVertex(pose, buffer, 1.f, y / 16.f, z / 16.f, y / 16.f, z / 16.f, Direction.EAST.getUnitVec3f(), lightCoords);
        }

        private static void addWestVertex(PoseStack.Pose pose, VertexConsumer buffer, int y, int z, int lightCoords) {
            addVertex(pose, buffer, 0, y / 16.f, z / 16.f, y / 16.f, z / 16.f, Direction.WEST.getUnitVec3f(), lightCoords);
        }

        private static void addVertex(PoseStack.Pose pose, VertexConsumer buffer, float x, float y, float z, float u, float v, Vector3fc normal, int lightCoords) {
            buffer.addVertex(pose, x, y, z)
                    .setUv(u, v)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(lightCoords)
                    .setNormal(pose, normal)
                    .setColor(0xFFFFFFFF);// Put setColor at last because this method will return the delegate object instead of the wrapper
        }
    }
}