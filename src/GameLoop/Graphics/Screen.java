package GameLoop.Graphics;

import java.awt.image.BufferedImage;

public class Screen {

    private int width, height;
    public int[] pixels;
    public int[] pixelsArena;
    BufferedImage imgArena;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        pixelsArena = new int[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelsArena[x + y * width] = 0xdcdcdc;     //Assign Background Image
            }
        }
    }

    public void renderArena() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = pixelsArena[x + y * width];
            }
        }
        /*for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((x/8 + y/8) % 2 == 0) {
                    pixels[x + y * width] = 0xff0000;
                }
                else
                {
                    pixels[x + y * width] = 0x000000;
                }
            }
        }*/
    }

    public void renderSprite(Sprite sprite, int x, int y) {
        int color;
        for (int yy = 0; yy < sprite.SIZEH; yy++) {
            for (int xx = 0; xx < sprite.SIZEW; xx++) {
                color = sprite.pixels[xx + yy * sprite.SIZEW];
                if (color != 0xffffff) {
                    pixels[xx + x + (yy + y) * width] = color;
                }
            }
        }
        pixels[x + 7 + (y + 7) * width] = 0xff0000;
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0x00ffff;
            }
        }
    }
}
