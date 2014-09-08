import sum.kern.*;
import java.util.ArrayList;
/**
 * @author 
 * @version 
 */
public class Ansicht
{
    private Fenster fenster;
    private Buntstift stift;

    
    public Ansicht(int w, int h)
    {
        this.fenster = new Fenster(w, h);
        this.stift = new Buntstift(this.fenster);
        
    }    
    
    public void neuZeichnen(ArrayList<Zeichenobjekt> objekte) {
        this.loescheAlles();
        for(Zeichenobjekt o: objekte) {
            this.zeichneObjekt(o);
        }
    }
    
    public void loescheAlles() {
        this.fenster.loescheAlles();
    }
    
    private void zeichneObjekt(Zeichenobjekt z) {
        z.zeichne(stift);
    }

}
