package GameLoop.Entities;

import GameLoop.Graphics.Screen;
import java.util.Random;

public class PlayArea {

    private final Deck deck;
    private final Deck table;
    private final Deck cover;
    private int turn;

    public PlayArea() {
        deck = new Deck();
        table = new Deck();
        cover = new Deck();

        deck.loadDeck();
    }

    /**
     * Place one card from the deck on to the table
     */
    public void placeCard() {
        table.put(deck.draw());
    }

    /**
     * Remove the last card placed on the table to the bottom of deck
     */
    public void removeCard() {
        if (table.size > 0) {
            deck.discardInThis(table.draw());
        }
    }

    /**
     * Discard current game and make new game
     */
    public void newGame() {
        putCardsOnTable();
        
        assignColours();
    }

    private void putCardsOnTable() {
        while (table.size > 0) {
            deck.discardInThis(table.draw());
        }

        deck.shuffle();

        int x = 10, y = 10;                         //Table top left corner

        for (int i = 0; i < 25; ++i) {              //Take and place card on table
            Card card = deck.draw();
            card.x = x;
            card.y = y;

            table.discardInThis(card);

            x += 125;
            if ((i + 1) % 5 == 0) {
                x = 10;
                y += 55;
            }
        }
    }

    private void assignColours() {
        Random random = new Random();
        random.setSeed(System.nanoTime());

        int[] colours = {8, 8, 7, 1};

        turn = random.nextInt(2);

        if (turn == 0) {
            ++colours[0];
        } else {
            ++colours[1];
        }

        for (int i = 0; i < table.size; ++i) {
            int color = random.nextInt(4);
            if (colours[color] > 0) {
                Card card = table.draw();
                card.colour = Colour.values()[color];
                --colours[color];
                table.discardInThis(card);
            } else {
                --i;
            }
        }
        for (int i = 0; i < table.size; ++i) {
            Card card = table.draw();
            System.out.println(card.colour.toString());
            table.discardInThis(card);
        }
    }

    /**
     * Discard the current game
     */
    public void discardGame() {
        while (table.size > 0) {
            deck.discardInThis(table.draw());
        }
    }

    public void render(Screen screen) {
        table.render(screen);
    }
}
