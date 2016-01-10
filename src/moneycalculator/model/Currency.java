package moneycalculator.model;

public class Currency {

    private final String acronym;
    private final String name;
    private final String symbol;

    public Currency(String acronym, String name, String symbol) {
        this.acronym = acronym;
        this.name = name;
        this.symbol = symbol;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
    
    
    
}
