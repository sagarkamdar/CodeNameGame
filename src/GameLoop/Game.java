package GameLoop;

import GameLoop.Entities.PlayArea;
import GameLoop.Graphics.Screen;
import GameLoop.Graphics.Sprite;
import GameLoop.Input.Keyboard;
import GameLoop.Input.Mouse;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    public static int width = 960;
    public static int height = 540;
    public static int scale = 1;
    public static double FPS = 60.0;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;
    private Keyboard key;
    private Mouse mouse;
    private PlayArea playArea;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        mouse = new Mouse();
        playArea = new PlayArea();

        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double ns = 1000000000.0 / FPS;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                frame.setTitle("Game  |  " + updates + "ups , " + frames + "fps");
                //System.out.println("PacMan  |  " + updates + "ups , " + frames + "fps");
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public void update() {
        key.update();
        if (key.space) {
            playArea.newGame();
        }
        if (key.escape) {
            playArea.discardGame();
        }
        if (key.down) {
            playArea.placeCard();
        }
        if (key.up) {
            playArea.removeCard();
        }
        mouse.update();
        //System.out.println(posX + "   " + posY);
        //System.out.println(lastPress);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        screen.renderArena();
        playArea.render(screen);
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        Sprite.loadLetters();
        Game game = new Game();
        game.frame.setResizable(false);
        //game.frame.setUndecorated(true);      //Uncomment to remove border of frame
        game.frame.setTitle("Game");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
