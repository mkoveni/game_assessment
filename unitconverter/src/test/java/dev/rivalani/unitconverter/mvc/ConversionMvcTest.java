package dev.rivalani.unitconverter.mvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dev.rivalani.unitconverter.controller.ConversionController;
import dev.rivalani.unitconverter.service.UnitConverterService;

@ExtendWith(MockitoExtension.class)
public class ConversionMvcTest {

    MockMvc mockMvc;

    @InjectMocks
    ConversionController controller;

    @Mock
    UnitConverterService service;

    @BeforeEach
    void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnJson() throws Exception {

        when(service.kelvinToCelsius(anyDouble())).thenReturn(23.6);

        mockMvc.perform(MockMvcRequestBuilders.post("/conversions/ktoc").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"amount\": 300}").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.unit").value("Celsius"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(23.6)).andDo(MockMvcResultHandlers.print());
    }
}
