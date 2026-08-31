package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGamesMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

//@EventBusSubscriber(modid = InfiniteBoardGamesMod.MODID)
public class Registries {
    public static void registerDeferred(IEventBus modEventBus) {
        // Register the Deferred Register to the mod event bus so blocks get registered
        BlockRegistry.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ItemRegistry.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
//        CreativeTabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        CreativeTabRegistry.addCreative(event);
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        GameRegistry.registerDatapackRegistries(event);
    }
}