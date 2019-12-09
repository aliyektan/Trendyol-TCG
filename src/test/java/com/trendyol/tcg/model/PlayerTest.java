package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {


    private Player object;

    @Before
    public void setUp() throws Exception {
        object = new Player("Player 1");
    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_equals() {
        Player objectToCompare = createObject();
        Player nullPlayer = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullPlayer);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {
        assertNotNull(object.hashCode());
    }

    private Player createObject() {
        object = new Player("Player 1");
        return object;
    }
}