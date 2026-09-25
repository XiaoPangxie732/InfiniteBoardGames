package cn.maxpixel.mods.infinite_board_games.client;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.client.renderer.blockentity.GameTableBlockEntityRenderer;
import cn.maxpixel.mods.infinite_board_games.client.screen.GameTableMenuScreen;
import cn.maxpixel.mods.infinite_board_games.registry.BlockEntityRegistry;
import cn.maxpixel.mods.infinite_board_games.registry.MenuTypeRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = InfiniteBoardGames.MODID, value = Dist.CLIENT)
public class ClientRegistries {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.GAME_TABLE.get(), GameTableBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(MenuTypeRegistry.GAME_TABLE.get(), GameTableMenuScreen::new);
    }
}