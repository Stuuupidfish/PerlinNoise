import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

//https://stackoverflow.com/questions/14859593/java-basic-plotting-drawing-a-point-dot-pixel

public class Main extends JFrame 
{
    private Screen screen;

    private final BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Main() {
        screen = new Screen(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        new Thread(() -> {
            while(true) {
                render();
            }
        }).start();
    }

    public static void main(String args[])
    {
        new Main();
    }


    public void render() 
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();

         for(int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
         }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
