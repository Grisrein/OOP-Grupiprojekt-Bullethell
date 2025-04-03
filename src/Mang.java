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