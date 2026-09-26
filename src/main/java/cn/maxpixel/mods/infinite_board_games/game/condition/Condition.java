package cn.maxpixel.mods.infinite_board_games.game.condition;

import com.mojang.serialization.MapCodec;

public interface Condition {
    String type();

    MapCodec<? extends Condition> codec();
}