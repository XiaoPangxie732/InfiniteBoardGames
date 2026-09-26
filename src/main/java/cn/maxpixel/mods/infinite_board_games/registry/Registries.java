package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.commands.ModCommands;
import cn.maxpixel.mods.infinite_board_games.game.condition.Conditions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = InfiniteBoardGames.MODID)
public class Registries {
    public static void register(IEventBus modEventBus) {
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITY_TYPES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        DataAttachmentRegistry.ATTACHMENT_TYPES.register(modEventBus);
        MenuTypeRegistry.MENU_TYPES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
//        CreativeTabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event);
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        CreativeTabRegistry.addCreative(event);
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        GameRegistry.registerDatapackRegistries(event);
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
        Conditions.bootstrap();
    }
}