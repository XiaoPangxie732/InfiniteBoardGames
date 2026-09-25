package cn.maxpixel.mods.infinite_board_games.registry;

import cn.maxpixel.mods.infinite_board_games.InfiniteBoardGames;
import cn.maxpixel.mods.infinite_board_games.attachment.PlayerMatchStatus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class DataAttachmentRegistry {
    static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, InfiniteBoardGames.MODID);

    public static final Supplier<AttachmentType<PlayerMatchStatus>> PLAYER_MATCH_STATUS = ATTACHMENT_TYPES.register(
            "player_match_status", () -> AttachmentType.builder(PlayerMatchStatus::new)
//                    .sync(new HurriednessValueSyncHandler())
                    .build()
    );
}