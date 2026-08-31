package cn.maxpixel.mods.infinite_board_games.game;

import cn.maxpixel.mods.infinite_board_games.layout.Layout;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.chat.Component;

public class Game {
    public static final Codec<Game> CODEC = MapCodec.unitCodec(Game::new);

    private String id;
    private Component displayName;
    private Component description;
    private Size size;
    private Layout layout;

}