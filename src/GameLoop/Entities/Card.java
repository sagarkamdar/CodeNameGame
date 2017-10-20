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
        x = y = 10;
        card = new Sprite(100, 50, 0, 0, SpriteSheet.card);
        printWord();
    }

    public void render(Screen screen) {
        screen.renderSprite(card, x, y);
    }

    private void printWord() {
        int currX = 12, currY = 15;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                card.printLetter(ch, currX, currY);
            } else {
                continue;
            }
            currX += 8;
        }
    }
}
