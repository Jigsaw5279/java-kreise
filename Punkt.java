public class Punkt
{
    private double x;
    private double y;
    
    public Punkt() 
    {
        this.x = 0;
        this.y = 0;
    }
    
    public Punkt(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void move(double x, double y) {
        this.x += x; // Kurzform für this.x = this.x + x;
        this.y += y;
    }
    
    public void moveX(double x) {
        this.move(x, 0);
    }
    
    public void moveY(double y) {
        this.move(0, y);
    }

    public void setX(double x) {
        this.x = x;
    }
    
    public double distanceTo(Punkt x) {
        return Math.abs(
                Math.sqrt(
                    (Math.pow(this.x - x.getX(),2) + 
                    Math.pow(this.y - x.getY(),2))
            )); //
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public boolean equals(Punkt p) {
        return p.getX() == this.x && p.getY() == this.y;
    }

}
