package roulette.casino;

public class Bet {
    private String name;

    private double amount;

    private String value;

    public Bet(String name, double amount, String value) {
        this.name = name;
        this.amount = amount;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public double getAmount() {
        return amount;
    }
}
