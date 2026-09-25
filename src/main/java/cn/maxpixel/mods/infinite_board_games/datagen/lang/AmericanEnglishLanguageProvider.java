package cn.maxpixel.mods.infinite_board_games.datagen.lang;

import cn.maxpixel.mods.infinite_board_games.Config;
import cn.maxpixel.mods.infinite_board_games.block.entity.GameTableBlockEntity;
import cn.maxpixel.mods.infinite_board_games.commands.InfiniteBoardGamesCommands;
import cn.maxpixel.mods.infinite_board_games.registry.BlockRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.ItemRegistry;
import cn.maxpixel.mods.infinite_board_games.util.I18nKey;
import net.minecraft.data.PackOutput;

public class AmericanEnglishLanguageProvider extends CustomLanguageProvider {
    public AmericanEnglishLanguageProvider(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(I18nKey.KEY_CONFIG_TITLE, "Infinite Board Games Configs");
        add(I18nKey.KEY_CONFIG_SECTION_COMMON, "Infinite Board Games Configs");
        add(I18nKey.KEY_CONFIG_SECTION_COMMON_TITLE, "Infinite Board Games Configs");
        add(Config.KEY_SHOW_DEBUG_INFO, "Show debug info");
        add(Config.KEY_SHOW_DEBUG_INFO_TOOLTIP, "Whether to show debug info");

        addBlock(BlockRegistry.GAME_TABLE, "Game Table");
        add(GameTableBlockEntity.DEFAULT_NAME_KEY, "Game Table");

        addItem(ItemRegistry.GAME, "Game");
        addItem(ItemRegistry.GAME_CARD, "Game Card");

        add(InfiniteBoardGamesCommands.KEY_POS_NOT_GAME_TABLE, "The block at the given position is not a game table");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_SET_BASE_TEXTURE, "Successfully changed the base texture at %s, %s, %s to \"%s\"");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_SET_BASE_TEXTURE_SUFFIX, "Successfully changed the base texture suffix at %s, %s, %s to \"%s\"");
        add(InfiniteBoardGamesCommands.KEY_SUCCESS_CLEAR_BASE_TEXTURE_SUFFIX, "Successfully cleared the base texture suffix at %s, %s, %s");
    }
}
