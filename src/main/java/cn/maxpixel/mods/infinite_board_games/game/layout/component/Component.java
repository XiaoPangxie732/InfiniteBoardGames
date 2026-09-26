package cn.maxpixel.mods.infinite_board_games.game.layout.component;

import com.mojang.serialization.MapCodec;

public interface Component {
    String type();

    MapCodec<? extends Component> codec();
}