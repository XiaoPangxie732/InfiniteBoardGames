package cn.maxpixel.mods.infinite_board_games.game.action.turn;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class TurnActions {
    public static final ExtraCodecs.LateBoundIdMapper<String, MapCodec<? extends TurnAction>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<TurnAction> CODEC = ID_MAPPER.codec(Codec.STRING).dispatch(TurnAction::codec, Function.identity());

    static {
        ID_MAPPER.put(PlacePiece.TYPE, PlacePiece.MAP_CODEC);
    }
}
