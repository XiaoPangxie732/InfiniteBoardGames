package cn.maxpixel.mods.infinite_board_games.datagen.lang;

import cn.maxpixel.mods.infinite_board_games.Config;
import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import cn.maxpixel.mods.infinite_board_games.commands.InfiniteBoardGamesCommands;
import cn.maxpixel.mods.infinite_board_games.registry.BlockRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import cn.maxpixel.mods.infinite_board_games.util.I18nKey;
import net.minecraft.data.PackOutput;

public class SimplifiedChineseLanguageProvider extends CustomLanguageProvider {
    public SimplifiedChineseLanguageProvider(PackOutput output) {
        super(output, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(I18nKey.KEY_CONFIG_TITLE, "无穷桌游 配置");
        add(I18nKey.KEY_CONFIG_SECTION_COMMON, "无穷桌游 配置");
        add(I18nKey.KEY_CONFIG_SECTION_COMMON_TITLE, "无穷桌游 配置");
        add(Config.KEY_SHOW_DEBUG_INFO, "显示调试信息");
        add(Config.KEY_SHOW_DEBUG_INFO_TOOLTIP, "是否显示调试信息");

        addBlock(BlockRegistry.GAME_TABLE, "游戏桌");
        add(GameTableBlockEntity.DEFAULT_NAME_KEY, "游戏桌");

        addItem(ItemRegistry.GAME, "游戏");
        addItem(ItemRegistry.GAME_CARD, "游戏卡");

        add(InfiniteBoardGamesCommands.KEY_POS_NOT_GAME_TABLE, "给定位置的方块不是游戏桌");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_SET_BASE_TEXTURE, "成功将位于%s, %s, %s的基座纹理更改为\"%s\"");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_SET_BASE_TEXTURE_SUFFIX, "成功将位于%s, %s, %s的基座纹理后缀更改为\"%s\"");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_CLEAR_BASE_TEXTURE_SUFFIX, "成功将位于%s, %s, %s的基座纹理后缀清除");
    }
}
