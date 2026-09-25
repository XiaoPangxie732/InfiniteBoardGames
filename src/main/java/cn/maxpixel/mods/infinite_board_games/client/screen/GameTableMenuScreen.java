package cn.maxpixel.mods.infinite_board_games.client.screen;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.menu.GameTableMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class GameTableMenuScreen extends AbstractContainerScreen<GameTableMenu> {
    private static final Identifier GAME_TABLE_LOCATION = InfiniteBoardGames.rl("textures/gui/container/game_table.png");

    public GameTableMenuScreen(GameTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 133);
        inventoryLabelY = imageHeight - 94;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = (width - imageWidth) / 2;
        int yo = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GAME_TABLE_LOCATION, xo, yo, 0.f, 0.f,
                imageWidth, imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
    }
}