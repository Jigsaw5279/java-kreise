import sum.kern.*;
/**
 * @author 
 * @version 
 */
public class Rechteck extends Zeichenobjekt
{
    private double hoehe = 0;
    private double breite = 0;
    
    public Rechteck()
    {
        super();
        this.breite = 0;
        this.hoehe = 0;
    }
    
    public Rechteck(double breite, double hoehe) {
        super();
        this.breite = breite;
        this.hoehe = hoehe;
    }
    
    public void setBreite(double breite) {
        this.breite = breite;
    }
    
    public double getBreite() {
        return this.breite;
    }
    
    public void setHoehe(double hoehe) {
        this.hoehe = hoehe;
    }
    
    public double getHoehe() {
        return this.hoehe;
    }
    
    @Override 
    public boolean getroffen(Punkt p) {
        if(p.getX() < this.mittelpunkt.getX()) return false;
        if(p.getX() > this.mittelpunkt.getX() + breite) return false;
        if(p.getY() < this.mittelpunkt.getY()) return false;
        if(p.getY() > this.mittelpunkt.getY() + hoehe) return false;
        return true;
    }
    
    @Override 
    public void zeichne(Buntstift b) {
        this.stiftVorbereiten(b);
        b.zeichneRechteck(this.breite, this.hoehe);
    }
    
    @Override
    public void groesseAendern(double aenderung) {
        this.setBreite(this.getBreite() + aenderung);
        this.setHoehe(this.getHoehe() + aenderung);
    }

}
