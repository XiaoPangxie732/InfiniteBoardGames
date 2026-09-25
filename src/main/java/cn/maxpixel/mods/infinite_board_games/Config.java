package cn.maxpixel.mods.infinite_board_games;

import cn.maxpixel.mods.infinite_board_games.util.I18nKey;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final String KEY_SHOW_DEBUG_INFO = I18nKey.config("showDebugInfo");
    public static final String KEY_SHOW_DEBUG_INFO_TOOLTIP = I18nKey.config("showDebugInfo.tooltip");
    public static final ModConfigSpec.BooleanValue SHOW_DEBUG_INFO = BUILDER
            .comment("Whether to show debug info")
            .define("showDebugInfo", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
