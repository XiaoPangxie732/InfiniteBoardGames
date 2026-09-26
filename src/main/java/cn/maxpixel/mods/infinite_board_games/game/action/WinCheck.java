package cn.maxpixel.mods.infinite_board_games.game.action;

import cn.maxpixel.mods.infinite_board_games.game.condition.CompositeCondition;
import cn.maxpixel.mods.infinite_board_games.game.condition.Conditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record WinCheck(
        CompositeCondition conditions
) implements Action {
    public static final String TYPE = "win_check";
    public static final MapCodec<WinCheck> MAP_CODEC = CompositeCondition.CODEC.fieldOf("conditions")
            .xmap(WinCheck::new, WinCheck::conditions);
    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends Action> codec() {
        return MAP_CODEC;
    }
}