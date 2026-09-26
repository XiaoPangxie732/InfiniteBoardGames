package cn.maxpixel.mods.infinite_board_games.game.layout;

import cn.maxpixel.mods.infinite_board_games.game.layout.component.Component;
import cn.maxpixel.mods.infinite_board_games.game.layout.component.Components;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record Layout(
        int width,
        int height,
        List<Component> components
) {
    public static final Codec<Layout> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("width").forGetter(Layout::width),
            Codec.INT.fieldOf("height").forGetter(Layout::height),
            Components.CODEC.listOf().fieldOf("components").forGetter(Layout::components)
    ).apply(instance, Layout::new));
}