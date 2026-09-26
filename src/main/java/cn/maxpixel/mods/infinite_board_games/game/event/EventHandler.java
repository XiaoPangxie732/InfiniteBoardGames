package cn.maxpixel.mods.infinite_board_games.game.event;

import cn.maxpixel.mods.infinite_board_games.game.action.Action;
import cn.maxpixel.mods.infinite_board_games.game.action.Actions;
import cn.maxpixel.mods.infinite_board_games.game.action.MergeDirection;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.function.Function;

public record EventHandler(
        String type,
        List<Action> actions
) {
    public static final Codec<EventHandler> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(EventHandler::type),
            Codec.either(Actions.CODEC.listOf(), Actions.CODEC)
                    .xmap(e -> e.map(Function.identity(), List::of), Either::left)
                    .fieldOf("actions").forGetter(EventHandler::actions)
    ).apply(instance, EventHandler::new));
}