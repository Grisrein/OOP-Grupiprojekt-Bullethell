import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Mangupaneel extends JPanel implements ActionListener, KeyListener {
    private final int LAIUS = 800, KORGUS = 300;
    private Timer taimer; //Mäng lõpetab töötamise kui eemaldada
    private Mangija mangija;
    //Takistuse elemendid
    private final List<Takistus> takistused;
    private long viimaneTakistuseAeg = 0;
    private int takistuseIntervalMax = 2000;
    private int takistuseInterval = getRandomNumber(500,takistuseIntervalMax); //Suvaline number. Muudab takistuste loomise ajavahemikku
    //Mängitud aja elemendid
    private final JLabel aegSilt;
    private final long aegStart;
    private String aegLõpp;
    private final Timer aeg;

    public Mangupaneel() {
        this.setPreferredSize(new Dimension(LAIUS, KORGUS));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        mangija = new Mangija();
        takistused = new ArrayList<>();
        taimer = new Timer(10, this);
        taimer.start();

        //Mängitud aeg
        aegStart = System.nanoTime();
        aegLõpp = "";
        aegSilt = new JLabel(aegLõpp);

        aegSilt.setFont(new Font("DialogInput", Font.BOLD, 24));
        aegSilt.setForeground(Color.WHITE);
        add(aegSilt);

        aeg = new Timer(16, e -> uuendaAeg());  // Uuendab aega iga 16ns
        aeg.start();
    }

    private void uuendaAeg() {
        long läbitudAeg = System.nanoTime() - aegStart;
        double sekundid = läbitudAeg / 1_000_000_000.0;
        aegLõpp = String.format("%.3f s", sekundid);
        aegSilt.setText(aegLõpp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        uuendaMangu();
        repaint();
    }

    private void uuendaMangu() { //uuendab mängu seisundit
        mangija.uuenda();
        haldaTakistusi(); //loob ja uuendab takistuste positsioone
        kontrolliKokkuporgeid(); 
    }

    public int getRandomNumber(int min, int max) {  //Annab tagasi suvalise numbri min ja max-1 vahemikus
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void haldaTakistusi() { //haldab takistuste loomist ja liikumist
        long praeguneAeg = System.currentTimeMillis();
        // Aeg mis takistuste vahel
        if (praeguneAeg - viimaneTakistuseAeg > takistuseInterval) {
            //loob uue takistuse ja lisab takistuste listi
            takistused.add(new Takistus(LAIUS, (int)(Math.random() * (KORGUS - 30)), 30, 30));
            viimaneTakistuseAeg = praeguneAeg;
            takistuseInterval = getRandomNumber(500,takistuseIntervalMax); //Uuendab järgmise takistuse loomise ajavahemikku
            takistuseIntervalMax -= 20;
        }

        takistused.forEach(Takistus::uuenda); // uuendab takistuse positsiooni
        takistused.removeIf(takistus -> takistus.x < -30);// eemaldab takistused, mis on ekraanilt väljas
    }

    private void kontrolliKokkuporgeid() {
        Rectangle mangijaPiirid = mangija.saadaPiirid();
        for (Takistus takistus : takistused) {
            if (takistus.saadaPiirid().intersects(mangijaPiirid)) { //kui mängija ja takistuse piirid kattuvad
                taimer.stop(); //peatab taimeri
                aeg.stop();
                JOptionPane.showMessageDialog(this, "Mäng läbi! Teie lõplik aeg oli " + aegLõpp);
                System.exit(0); //väljub programmist
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) { //sellega saame mängu graafilised komponendid joonistada
        super.paintComponent(g);
        joonista(g); //mängu elemendid
    }

    public void joonista(Graphics g) {
        mangija.joonista(g); //joonistab mängija
        for (Takistus takistus : takistused) { //käib takistused läbi
            takistus.joonista(g); //joonistab takistused
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mangija.klahviVajutus(e); //saame tänu sellele mängijat juhtida
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mangija.klahviVabastus(e);
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
