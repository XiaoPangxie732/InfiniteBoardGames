package cn.maxpixel.mods.infinite_board_games.game.condition;

import cn.maxpixel.mods.infinite_board_games.game.piece.PieceGroup;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record GreaterEqual(
        Optional<String> pieceGroup,
        int value
) implements Condition {
    public static final String TYPE = "greater_equal";
    public static final MapCodec<GreaterEqual> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("piece_group").forGetter(GreaterEqual::pieceGroup),
            Codec.INT.fieldOf("value").forGetter(GreaterEqual::value)
    ).apply(instance, GreaterEqual::new));
    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends Condition> codec() {
        return MAP_CODEC;
    }
}