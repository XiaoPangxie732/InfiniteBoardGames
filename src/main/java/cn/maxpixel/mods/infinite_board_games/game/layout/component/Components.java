package cn.maxpixel.mods.infinite_board_games.game.layout.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class Components {
    public static final ExtraCodecs.LateBoundIdMapper<String, MapCodec<? extends Component>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<Component> CODEC = ID_MAPPER.codec(Codec.STRING).dispatch(Component::codec, Function.identity());

    static {
        ID_MAPPER.put(GridComponent.TYPE, GridComponent.MAP_CODEC);
    }
}