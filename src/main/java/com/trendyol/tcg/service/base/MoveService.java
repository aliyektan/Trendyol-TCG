package com.trendyol.tcg.service.base;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Move;
import com.trendyol.tcg.model.Player;

public interface MoveService {

    Move makeMove(Player player, Card card);

    boolean canMakeMoveWithHand(Player player);

}
