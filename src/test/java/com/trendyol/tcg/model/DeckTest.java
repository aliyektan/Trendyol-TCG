package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck object;

    @Before
    public void setUp() throws Exception {
        Card card = new Card("Card 1", 1, 1);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        object = new Deck(cards);
    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_to_equals() {
        Deck objectToCompare = createObject();
        Deck nullDeck = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullDeck);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {
        assertNotNull(object.hashCode());
    }

    private Deck createObject() {
        Card card = new Card("Card 1", 1, 1);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        return new Deck(cards);
    }
}