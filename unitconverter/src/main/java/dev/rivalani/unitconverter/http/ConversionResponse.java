package dev.rivalani.unitconverter.http;

public class ConversionResponse {
    private double amount;
    private String unit;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amout) {
        this.amount = amout;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
