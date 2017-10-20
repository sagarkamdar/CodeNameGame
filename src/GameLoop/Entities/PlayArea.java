package GameLoop.Entities;

import GameLoop.Graphics.Screen;

public class PlayArea {

    private Deck deck;
    private Deck table;
    private Deck cover;

    public PlayArea() {
        deck = new Deck();
        table = new Deck();
        cover = new Deck();

        deck.loadDeck();
    }

    public void placeCard() {
        table.put(deck.draw());
    }

    public void removeCard() {
        if (table.size > 0) {
            deck.discardInThis(table.draw());
        }
    }

    public void spreadGame() {
        deck.shuffle();
        int x = 10, y = 10;
        for (int i = 0; i < 25; ++i) {
            Card card = deck.draw();
            card.x = x;
            card.y = y;
            card.colour = Colour.Blue;

            table.discardInThis(card);

            x += 105;
            if ((i + 1) % 5 == 0) {
                x = 10;
                y += 55;
            }
        }
    }

    public void discardGame() {
        while (table.size > 0) {
            deck.discardInThis(table.draw());
        }
    }

    public void render(Screen screen) {
        table.render(screen);
    }
}
