package com.trendyol.tcg.service;

import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Deck;
import com.trendyol.tcg.service.base.CardService;
import com.trendyol.tcg.service.base.DeckService;

import java.util.ArrayList;
import java.util.List;

public class DeckServiceImpl implements DeckService {

    private static DeckServiceImpl deckServiceImpl;
    private CardService cardService = CardServiceImpl.getInstance();
    private TCGConstants tcgConstants = TCGConstants.getInstance();

    /**
     * Singleton Pattern
     */
    private DeckServiceImpl() {
    }

    public static DeckServiceImpl getInstance() {
        if (deckServiceImpl == null) {
            deckServiceImpl = new DeckServiceImpl();
        }
        return deckServiceImpl;
    }

    public Deck loadDeck() {

        List<Integer> manaCosts = tcgConstants.getManaCosts();
        List<Integer> cardCountsByManaCost = tcgConstants.getCardCountsByManaCost();

        List<Card> finalCards = new ArrayList<>();

        for (Integer manaCost : manaCosts) {
            finalCards.addAll(cardService.pickNRandomCardsByManaCost(manaCost, cardCountsByManaCost.get(manaCost)));
        }

        return new Deck(finalCards);

    }

}
