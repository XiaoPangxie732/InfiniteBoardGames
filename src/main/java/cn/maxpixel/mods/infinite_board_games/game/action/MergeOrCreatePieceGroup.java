package cn.maxpixel.mods.infinite_board_games.game.action;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;

import java.util.List;
import java.util.function.Function;

public record MergeOrCreatePieceGroup(
        String id,
        List<MergeDirection> directions
) implements Action {
    public static final String TYPE = "merge_or_create_piece_group";
    public static final MapCodec<MergeOrCreatePieceGroup> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(MergeOrCreatePieceGroup::id),
            Codec.either(MergeDirection.CODEC.listOf(), MergeDirection.CODEC)
                    .xmap(e -> e.map(Function.identity(), List::of), Either::left)
                    .fieldOf("directions").forGetter(MergeOrCreatePieceGroup::directions)
    ).apply(instance, MergeOrCreatePieceGroup::new));

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends Action> codec() {
        return MAP_CODEC;
    }
}