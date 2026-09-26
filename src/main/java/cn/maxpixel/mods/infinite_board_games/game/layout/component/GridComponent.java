package cn.maxpixel.mods.infinite_board_games.game.layout.component;

import cn.maxpixel.mods.infinite_board_games.game.layout.LinePosition;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record GridComponent(
        CommonComponentData common,
        LinePosition linePosition,
        int lineWidth,
        List<String> tags
) implements Component {
    public static final String TYPE = "grid";
    public static final MapCodec<GridComponent> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            CommonComponentData.MAP_CODEC.forGetter(GridComponent::common),
            LinePosition.CODEC.optionalFieldOf("line_position", LinePosition.NONE).forGetter(GridComponent::linePosition),
            Codec.INT.optionalFieldOf("line_width", 1).forGetter(GridComponent::lineWidth),
            Codec.STRING.listOf().optionalFieldOf("tags", List.of()).forGetter(GridComponent::tags)
    ).apply(instance, GridComponent::new));

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<GridComponent> codec() {
        return MAP_CODEC;
    }
}