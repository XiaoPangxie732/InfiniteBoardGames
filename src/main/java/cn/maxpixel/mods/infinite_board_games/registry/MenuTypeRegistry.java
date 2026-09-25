package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.block.GameTableBlock;
import cn.maxpixel.mods.infinite_board_games.menu.GameTableMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuTypeRegistry {
    static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, InfiniteBoardGames.MODID);

    public static final Supplier<MenuType<GameTableMenu>> GAME_TABLE = MENU_TYPES.register(GameTableBlock.ID, () ->
            new MenuType<>(GameTableMenu::new, FeatureFlags.DEFAULT_FLAGS));
}