package com.trendyol.tcg.service.base;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Player;

import java.util.List;

public interface CardService {

    List<Card> pickNRandomCardsByManaCost(Integer manaCost, Integer n);

    List<Card> pickRandomCardsForFirstTurn(Player player);

    Card pickRandomCardForTurn(Player player);

}
