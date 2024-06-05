package otan.pp.priklad;

public class Priklad {
    int a;
    Operator operator;
    int b;
    double vysledek;
    double spravnyVysledek;
    boolean spravne;

    public Priklad(int a, Operator operator, int b, int vysledek) {
        this.a = a;
        this.operator = operator;
        this.b = b;
        this.vysledek = vysledek;
        this.spravnyVysledek = calculate();
        this.spravne = spravnyVysledek == vysledek;
    }

    public double calculate() {
        return switch (operator) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case TIMES -> a * b;
            case DIVIDE -> (double) a / b;
        };
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getVysledek() {
        return vysledek;
    }

    public void setVysledek(int vysledek) {
        this.vysledek = vysledek;
    }

    public double getSpravnyVysledek() {
        return spravnyVysledek;
    }

    public void setSpravnyVysledek(int spravnyVysledek) {
        this.spravnyVysledek = spravnyVysledek;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isSpravne() {
        return spravne;
    }

    public void setSpravne(boolean spravne) {
        this.spravne = spravne;
    }

    @Override
    public String toString() {
        return "Priklad{" +
                "a=" + a +
                ", operator=" + operator +
                ", b=" + b +
                ", vysledek=" + vysledek +
                ", spravnyVysledek=" + spravnyVysledek +
                ", spravne=" + spravne +
                '}';
    }
}
