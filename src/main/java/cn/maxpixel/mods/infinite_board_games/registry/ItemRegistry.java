package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.item.GameCardItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfiniteBoardGames.MODID);

    public static final DeferredItem<BlockItem> GAME_TABLE = ITEMS.registerSimpleBlockItem(BlockRegistry.GAME_TABLE);

    public static final DeferredItem<Item> GAME_CARD = ITEMS.registerItem("game_card", GameCardItem::new);
    public static final DeferredItem<Item> GAME = ITEMS.registerItem("game", GameCardItem::new);
}
