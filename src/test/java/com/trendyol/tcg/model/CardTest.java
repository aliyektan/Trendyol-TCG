package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card object;

    @Before
    public void setUp() throws Exception {
        object = new Card("Card 1", 1, 1);
    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_equals() {
        Card objectToCompare = createObject();
        Card nullCard = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullCard);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {
        assertNotNull(object.hashCode());
    }

    private Card createObject() {
        return new Card("Card 1", 1, 1);
    }

}