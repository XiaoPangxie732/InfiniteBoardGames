package cn.maxpixel.mods.infinite_board_games.game.layout.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record CommonComponentData(
        int width,
        int height,
        Optional<Integer> x,
        Optional<Integer> y
) {
    public static final MapCodec<CommonComponentData> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("width").forGetter(CommonComponentData::width),
            Codec.INT.fieldOf("height").forGetter(CommonComponentData::height),
            Codec.INT.optionalFieldOf("x").forGetter(CommonComponentData::x),
            Codec.INT.optionalFieldOf("y").forGetter(CommonComponentData::y)
    ).apply(instance, CommonComponentData::new));
}