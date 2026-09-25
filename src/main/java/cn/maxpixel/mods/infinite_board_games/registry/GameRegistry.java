package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.game.Game;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class GameRegistry {
    public static final ResourceKey<Registry<Game>> GAME_REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(InfiniteBoardGames.MODID, "game"));

    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(GAME_REGISTRY_KEY, Game.CODEC);
    }
}