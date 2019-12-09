package com.trendyol.tcg.service;

import com.trendyol.tcg.model.Card;
import com.trendyol.tcg.model.Deck;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class DeckServiceImplTest {

    @InjectMocks
    DeckServiceImpl deckService;

    @Mock
    CardServiceImpl cardService;

    @Before
    public void setUp() throws Exception {
        deckService = DeckServiceImpl.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void it_should_load_deck() {
        // Given
        Card card = new Card("Card 1", 0, 0);
        Card card2 = new Card("Card 2", 1, 1);
        Card card3 = new Card("Card 3", 2, 2);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        Deck deck = new Deck(cards);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(card);
        List<Card> cards2 = new ArrayList<>();
        cards2.add(card2);
        List<Card> cards3 = new ArrayList<>();
        cards3.add(card3);

        // When
        when(cardService.pickNRandomCardsByManaCost(0, 2)).thenReturn(cards1);
        when(cardService.pickNRandomCardsByManaCost(1, 2)).thenReturn(cards2);
        when(cardService.pickNRandomCardsByManaCost(2, 3)).thenReturn(cards3);
        when(cardService.pickNRandomCardsByManaCost(3, 4)).thenReturn(new ArrayList<>());
        when(cardService.pickNRandomCardsByManaCost(4, 3)).thenReturn(new ArrayList<>());
        when(cardService.pickNRandomCardsByManaCost(5, 2)).thenReturn(new ArrayList<>());
        when(cardService.pickNRandomCardsByManaCost(6, 2)).thenReturn(new ArrayList<>());
        when(cardService.pickNRandomCardsByManaCost(7, 1)).thenReturn(new ArrayList<>());
        when(cardService.pickNRandomCardsByManaCost(8, 1)).thenReturn(new ArrayList<>());

        // then
        assertNotNull(deckService.loadDeck());
        assertEquals(deck, deckService.loadDeck());


        // When
        when(cardService.pickNRandomCardsByManaCost(0, 2)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(1, 2)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(2, 3)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(3, 4)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(4, 3)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(5, 2)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(6, 2)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(7, 1)).thenCallRealMethod();
        when(cardService.pickNRandomCardsByManaCost(8, 1)).thenCallRealMethod();

        // then
        assertNotNull(deckService.loadDeck());
        assertEquals(20, deckService.loadDeck().getCards().size());

    }

    @Test
    public void it_should_get_instance() {
        assertNotNull(DeckServiceImpl.getInstance());
        assertEquals(deckService, DeckServiceImpl.getInstance());
    }


}