package cn.maxpixel.mods.infinite_board_games.game.condition;

import java.util.List;

public class CompositeCondition {
    private List<Condition> anyOf;
    private List<Condition> allOf;
}