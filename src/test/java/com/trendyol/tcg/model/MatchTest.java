package com.trendyol.tcg.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class MatchTest {

    private Match object;

    @Before
    public void setUp() throws Exception {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        Card card1 = new Card("card1", 1, 1);
        Card card2 = new Card("card2", 1, 1);

        Move move1 = new Move(player1, card1);
        Move move2 = new Move(player2, card2);

        Stack<Move> moves = new Stack<>();
        moves.push(move1);
        moves.push(move2);

        object = new Match();
        object.setPlayer1(player1);
        object.setPlayer2(player1);
        object.setMoves(moves);
    }

    @Test
    public void it_should_to_string() {
        assertNotNull(object.toString());
    }

    @Test
    public void it_should_equals() {
        Match objectToCompare = createObject();
        Match nullMatch = null;
        Object nullObject = null;
        assertTrue(objectToCompare.equals(object) && object.equals(objectToCompare));
        assertNotEquals(object, nullMatch);
        assertNotEquals(object, nullObject);
    }

    @Test
    public void it_should_hash_code() {

        assertNotNull(object.hashCode());

    }

    @Test
    public void it_should_add_move() {

        Player player = new Player("player 1");
        Player player2 = new Player("player 2");

        Card card = new Card("Card 1", 0, 0);

        Move move = new Move(player, card);

        Match match = new Match();
        match.setPlayer1(player);
        match.setPlayer2(player2);

        match.addMove(move);

        assertNotNull(match.getMoves());
        assertEquals(1, match.getMoves().size());
        assertEquals(move, match.getMoves().peek());

    }

    private Match createObject() {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        Card card1 = new Card("card1", 1, 1);
        Card card2 = new Card("card2", 1, 1);

        Move move1 = new Move(player1, card1);
        Move move2 = new Move(player2, card2);

        Stack<Move> moves = new Stack<>();
        moves.push(move1);
        moves.push(move2);

        Match object = new Match();
        object.setPlayer1(player1);
        object.setPlayer2(player1);
        object.setMoves(moves);

        return object;
    }
}