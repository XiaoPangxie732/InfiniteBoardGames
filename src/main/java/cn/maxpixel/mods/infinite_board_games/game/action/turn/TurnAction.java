package cn.maxpixel.mods.infinite_board_games.game.action.turn;

import com.mojang.serialization.MapCodec;

public interface TurnAction {
    String type();

    MapCodec<? extends TurnAction> codec();
}