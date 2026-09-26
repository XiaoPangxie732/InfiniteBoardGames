package cn.maxpixel.mods.infinite_board_games.game;

import cn.maxpixel.mods.infinite_board_games.game.event.EventHandler;
import cn.maxpixel.mods.infinite_board_games.game.status.StatusText;
import cn.maxpixel.mods.infinite_board_games.game.status.StatusTexts;
import cn.maxpixel.mods.infinite_board_games.game.teaming.Teaming;
import cn.maxpixel.mods.infinite_board_games.game.layout.Layout;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

import java.util.List;
import java.util.Optional;

public record Game(
        String id,
        Optional<Component> displayName,
        Optional<Component> description,
        Size size,
        Layout layout,
        Teaming teaming,
        List<StatusText> statusDisplays,
        List<EventHandler> eventHandlers
) {
    public static final Codec<Game> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(Game::id),
            ComponentSerialization.CODEC.optionalFieldOf("display_name").forGetter(Game::displayName),
            ComponentSerialization.CODEC.optionalFieldOf("description").forGetter(Game::description),
            Size.CODEC.fieldOf("size").forGetter(Game::size),
            Layout.CODEC.fieldOf("layout").forGetter(Game::layout),
            Teaming.CODEC.fieldOf("teaming").forGetter(Game::teaming),
            StatusTexts.CODEC.listOf().optionalFieldOf("status_displays", List.of()).forGetter(Game::statusDisplays),
            EventHandler.CODEC.listOf().optionalFieldOf("event_handlers", List.of()).forGetter(Game::eventHandlers)
    ).apply(instance, Game::new));
}