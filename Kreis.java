import sum.kern.*;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    
    public void setzeFarbe(Color f) {
        this.farbe = farbe;
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
    
    @Override
    public void optionsMenue() {
        JTextField radius = new JTextField(String.valueOf(this.radius));
        JTextField x = new JTextField(String.valueOf(this.getMittelpunkt().getX()));
        JTextField y = new JTextField(String.valueOf(this.getMittelpunkt().getY()));
        JColorChooser j = new JColorChooser(this.farbe);
           Object[] message = {"Radius (double)", radius,
                               "X (double)", x,
                               "Y (double)", y,
                               "Farbe (Color)", j, };
              JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                 pane.createDialog(null, "Kreis bearbeiten").setVisible(true);
        try {
           this.setRadius(Double.parseDouble(radius.getText()));
           this.setFarbe(j.getColor());
           Punkt p = new Punkt(
           Double.parseDouble(x.getText()),
           Double.parseDouble(y.getText()));
           this.setMittelpunkt(p);
        } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}