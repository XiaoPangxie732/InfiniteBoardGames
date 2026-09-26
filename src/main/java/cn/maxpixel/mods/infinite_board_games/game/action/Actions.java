package cn.maxpixel.mods.infinite_board_games.game.action;

import cn.maxpixel.mods.infinite_board_games.game.action.turn.PlacePiece;
import cn.maxpixel.mods.infinite_board_games.game.action.turn.TurnAction;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class Actions {
    public static final ExtraCodecs.LateBoundIdMapper<String, MapCodec<? extends Action>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<Action> CODEC = ID_MAPPER.codec(Codec.STRING).dispatch(Action::codec, Function.identity());

    static {
        ID_MAPPER.put(MergeOrCreatePieceGroup.TYPE, MergeOrCreatePieceGroup.MAP_CODEC);
        ID_MAPPER.put(SetTurn.TYPE, SetTurn.MAP_CODEC);
        ID_MAPPER.put(WinCheck.TYPE, WinCheck.MAP_CODEC);
    }
}