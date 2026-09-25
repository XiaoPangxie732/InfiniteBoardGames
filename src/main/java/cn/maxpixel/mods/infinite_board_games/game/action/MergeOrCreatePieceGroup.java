package cn.maxpixel.mods.infinite_board_games.game.action;

import net.minecraft.core.Direction;

import java.util.List;

public class MergeOrCreatePieceGroup implements Action {
    private String id;
    private List<MergeDirection> directions;
}