package cn.maxpixel.mods.infinite_board_games;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = InfiniteBoardGamesMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = InfiniteBoardGamesMod.MODID, value = Dist.CLIENT)
public class InfiniteBoardGamesModClient {
    public InfiniteBoardGamesModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
//        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

//    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        InfiniteBoardGamesMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        InfiniteBoardGamesMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
