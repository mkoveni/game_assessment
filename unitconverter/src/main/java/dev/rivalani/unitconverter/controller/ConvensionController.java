package dev.rivalani.unitconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.rivalani.unitconverter.Unit;
import dev.rivalani.unitconverter.http.ConversionRequest;
import dev.rivalani.unitconverter.http.ConversionResponse;
import dev.rivalani.unitconverter.service.UnitConverterService;

@RestController
@RequestMapping("/conversions")
public class ConvensionController {

    @Autowired
    UnitConverterService unitConverterService;

    @RequestMapping(value = "/ktoc", method = RequestMethod.POST)
    public ResponseEntity<ConversionResponse> kelvinToCelsius(@RequestBody ConversionRequest conversionRequest) {
        double celsius = unitConverterService.kelvinToCelsius(conversionRequest.getAmount());

        return ResponseEntity.ok(generatConversionResponse(celsius, Unit.Celsius));
    }

    @RequestMapping(value = "/ctok", method = RequestMethod.POST)
    public ResponseEntity<ConversionResponse> celsiusToKelvin(@RequestBody ConversionRequest conversionRequest) {
        double kelvin = unitConverterService.celsiusToKelvin(conversionRequest.getAmount());

        return ResponseEntity.ok(generatConversionResponse(kelvin, Unit.Kelvin));
    }

    @RequestMapping(value = "/ktom", method = RequestMethod.POST)
    public ResponseEntity<ConversionResponse> kilosToMiles(@RequestBody ConversionRequest conversionRequest) {
        double miles = unitConverterService.kilometerToMile(conversionRequest.getAmount());

        return ResponseEntity.ok(generatConversionResponse(miles, Unit.Miles));
    }

    @RequestMapping(value = "/mtok", method = RequestMethod.POST)
    public ResponseEntity<ConversionResponse> milesToKilos(@RequestBody ConversionRequest conversionRequest) {
        double kilos = unitConverterService.mileToKilo(conversionRequest.getAmount());

        return ResponseEntity.ok(generatConversionResponse(kilos, Unit.Kilometers));
    }

    private ConversionResponse generatConversionResponse(double amount, Unit unit) {
        ConversionResponse response = new ConversionResponse();
        response.setAmout(amount);
        response.setUnit(unit.name());

        return response;
    }
}
