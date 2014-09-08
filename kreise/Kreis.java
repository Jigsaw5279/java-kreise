package kreise;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import kreise.exception.InvalidValueException;
import sum.kern.*;

/**
 * Write a description of class Kreis here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kreis extends Zeichenobjekt {

    private double radius;

    public Kreis() {
        super();
        this.radius = 1d;
    }

    public Kreis(double radius) {
        super();
        this.radius = radius;
    }

    public Kreis(Punkt mittelpunkt, double radius) {
        super(mittelpunkt);
        this.radius = radius;
    }

    public void setRadius(double r) throws InvalidValueException {
        if (r > 0) {
            this.radius = r;
        } else {
            throw new InvalidValueException(String.format("Radius darf nicht <= 0 sein. '%1$,.2f' gegeben", r));
        }
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean getroffen(Punkt p) {
        return this.mittelpunkt.distanceTo(p) <= radius;

    }

    @Override
    public void zeichne(Buntstift b) {
        this.stiftVorbereiten(b);
        b.zeichneKreis(this.radius);
    }

    @Override
    public void groesseAendern(double aenderung) {
        try {
            this.setRadius(this.getRadius() + aenderung);
        } catch (InvalidValueException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void optionsDialog() {
        boolean erfolg = false;
        while (!erfolg) {
            JTextField r = new JTextField(String.valueOf(this.radius));
            JColorChooser color = new JColorChooser(this.farbe);
            JTextField x = new JTextField(String.valueOf(this.getMittelpunkt().getX()));
            JTextField y = new JTextField(String.valueOf(this.getMittelpunkt().getY()));
            JTextField linie = new JTextField(String.valueOf(this.linienstaerke));
            
            Object[] message
                    = {
                        "Radius (double)", r,
                        "Farbe (Color)", color,
                        "X (double)", x,
                        "Y (double)", y,
                        "Linienstärke (int)", linie
                    };

            JOptionPane pane = new JOptionPane(message,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            pane.createDialog(null, "Kreis bearbeiten").setVisible(true);
            try {
                this.setRadius(Double.parseDouble(r.getText()));
                this.setFarbe(color.getColor());
                Punkt p = new Punkt(
                        Double.parseDouble(x.getText()),
                        Double.parseDouble(y.getText())
                );
                this.setMittelpunkt(p);
                this.setLinienstaerke(Integer.parseInt(linie.getText()));
                erfolg = true;
            } catch (NumberFormatException | InvalidValueException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
