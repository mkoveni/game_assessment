package dev.rivalani.unitconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import dev.rivalani.unitconverter.Unit;
import dev.rivalani.unitconverter.http.ConversionRequest;
import dev.rivalani.unitconverter.http.ConversionResponse;
import dev.rivalani.unitconverter.service.UnitConverterService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ConversionControllerTest {

    @Mock
    UnitConverterService unitConverterService;

    @InjectMocks
    ConversionController controller;

    ConversionRequest request;

    @BeforeEach
    void before() {
        request = new ConversionRequest();
        request.setAmount(10);
    }

    @Test
    void shoudReturnWithUnitOfMiles() {

        ResponseEntity<ConversionResponse> responseEntity = controller.kilosToMiles(request);

        assertEquals(Unit.Miles, Unit.valueOf(responseEntity.getBody().getUnit()));

    }

    @Test
    void shoudReturnWithUnitOfKilometers() {
        ResponseEntity<ConversionResponse> responseEntity = controller.milesToKilos(request);

        assertEquals(Unit.Kilometers, Unit.valueOf(responseEntity.getBody().getUnit()));
    }

    @Test
    void shoudReturnWithUnitOfCelsius() {
        ResponseEntity<ConversionResponse> responseEntity = controller.kelvinToCelsius(request);

        assertEquals(Unit.Celsius, Unit.valueOf(responseEntity.getBody().getUnit()));
    }

    @Test
    void shoudReturnWithUnitOfKelvin() {
        ResponseEntity<ConversionResponse> responseEntity = controller.celsiusToKelvin(request);

        assertEquals(Unit.Kelvin, Unit.valueOf(responseEntity.getBody().getUnit()));
    }

}
