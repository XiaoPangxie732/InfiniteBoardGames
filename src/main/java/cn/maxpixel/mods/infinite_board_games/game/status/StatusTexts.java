package cn.maxpixel.mods.infinite_board_games.game.status;

import cn.maxpixel.mods.infinite_board_games.game.action.turn.PlacePiece;
import cn.maxpixel.mods.infinite_board_games.game.action.turn.TurnAction;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class StatusTexts {
    public static final ExtraCodecs.LateBoundIdMapper<String, MapCodec<? extends StatusText>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<StatusText> CODEC = ID_MAPPER.codec(Codec.STRING).dispatch(StatusText::codec, Function.identity());

    static {
        ID_MAPPER.put(TurnText.TYPE, TurnText.MAP_CODEC);
    }
}