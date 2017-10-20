package GameLoop.Entities;

import GameLoop.Graphics.Screen;
import GameLoop.Graphics.Sprite;
import GameLoop.Graphics.SpriteSheet;

public class Card {

    Sprite card;
    private String word;
    int x, y;
    Colour colour;

    public Card(String word, Colour colour) {
        this.colour = colour;
        this.word = word;
        x = y = 0;
        card = new Sprite(100, 50, 0, 0, SpriteSheet.card);
    }

    public void render(Screen screen) {
        screen.renderSprite(card, x, y);
    }
}
