package dev.rivalani.unitconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitConverterServiceTest {

    UnitConverterService unitConverterService;

    @BeforeEach
    void before() {
        unitConverterService = new UnitConverterService();
    }

    @Test
    void shouldConvertFromKelvinToCelsius() {
        assertEquals(26.85, unitConverterService.kelvinToCelsius(300));
    }

    @Test
    void shouldConvertFromCelsiusToKelvin() {
        assertEquals(300, unitConverterService.celsiusToKelvin(26.85));
    }

    @Test
    void shouldConvertFromMilesToKilometers() {
        assertEquals(1.61, unitConverterService.mileToKilo(1));
    }

    @Test
    void shouldConvertFromKilometersToMiles() {
        assertEquals(9.94, unitConverterService.kilometerToMile(16));
    }
}
