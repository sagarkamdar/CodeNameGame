package GameLoop.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    public final int SIZEW;
    public final int SIZEH;
    public int[] pixels;

    public static SpriteSheet card = new SpriteSheet("graphics/card.png", 120, 50);
    public static SpriteSheet letters = new SpriteSheet("graphics/letters.png", 48);

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZEW = size;
        this.SIZEH = size;
        pixels = new int[SIZEW * SIZEH];
        load();
    }

    public SpriteSheet(String path, int sizeW, int sizeH) {
        this.path = path;
        this.SIZEW = sizeW;
        this.SIZEH = sizeH;
        pixels = new int[SIZEW * SIZEH];
        load();
    }

    public void load() {
        try {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);

            int w = image.getWidth();
            int h = image.getHeight();

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pixels[x + y * w] = image.getRGB(x, y);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
