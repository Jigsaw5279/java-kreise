import sum.kern.*;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    
    @Override
    public void optionsMenue() {
        JTextField hoehe = new JTextField(String.valueOf(this.hoehe));
        JTextField breite = new JTextField(String.valueOf(this.breite));
        JTextField x = new JTextField(String.valueOf(this.getMittelpunkt().getX()));
        JTextField y = new JTextField(String.valueOf(this.getMittelpunkt().getY()));
        JColorChooser j = new JColorChooser(this.farbe);
           Object[] message = {"Höhe (double)", hoehe,
                               "Breite (double)", breite,
                               "X (double)", x,
                               "Y (double)", y,
                               "Farbe (Color)", j, };
              JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                 pane.createDialog(null, "Kreis bearbeiten").setVisible(true);
        try {
           this.setHoehe(Double.parseDouble(hoehe.getText()));
           this.setBreite(Double.parseDouble(breite.getText()));
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
