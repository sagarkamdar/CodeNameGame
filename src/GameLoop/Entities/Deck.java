package GameLoop.Entities;

import GameLoop.Graphics.Screen;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deck {

    private Vector<Card> cards;
    public int size;

    public Deck() {
        cards = new Vector<>();
        size = 0;
    }

    public void loadDeck() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("data/words.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                Card card = new Card(line, Colour.Yellow);
                cards.add(card);
            }
            in.close();
            size = cards.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void discardInThis(Card card) {
        cards.add(card);
        ++size;
    }

    public void put(Card card) {
        cards.add(0, card);
        ++size;
    }

    public Card draw() {
        assert (cards.isEmpty());
        --size;
        return cards.remove(0);
    }

    public void shuffle() {
        Random random = new Random();
        random.setSeed(System.nanoTime());
        for(int i = 0; i < size; ++i) {
            int swap = random.nextInt(size);
            Collections.swap(cards, i, swap);
        }
    }

    public void render(Screen screen) {
        cards.forEach((card) -> {
            card.render(screen);
        });
    }
}
