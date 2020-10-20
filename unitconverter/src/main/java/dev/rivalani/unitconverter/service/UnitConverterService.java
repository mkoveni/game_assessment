package dev.rivalani.unitconverter.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class UnitConverterService {

    final double KILO_MILE_CONVERSION_RATE = 1.609344;
    final double CELSIUS_KELVIN_CONVERSION_RATE = 273.15;

    public double kelvinToCelsius(double kelvin) {
        return getFormatted(kelvin - CELSIUS_KELVIN_CONVERSION_RATE);
    }

    public double celsiusToKelvin(double celsius) {
        return getFormatted(celsius + CELSIUS_KELVIN_CONVERSION_RATE);
    }

    public double kilometerToMile(double kilos) {
        return getFormatted(kilos / KILO_MILE_CONVERSION_RATE);
    }

    public double mileToKilo(double mile) {
        return getFormatted(mile * KILO_MILE_CONVERSION_RATE);
    }

    public double getFormatted(double amount) {
        return Double.parseDouble(String.format(Locale.US, "%.2f", amount));
    }
}
