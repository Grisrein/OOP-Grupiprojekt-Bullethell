import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Peamine klass mänguakna seadistamiseks
public class Mang extends JFrame {
    public Mang() {
        this.add(new Mangupaneel());
        this.setTitle("Mäng");
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Mang::new);
    }
}

// Paneel mängu renderdamiseks ja loogika käitlemiseks
class Mangupaneel extends JPanel implements ActionListener, KeyListener {
    private final int LAIUS = 800, KORGUS = 300;
    private Timer taimer;
    private Mangija mangija;
    private List<Takistus> takistused;
    private final int TAKISTUSE_INTERVAL = 2000;  // Aeg ms takistuste vahel
    private long viimaneTakistuseAeg = 0;

    public Mangupaneel() {
        this.setPreferredSize(new Dimension(LAIUS, KORGUS));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        mangija = new Mangija();
        takistused = new ArrayList<>();
        taimer = new Timer(10, this);
        taimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        uuendaMangu();
        repaint();
    }

    private void uuendaMangu() {
        mangija.uuenda();
        haldaTakistusi();
        kontrolliKokkuporgeid();
    }

    private void haldaTakistusi() {
        long praeguneAeg = System.currentTimeMillis();
        if (praeguneAeg - viimaneTakistuseAeg > TAKISTUSE_INTERVAL) {
            takistused.add(new Takistus(LAIUS, (int)(Math.random() * (KORGUS - 30)), 30, 30));
            viimaneTakistuseAeg = praeguneAeg;
        }

        takistused.forEach(Takistus::uuenda);
        takistused.removeIf(takistus -> takistus.x < -30);
    }

    private void kontrolliKokkuporgeid() {
        Rectangle mangijaPiirid = mangija.saadaPiirid();
        for (Takistus takistus : takistused) {
            if (takistus.saadaPiirid().intersects(mangijaPiirid)) {
                taimer.stop();
                JOptionPane.showMessageDialog(this, "Mäng läbi!");
                System.exit(0);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        joonista(g);
    }

    public void joonista(Graphics g) {
        mangija.joonista(g);
        for (Takistus takistus : takistused) {
            takistus.joonista(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mangija.klahviVajutus(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mangija.klahviVabastus(e);
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}

// Mangija klass
class Mangija {
    int x = 100, y = 150, dx = 0, dy = 0;
    private final int KIIRUS = 5;
    private final int SUURUS = 50;

    public void uuenda() {
        x += dx;
        y += dy;
    }

    public void joonista(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SUURUS, SUURUS);
    }

    public Rectangle saadaPiirid() {
        return new Rectangle(x, y, SUURUS, SUURUS);
    }

    public void klahviVajutus(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                dx = -KIIRUS;
                break;
            case KeyEvent.VK_RIGHT:
                dx = KIIRUS;
                break;
            case KeyEvent.VK_UP:
                dy = -KIIRUS;  // Lihtne hüppe implementatsioon
                break;
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

// Takistus klass
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

