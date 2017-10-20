package GameLoop.Graphics;

public class Sprite {

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
}
