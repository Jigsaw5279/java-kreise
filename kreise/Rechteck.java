package kreise;


import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import kreise.exception.InvalidValueException;
import sum.kern.*;

/**
 * @author @version
 */
public class Rechteck extends Zeichenobjekt {

    private double hoehe = 0;
    private double breite = 0;

    public Rechteck() {
        super();
        this.breite = 0;
        this.hoehe = 0;
    }

    public Rechteck(double breite, double hoehe) {
        super();
        this.breite = breite;
        this.hoehe = hoehe;
    }

    public void setBreite(double b) throws InvalidValueException {
        if(b > 0) {
            this.breite = b;
        } else {
            throw new InvalidValueException(String.format("Breite darf nicht <= 0 sein. '%1$,.2f' gegeben", b));
        }
    }

    public double getBreite() {
        return this.breite;
    }

    public void setHoehe(double hoehe) throws InvalidValueException {
        if(hoehe > 0) {
            this.hoehe = hoehe;
        } else {
            throw new InvalidValueException(String.format("Höhe darf nicht <= 0 sein. '%1$,.2f' gegeben", hoehe));
        }
    }

    public double getHoehe() {
        return this.hoehe;
    }

    @Override
    public boolean getroffen(Punkt p) {
        if (p.getX() < this.mittelpunkt.getX()) {
            return false;
        }
        if (p.getX() > this.mittelpunkt.getX() + breite) {
            return false;
        }
        if (p.getY() < this.mittelpunkt.getY()) {
            return false;
        }
        if (p.getY() > this.mittelpunkt.getY() + hoehe) {
            return false;
        }
        return true;
    }

    @Override
    public void zeichne(Buntstift b) {
        this.stiftVorbereiten(b);
        b.zeichneRechteck(this.breite, this.hoehe);
    }

    @Override
    public void groesseAendern(double aenderung) {
        try {
            this.setBreite(this.getBreite() + aenderung);
            this.setHoehe(this.getHoehe() + aenderung);
        } catch (InvalidValueException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void optionsDialog() {
        boolean erfolg = false;
        while (!erfolg) {
            JTextField h = new JTextField(String.valueOf(this.hoehe));
            JTextField b = new JTextField(String.valueOf(this.breite));
            JColorChooser color = new JColorChooser(this.farbe);
            JTextField x = new JTextField(String.valueOf(this.getMittelpunkt().getX()));
            JTextField y = new JTextField(String.valueOf(this.getMittelpunkt().getY()));
            JTextField linie = new JTextField(String.valueOf(this.linienstaerke));
            Object[] message
                    = {
                        "Höhe (double)", h,
                        "Breite (double)", b,
                        "Farbe (Color)", color,
                        "X (double)", x,
                        "Y (double)", y,
                        "Linienstärke (int)", linie
                    };

            JOptionPane pane = new JOptionPane(message,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            pane.createDialog(null, "Rechteck bearbeiten").setVisible(true);
            try {
                this.setHoehe(Double.parseDouble(h.getText()));
                this.setBreite(Double.parseDouble(b.getText()));
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
