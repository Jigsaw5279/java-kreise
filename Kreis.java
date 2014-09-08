import sum.kern.*;
import java.awt.*;
/**
 * Write a description of class Kreis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kreis extends Zeichenobjekt
{

    private double radius = 0;
    
    public Kreis() {
        super();
        this.radius = 0d;
    }
    
    public Kreis(double radius) {
        super();
        this.radius = radius;
    }
    
    public Kreis(Punkt mittelpunkt, double radius) {
        super(mittelpunkt);
        this.radius = radius;
    }
    
    public void setRadius(double r) {
        if(r > 0) {
            this.radius = r;
        } else {
            System.err.println(String.format("Radius darf nicht <= 0 sein. '%1$,.2f' gegeben", r));
        }
    }
    
    public double getRadius() {
        return this.radius;
    }
    
    @Override
    public boolean getroffen(Punkt p)
    {
        return this.mittelpunkt.distanceTo(p) <= radius;
        
    }
    
    @Override
    public void zeichne(Buntstift b) {
        this.stiftVorbereiten(b);
        b.zeichneKreis(this.radius);
    }
    
    @Override
    public void groesseAendern(double aenderung) {
        this.setRadius(this.getRadius() + aenderung);
    }
}
