package com.trendyol.tcg.service;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.CardService;
import com.trendyol.tcg.util.JSONUtil;
import com.trendyol.tcg.util.PrinterUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CardServiceImpl implements CardService {

    private static CardServiceImpl cardServiceImpl;

    private TCGConstants tcgConstants = TCGConstants.getInstance();

    /**
     * Singleton Pattern
     */
    private CardServiceImpl() {
    }

    public static CardServiceImpl getInstance() {
        if (cardServiceImpl == null) {
            cardServiceImpl = new CardServiceImpl();
        }
        return cardServiceImpl;
    }

    public List<Card> pickNRandomCardsByManaCost(Integer manaCost, Integer n) {

        List<Card> cards = null;
        try {
            cards = JSONUtil.readAllCards().get(manaCost.toString());
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage(), e);
        }

        Collections.shuffle(cards);

        List<Card> randoms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randoms.add(cards.get(i));
        }
        return randoms;

    }

    public List<Card> pickRandomCardsForFirstTurn(Player player) {

        Integer randomCardCount = tcgConstants.getRandomCardCountFirstTurn();
        List<Card> cards = player.getDeck().getCards();

        Collections.shuffle(cards);

        List<Card> randoms = new ArrayList<>();

        for (int i = 0; i < randomCardCount; i++) {
            randoms.add(cards.get(i));
        }

        cards.removeAll(randoms);

        return randoms;
    }

    public Card pickRandomCardForTurn(Player player) {

        List<Card> cards = player.getDeck().getCards();

        if (cards.size() == 0)
            return null;

        Collections.shuffle(cards);

        PrinterUtil.printDrawCardMessage(player);

        return cards.remove(0);
    }

}
