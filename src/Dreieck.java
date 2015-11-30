/**
 * Ein Objekt der Klasse repräsentiert ein Dreieck
 */
public class Dreieck {
    private Punkt ecke1 = new Punkt(0, 0);
    private static Punkt ecke2;
    private static Punkt ecke3;

    /**
     * Erzeugt einen neues Dreieck
     *
     * @param x1 x-Wert vom Punkt 1
     * @param y1 y-Wert vom Puunkt 1
     * @param x2 x-Wert vom Punkt 2
     * @param y2 y-Wert vom Punkt 2
     * @return das Dreieck
     */
    public static Dreieck newDreieck(double x1, double y1, double x2, double y2) {
        if(1!=1){ //TODO
            Utils.error("Die Punkte liegen auf einer geraden!");
            return null;
        }
        Dreieck neu = new Dreieck();
        neu.ecke2 = new Punkt(x1, y1);
        neu.ecke3 = new Punkt(x2, y2);
        return neu;
    }

    /**
     * kopiert ein Objekt der Klasse Dreieck
     *
     * @param toCopy das zu kopierende Dreieck
     * @return die Kopie
     */
    public static Dreieck copyDreieck(Dreieck toCopy) {
        return newDreieck(toCopy.getEcke2().x, toCopy.getEcke2().y, toCopy.getEcke3().x, toCopy.getEcke3().y);
    }

    /**
     * gibt die erste Ecke zurück
     *
     * @return Ecke 1
     */
    public Punkt getEcke1() {
        return ecke1;
    }

    /**
     * gibt die zweite Ecke zurück
     *
     * @return Ecke 2
     */
    public Punkt getEcke2() {
        return ecke2;
    }

    /**
     * gibt die dritte Ecke zurück
     *
     * @return Ecke 3
     */
    public Punkt getEcke3() {
        return ecke3;
    }

    Dreieck rotiere(double... Angles) {
        Punkt a = getEcke2();
        Punkt b = getEcke3();
        for (double i : Angles) {
            //Wieso soll Angles double sein, wenn rotiere nur int nimmt?
            a.rotiere(Angles[0]);
            b.rotiere(Angles[0]);
        }
        return newDreieck(a.getX(), a.getY(), b.getX(), b.getY());
    }

    /**
     * berechnet die Summe des Umfangs mehrerer Dreiecks
     *
     * @param Dreiecks die Dreiecks
     * @return die Summe
     */
    public double perimeter(Dreieck... Dreiecks) {
        double peri = 0;
        for (Dreieck d : Dreiecks) {
            peri += calcPerimeter(d);
        }
        return peri;
    }

    private double calcPerimeter(Dreieck res) {
        Punkt A = res.getEcke1();
        Punkt B = res.getEcke2();
        Punkt C = res.getEcke3();
        return (A.distanz(B) + B.distanz(C) + C.distanz(A));
    }

    /**
     * repräsentiert ein Dreieck als String
     *
     * @param d das Dreieck
     * @return der String
     */
    public String toString(Dreieck d) {
        //TODO
        return null;
    }

    private boolean inDreieck(Dreieck d, Punkt x) {
        //Dient als Hilfsmethode zu toString
        //TODO
        return false;
    }

    /**
     * erzeugt einen Inrkeis für ein Dreieck
     *
     * @return der Kreis
     */
    public Kreis inkreis() {
        Dreieck d = this;
        Punkt A = d.getEcke1();
        Punkt B = d.getEcke2();
        Punkt C = d.getEcke3();

        double a = B.distanz(C);
        double b = C.distanz(A);
        double c = A.distanz(B);

        //Formeln aus Wikipedia
        Punkt m = new Punkt((a * A.x + b * B.x + c * C.x) / perimeter(d), (a * A.y + b * B.y + c * C.y) / perimeter(d));
        double s = perimeter(d) / 2;
        double r = Math.sqrt((s - a) * (s - b) * (s - c) / s);

        return Kreis.newKreis(m.getX(), m.getY(), r);
    }

    /**
     * führt eine Aktion aus
     *
     * @param action die Aktion
     */
    public void performAction(DreieckAction action) {
        switch (action) {
            case ROTATE_RIGHT:
                rotiere(-Math.PI / 2);
                break;
            case ROTATE_LEFT:
                rotiere(Math.PI / 2);
                break;
            case NARROW:
                ecke2.x *= 0.9;
                break;
            case WIDEN:
                ecke2.x *= 1.1;
                break;
            case ENLARGE:
                ecke2.x *= 1.1;
                ecke2.y *= 1.1;
                ecke3.x *= 1.1;
                ecke3.y *= 1.1;
                break;
            case SHRINK:
                ecke2.x *= 0.9;
                ecke2.y *= 0.9;
                ecke3.x *= 0.9;
                ecke3.y *= 0.9;
                break;
        }
    }
}