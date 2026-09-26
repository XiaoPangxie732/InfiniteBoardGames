package cn.maxpixel.mods.infinite_board_games.game.action;

import com.mojang.serialization.MapCodec;

public interface Action {
    String type();

    MapCodec<? extends Action> codec();
}