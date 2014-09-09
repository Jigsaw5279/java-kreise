import sum.kern.*;
import java.awt.*;
/**
 * @author 
 * @version 
 */
public abstract class Zeichenobjekt
{

    protected boolean zAktiv = false;
    protected Punkt mittelpunkt = new Punkt(0,0);
    protected Color farbe = Color.black;
    
    public Zeichenobjekt()
    {
        this.mittelpunkt = new Punkt(0,0);
        this.farbe = Color.black;
        this.zAktiv = false;
    }
    
    public Zeichenobjekt(Punkt mittelpunkt) {
        this.mittelpunkt = mittelpunkt;
        this.farbe = Color.black;
        this.zAktiv = false;
    }
    
    public Zeichenobjekt(Punkt mittelpunkt, Color farbe) {
        this.mittelpunkt = mittelpunkt;
        this.farbe = farbe;
        this.zAktiv = false;
    }
    
    public Zeichenobjekt(Punkt mittelpunkt, Color farbe, boolean zAktiv) {
        this.mittelpunkt = mittelpunkt;
        this.farbe = farbe;
        this.zAktiv = zAktiv;
    }
    
    public void move(double x, double y) {
        this.mittelpunkt.move(x,y);
    }
    
    public void moveX(double x) {
        this.move(x, 0);
    }
    
    public void moveY(double y) {
        this.move(0, y);
    }
        
    public void setMittelpunkt(Punkt p) {
        this.mittelpunkt = p;
    }
    
    public Punkt getMittelpunkt() {
        return this.mittelpunkt;
    }
    
    public void setFarbe(Color f) {
        this.farbe = f;
    }
    
    public Color getFarbe() {
        return this.farbe;
    }
    
    public void setAktiv(boolean aktiv) {
        this.zAktiv = aktiv;
    }
    
    public boolean isAktiv() {
        return this.zAktiv;
    }
    
    protected void stiftVorbereiten(Buntstift b) {
        b.normal();
        b.bewegeBis(this.mittelpunkt.getX(), this.mittelpunkt.getY());
        b.setzeLinienbreite(1);
        b.setzeFarbe(this.farbe);
        
        if(this.isAktiv()) {
            b.setzeFarbe(Color.red);
            b.setzeLinienbreite(3);
        }
    }
    
    public abstract void zeichne(Buntstift b);
    
    public abstract boolean getroffen(Punkt p);
    
    public abstract void groesseAendern(double aenderung);
    
    public abstract void optionsMenue();
}
