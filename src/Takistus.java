import java.awt.*;

class Takistus {
    int x, y, laius, korgus;

    public Takistus(int x, int y, int laius, int korgus) {
        this.x = x;
        this.y = y;
        this.laius = laius;
        this.korgus = korgus;
    }

    public void uuenda() {
        x -= 5;
    }

    public void joonista(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, laius, korgus);
    }

    public Rectangle saadaPiirid() {
        return new Rectangle(x, y, laius, korgus);
    }

}