package cn.maxpixel.mods.infinite_board_games;

import cn.maxpixel.mods.infinite_board_games.registry.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(InfiniteBoardGames.MODID)
public class InfiniteBoardGames {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "infinite_board_games";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public InfiniteBoardGames(IEventBus modEventBus, ModContainer modContainer) {
        Registries.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}
