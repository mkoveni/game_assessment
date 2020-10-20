package dev.rivalani.unitconverter.service;

import org.springframework.stereotype.Service;

@Service
public class UnitConverterService {

    final double KILO_MILE_CONVERSION_RATE = 1.609344;
    final double CELSIUS_KELVIN_CONVERSION_RATE = 273.15;

    public double kelvinToCelsius(double kelvin) {
        return kelvin - CELSIUS_KELVIN_CONVERSION_RATE;
    }

    public double celsiusToKelvin(double celsius) {
        return celsius + CELSIUS_KELVIN_CONVERSION_RATE;
    }

    public double kilometerToMile(double kilos) {
        return kilos / KILO_MILE_CONVERSION_RATE;
    }

    public double mileToKilo(double mile) {
        return mile * KILO_MILE_CONVERSION_RATE;
    }
}
