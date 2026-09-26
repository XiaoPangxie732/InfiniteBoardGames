package cn.maxpixel.mods.infinite_board_games.game.status;

import com.mojang.serialization.MapCodec;

public interface StatusText {
    String type();

    MapCodec<? extends StatusText> codec();
}