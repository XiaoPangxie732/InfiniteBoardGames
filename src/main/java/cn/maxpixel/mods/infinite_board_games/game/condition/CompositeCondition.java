package cn.maxpixel.mods.infinite_board_games.game.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record CompositeCondition(
        List<Condition> anyOf,
        List<Condition> allOf
) implements Condition {
    public static final String TYPE = "composite";
    public static final MapCodec<CompositeCondition> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Conditions.CODEC.listOf().optionalFieldOf("any_of", List.of()).forGetter(CompositeCondition::anyOf),
            Conditions.CODEC.listOf().optionalFieldOf("all_of", List.of()).forGetter(CompositeCondition::allOf)
    ).apply(instance, CompositeCondition::new));
    public static final Codec<CompositeCondition> CODEC = MAP_CODEC.codec();

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public MapCodec<? extends Condition> codec() {
        return MAP_CODEC;
    }
}