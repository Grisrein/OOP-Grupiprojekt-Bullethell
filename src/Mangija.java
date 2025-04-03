import java.awt.*;
import java.awt.event.KeyEvent;

public class Mangija {
    int x = 100, y = 150, dx = 0, dy = 0;
    private final int KIIRUS = 5;
    private final int SUURUS = 50;

    public void uuenda() { //Uuendab mängija asukohta
        x += dx;
        y += dy;

        //Lisab piirid, et mängija ei saaks ekraanist välja liikuda
        int minX = 0, maxX = 800 - SUURUS;
        int minY = 0, maxY = 300 - SUURUS;

        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    public void joonista(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SUURUS, SUURUS);
    }

    public Rectangle saadaPiirid() {
        return new Rectangle(x, y, SUURUS, SUURUS);
    }

    public void klahviVajutus(KeyEvent e) {  //Liikumine läbi maa
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                dx = -KIIRUS - 1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = KIIRUS;
                break;
            case KeyEvent.VK_UP:
                dy = -KIIRUS;
                break;
            case KeyEvent.VK_DOWN:
                dy = KIIRUS;
        }
    }

    public void klahviVabastus(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = 0;
        } else {
            dx = 0;
        }
    }
}