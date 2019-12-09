package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Deck;
import com.trendyol.tcg.model.Player;
import com.trendyol.tcg.service.base.CardService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardServiceImplTest {

    CardService cardService;

    @Before
    public void setUp() throws Exception {

        cardService = CardServiceImpl.getInstance();

    }

    @Test
    public void getInstance() {
        // Then
        assertNotNull(CardServiceImpl.getInstance());
        assertEquals(cardService, CardServiceImpl.getInstance());
    }

    @Test
    public void it_should_pick_N_random_cards_by_mana_cost() {
        // Then
        assertNotNull(cardService.pickNRandomCardsByManaCost(0, 2));
        assertEquals(0, cardService.pickNRandomCardsByManaCost(0, 0).size());
        assertEquals(2, cardService.pickNRandomCardsByManaCost(0, 2).size());
    }

    @Test
    public void it_should_pick_random_cards_for_first_turn() {
        // Given
        Player player = new Player("player1");
        List<Card> cards = cardService.pickNRandomCardsByManaCost(3, 4);
        Deck deck = new Deck(cards);
        player.setDeck(deck);

        List<Card> randoms = cardService.pickRandomCardsForFirstTurn(player);

        // Then
        assertNotNull(randoms);
        assertEquals(3, randoms.size());
        assertFalse(player.getDeck().getCards().containsAll(randoms));
    }

    @Test
    public void it_should_pick_random_card_for_turn() {

        // Given
        Player player = new Player("player1");
        List<Card> cards = cardService.pickNRandomCardsByManaCost(3, 4);
        Deck deck = new Deck(cards);
        player.setDeck(deck);

        Player player2 = new Player("player2");
        List<Card> cards2 = new ArrayList<>();
        Deck deck2 = new Deck(cards2);
        player2.setDeck(deck2);

        Card random = cardService.pickRandomCardForTurn(player);
        Card random2 = cardService.pickRandomCardForTurn(player2);

        // Then
        assertNotNull(random);
        assertFalse(player.getDeck().getCards().contains(random));
        assertNull(random2);

    }
}