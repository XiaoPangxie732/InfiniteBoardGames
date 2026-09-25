package cn.maxpixel.mods.infinite_board_games.datagen.lang;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class CustomLanguageProvider extends LanguageProvider {
    public CustomLanguageProvider(PackOutput output, String locale) {
        super(output, InfiniteBoardGames.MODID, locale);
    }
}