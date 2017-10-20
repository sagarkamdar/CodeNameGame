package GameLoop.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet mySheet = new SpriteSheet("graphics/SpriteSheet.png",120);
    
    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }
    
    public void load(){
        try {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            int w=image.getWidth();
            int h=image.getHeight();
            for(int y=0;y<h;y++){
                for(int x=0 ; x<w;x++){
                    pixels[x + y * w]= image.getRGB(x, y);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
