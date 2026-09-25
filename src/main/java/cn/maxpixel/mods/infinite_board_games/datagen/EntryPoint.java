package cn.maxpixel.mods.infinite_board_games.datagen;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.datagen.lang.AmericanEnglishLanguageProvider;
import cn.maxpixel.mods.infinite_board_games.datagen.lang.SimplifiedChineseLanguageProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = InfiniteBoardGames.MODID, value = Dist.CLIENT)
public class EntryPoint {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(AmericanEnglishLanguageProvider::new);
        event.createProvider(SimplifiedChineseLanguageProvider::new);

        event.createProvider(Models::new);
    }
}