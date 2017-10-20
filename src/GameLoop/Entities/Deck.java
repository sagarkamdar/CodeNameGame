package GameLoop.Entities;

import GameLoop.Graphics.Screen;
import java.util.ArrayList;
import java.util.Vector;

public class Deck {

    private Vector<Card> cards;

    public Deck() {
        cards = new Vector<>();
    }

    public void loadDeck() {
        Card card = new Card("Hello World", Colour.Blue);
        cards.add(card);
    }

    public void discard(Card card) {
        cards.add(card);
    }

    public void put(Card card) {
        cards.add(0, card);
    }

    public Card draw() {
        assert (cards.isEmpty());

        return cards.remove(0);
    }

    public void shuffle() {

    }

    public void render(Screen screen) {
        for(Card card : cards) {
            card.render(screen);
        }
    }
}
