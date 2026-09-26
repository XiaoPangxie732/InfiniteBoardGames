package cn.maxpixel.mods.infinite_board_games.game.condition;

import cn.maxpixel.mods.infinite_board_games.game.action.Action;
import cn.maxpixel.mods.infinite_board_games.game.action.MergeOrCreatePieceGroup;
import cn.maxpixel.mods.infinite_board_games.game.action.SetTurn;
import cn.maxpixel.mods.infinite_board_games.game.action.WinCheck;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class Conditions {
    public static final ExtraCodecs.LateBoundIdMapper<String, MapCodec<? extends Condition>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<Condition> CODEC = ID_MAPPER.codec(Codec.STRING).dispatch(Condition::codec, Function.identity());

    public static void bootstrap() {
        ID_MAPPER.put(CompositeCondition.TYPE, CompositeCondition.MAP_CODEC);
        ID_MAPPER.put(GreaterEqual.TYPE, GreaterEqual.MAP_CODEC);
    }
}