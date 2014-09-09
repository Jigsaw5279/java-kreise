import java.awt.Color;
import sum.kern.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @Fabio Brink und Jens Caspari    
 * @1.0.1
 */
public class Steuerung
{
    private Ansicht ansicht;
    private boolean gedrueckt = false;
    private ArrayList<Zeichenobjekt> objekte;
    private Zeichenobjekt aktiv;
    private double schritt = 1d;
            
    public Steuerung()
    {
        this.ansicht = new Ansicht(200,200);
        this.objekte = new ArrayList<Zeichenobjekt>();
    }
    
    public void setFarbe(Color f) {
        aktiv.farbe = f;
    }
    
    public Color getFarbe() {
        return aktiv.farbe;
    }
    
    public void start() {
        
        ansicht.neuZeichnen(this.objekte);
        Maus m = new Maus();
        Tastatur t = new Tastatur();
        while(true) {
            if(t.wurdeGedrueckt()) {
                verarbeiteTastatur(t);
            }
                
            if(m.istGedrueckt()) {
                this.verarbeiteMaus(m);
            } else {
                this.gedrueckt = false;
            }
        }
    }
    
    private void verarbeiteTastatur(Tastatur t) {
        boolean geloescht = false;
        switch(t.zeichen()) {
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
                if(this.aktiv != null) {
                    this.aktiv.moveX(-1 * this.schritt);
                }
                break;
            case 'd':
                if(this.aktiv != null) {
                    this.aktiv.moveX(1 * this.schritt);
                }
                break;
            case 's':
                if(this.aktiv != null) {
                    this.aktiv.moveY(1 * this.schritt);
                }
                break;
            case 'w':
                if(this.aktiv != null) {
                    this.aktiv.moveY(-1 * this.schritt);
                }
                break;
            case 'o':
                this.oeffneOptionen();
                break;
            case 'm':
                if(this.aktiv != null) {
                    this.aktiv.groesseAendern(1);
                }
                break;
            case 'n':
                if(this.aktiv != null) {
                    this.aktiv.groesseAendern(-1);
                }
                break;
            default:
                System.err.println(String.format("Tastaturbefehl '%c' ist nicht belegt", t.zeichen()));
        }
        t.weiter();
        if(!geloescht) {
            ansicht.neuZeichnen(this.objekte);
        }
    }
    
    private void oeffneOptionen() {
       // Aufruf der statischen Methode showMessageDialog()
       if (this.aktiv == null) {
        String eingabe = JOptionPane.showInputDialog(null,"Schrittweite (double)",
                                                             "Optionen",
                                                             JOptionPane.PLAIN_MESSAGE);
        try {
            this.schritt = Double.parseDouble(eingabe);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
       } else {
             this.aktiv.optionsMenue();
           }
        }
    
    
    private void verarbeiteMaus(Maus m) {
        if(!this.gedrueckt) {
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
        for(Zeichenobjekt o: this.objekte) {
            if(this.aktiv == null) {
                if(o.getroffen(p)){
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
        for(Zeichenobjekt o: this.objekte) {
            if(this.aktiv == null) {
                if(o.isAktiv()) {
                    this.aktiv = o;
                }
            } else {
                // Es darf nur ein aktives Objekt geben!
                o.setAktiv(false);
            }
        }
    }
    
    private void neuesRechteck() {
        Rechteck r = new Rechteck();
        r.optionsMenue();
        this.objekte.add(r);
    }
    
    private void neuerKreis() {
        // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten     
                Kreis k = new Kreis();
                k.optionsMenue();
                this.objekte.add(k);
    }
}