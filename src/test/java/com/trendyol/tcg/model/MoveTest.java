package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    private Move object;

    @Before
    public void setUp() throws Exception {
        Card card = new Card("Card 1", 1, 1);
        Player player = new Player("Player 1");

        object = new Move(player, card);
    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_equals() {
        Move objectToCompare = createObject();
        Move nullMove = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullMove);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {
        assertNotNull(object.hashCode());
    }

    private Move createObject() {
        Card card = new Card("Card 1", 1, 1);
        Player player = new Player("Player 1");

        return new Move(player, card);
    }

}