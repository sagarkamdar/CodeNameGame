package GameLoop.Graphics;

import java.util.HashMap;

public class Sprite {

    public static HashMap<Character, Sprite> letters = new HashMap<>();

    public final int SIZEW;
    public final int SIZEH;
    private int x, y;
    private SpriteSheet sheet;
    public int[] pixels;

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZEW = this.SIZEH = size;
        this.x = x * SIZEW;
        this.y = y * SIZEH;
        this.pixels = new int[SIZEW * SIZEH];
        this.sheet = sheet;
        load();
    }

    public Sprite(int sizeW, int sizeH, int x, int y, SpriteSheet sheet) {
        this.SIZEW = sizeW;
        this.SIZEH = sizeH;
        this.x = x * SIZEW;
        this.y = y * SIZEH;
        this.pixels = new int[SIZEW * SIZEH];
        this.sheet = sheet;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZEH; y++) {
            for (int x = 0; x < SIZEW; x++) {
                this.pixels[x + y * SIZEW] = this.sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZEW];
            }
        }
    }

    public void copySprite(Sprite sprite, int x, int y) {
        for (int yy = 0; yy < sprite.SIZEH; yy++) {
            for (int xx = 0; xx < sprite.SIZEW; xx++) {
                this.pixels[xx + x + (yy + y) * SIZEW] = sprite.pixels[xx + yy * sprite.SIZEW];
            }
        }
    }

    public void printLetter(char ch, int x, int y) {
        copySprite(letters.get(ch), x, y);
    }

    public static void loadLetters() {
        for (int i = 0; i < 26; ++i) {
            Sprite sprite = new Sprite(8, i % 6, i / 6, SpriteSheet.letters);
            letters.put((char) (i + 'A'), sprite);
        }
    }
}
