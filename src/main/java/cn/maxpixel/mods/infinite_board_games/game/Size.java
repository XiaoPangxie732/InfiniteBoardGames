package cn.maxpixel.mods.infinite_board_games.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.ComponentSerialization;

public record Size(// TODO: support more complex size definitions
        int minimumWidth,
        int minimumHeight,
        int maximumWidth,
        int maximumHeight
) {
    public static final Codec<Size> CODEC = Codec.withAlternative(RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(1, 128).fieldOf("minimumWidth").forGetter(Size::minimumWidth),
            Codec.intRange(1, 128).fieldOf("minimumHeight").forGetter(Size::minimumHeight),
            Codec.intRange(1, 128).fieldOf("maximumWidth").forGetter(Size::maximumWidth),
            Codec.intRange(1, 128).fieldOf("maximumHeight").forGetter(Size::maximumHeight)
    ).apply(instance, Size::new)), Codec.STRING.xmap(Size::parseFromString, Size::generateString));

    public static Size parseFromString(String s) {
        int dash = s.indexOf('-');
        if (dash == -1) {
            String[] sa = s.split("x");
            if (sa.length != 2) throw new IllegalArgumentException("Invalid size format");
            int width = Integer.parseInt(sa[0]);
            int height = Integer.parseInt(sa[1]);
            return new Size(width, height, width, height);
        } else {
            String[] sa = s.split("-");
            if (sa.length != 2) throw new IllegalArgumentException("Invalid size format");
            String[] mins = sa[0].split("x");
            if (mins.length != 2) throw new IllegalArgumentException("Invalid size format");
            String[] maxes = sa[1].split("x");
            if (maxes.length != 2) throw new IllegalArgumentException("Invalid size format");
            return new Size(
                    Integer.parseInt(mins[0]),
                    Integer.parseInt(mins[1]),
                    Integer.parseInt(maxes[0]),
                    Integer.parseInt(maxes[1])
            );
        }
    }

    public String generateString() {
        return "" + minimumWidth + 'x' + minimumHeight + '-' + maximumWidth + 'x' + maximumHeight;
    }
}