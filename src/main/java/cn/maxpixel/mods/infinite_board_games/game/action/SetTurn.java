package cn.maxpixel.mods.infinite_board_games.game.action;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

public record SetTurn(
        Either<String, Integer> value
) implements Action {
    public static final String TYPE = "set_turn";
    public static final MapCodec<SetTurn> MAP_CODEC = Codec.either(Codec.STRING, Codec.INT).fieldOf("value")
            .xmap(SetTurn::new, SetTurn::value);

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends Action> codec() {
        return MAP_CODEC;
    }
}