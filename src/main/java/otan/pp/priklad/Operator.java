package otan.pp.priklad;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operator fromString(String symbol) {
        for (Operator operator : Operator.values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("No operator with symbol " + symbol);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
