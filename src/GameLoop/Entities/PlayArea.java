package GameLoop.Entities;

import GameLoop.Graphics.Screen;

public class PlayArea {

    private Deck deck;
    private Deck table;

    public PlayArea() {
        deck = new Deck();
        table = new Deck();
        table.loadDeck();
    }
    
    public void render(Screen screen){
        table.render(screen);
    }
}
