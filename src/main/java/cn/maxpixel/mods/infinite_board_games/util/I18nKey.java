package cn.maxpixel.mods.infinite_board_games.util;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;

public class I18nKey {
    public static final String KEY_CONFIG_TITLE = I18nKey.config("title");
    public static final String KEY_CONFIG_SECTION_COMMON = I18nKey.config("section.infinite_board_games.common.toml");
    public static final String KEY_CONFIG_SECTION_COMMON_TITLE = I18nKey.config("section.infinite_board_games.common.toml.title");

    public static String commands(String cmd, String key) {
        return "commands." + InfiniteBoardGames.MODID + '.' + cmd + '.' + key;
    }

    public static String config(String key) {
        return InfiniteBoardGames.MODID + ".configuration." + key;
    }

    public static String container(String key) {
        return InfiniteBoardGames.MODID + ".container." + key;
    }

    public static String item(String key) {
        return InfiniteBoardGames.MODID + ".item." + key;
    }
}