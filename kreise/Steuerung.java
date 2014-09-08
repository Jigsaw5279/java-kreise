package kreise;


import sum.kern.*;
import java.util.ArrayList;
import javax.swing.*;

public class Steuerung {

    private final Ansicht ansicht;
    private boolean gedrueckt = false;
    private final ArrayList<Zeichenobjekt> objekte;
    private Zeichenobjekt aktiv;
    private double schritt = 1d;

    public Steuerung() {
        this.ansicht = new Ansicht(200, 200);
        this.objekte = new ArrayList<>();
    }

    public void start() {

        ansicht.neuZeichnen(this.objekte);
        Maus m = new Maus();
        Tastatur t = new Tastatur();
        while (true) {
            if (t.wurdeGedrueckt()) {
                verarbeiteTastatur(t);
            }

            if (m.istGedrueckt()) {
                this.verarbeiteMaus(m);
            } else {
                this.gedrueckt = false;
            }
        }
    }

    private void verarbeiteTastatur(Tastatur t) {
        boolean geloescht = false;
        switch (t.zeichen()) {
            case 'e':
                System.exit(0);
                break;
            case 'r':
                this.neuesRechteck();
                break;
            case 'k':
                this.neuerKreis();
                break;
            case 'c':
                this.ansicht.loescheAlles();
                geloescht = true;
                break;
            case 'a':
                if (this.aktiv != null) {
                    this.aktiv.moveX(-1 * this.schritt);
                }
                break;
            case 'd':
                if (this.aktiv != null) {
                    this.aktiv.moveX(1 * this.schritt);
                }
                break;
            case 's':
                if (this.aktiv != null) {
                    this.aktiv.moveY(1 * this.schritt);
                }
                break;
            case 'w':
                if (this.aktiv != null) {
                    this.aktiv.moveY(-1 * this.schritt);
                }
                break;
            case 'o':
                this.oeffneOptionen();
                break;
            case 'm':
                if (this.aktiv != null) {
                    this.aktiv.groesseAendern(1 * this.schritt);
                }
                break;
            case 'n':
                if (this.aktiv != null) {
                    this.aktiv.groesseAendern(-1 * this.schritt);
                }
                break;
            default:
                System.err.println(String.format("Tastaturbefehl '%c' ist nicht belegt", t.zeichen()));
        }
        t.weiter();
        if (!geloescht) {
            ansicht.neuZeichnen(this.objekte);
        }
    }

    private void oeffneOptionen() {
        if (this.aktiv == null) {
            boolean erfolg = false;
            while (!erfolg) {
                String eingabe = JOptionPane.showInputDialog("Schrittweite (double)",
                        String.valueOf(this.schritt));
                
                try {
                    this.schritt = Double.parseDouble(eingabe);
                    erfolg = true;
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        } else {
            this.aktiv.optionsDialog();
            this.ansicht.neuZeichnen(this.objekte);
        }

    }

    private void verarbeiteMaus(Maus m) {
        if (!this.gedrueckt) {
            // Ein einzelner Klick

            Punkt akt = new Punkt(m.hPosition(), m.vPosition());
            this.aktiviere(akt);
            ansicht.neuZeichnen(this.objekte);
            // Merken, dass gedrückt wurde
            this.gedrueckt = true;
        }
    }

    private void aktiviere(Punkt p) {
        this.aktiv = null;
        for (Zeichenobjekt o : this.objekte) {
            if (this.aktiv == null) {
                if (o.getroffen(p)) {
                    o.setAktiv(true);
                    this.aktiv = o;
                } else {
                    o.setAktiv(false);
                }
            } else {
                o.setAktiv(false);
            }
        }
    }

    private void findAktiv() {
        this.aktiv = null;
        for (Zeichenobjekt o : this.objekte) {
            if (this.aktiv == null) {
                if (o.isAktiv()) {
                    this.aktiv = o;
                }
            } else {
                // Es darf nur ein aktives Objekt geben!
                o.setAktiv(false);
            }
        }
    }

    private void neuesRechteck() {
        // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten     
        Rechteck r = new Rechteck();
        r.optionsDialog();
        this.objekte.add(r);
    }

    private void neuerKreis() {
        // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten     
        Kreis k = new Kreis();
        k.optionsDialog();
        this.objekte.add(k);
    }

}