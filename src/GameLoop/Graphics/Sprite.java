package GameLoop.Graphics;

public class Sprite {

    public final int SIZE;
    private int x, y;
    private SpriteSheet sheet;
    public int[] pixels;
    
    Sprite sprite = new Sprite(16, 0, 0, SpriteSheet.mySheet);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        load();
    }

    public void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.pixels[x + y * SIZE] = this.sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
            }
        }
    }
}
