package roulette;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roulette.casino.Bet;
import roulette.casino.BetInputProcessor;

public class BeInputProcessorTest {

    BetInputProcessor betInputProcessor;

    @BeforeEach
    void before() {
        betInputProcessor = new BetInputProcessor();
    }

    @Test
    void shouldReturnBetOnOddPlacement() {
        assertNotNull(betInputProcessor.process("Simon ODD 20"));
    }

    @Test
    void shouldReturnBetOnEvenPlacement() {
        assertNotNull(betInputProcessor.process("Simon EVEN 20"));
    }

    @Test
    void shouldReturnBetOnNumberPlacement() {
        assertNotNull(betInputProcessor.process("Simon 10 20"));
    }

    @Test
    void shouldFailOnIncorrectBetFormat() {
        assertNull(betInputProcessor.process("Simon Simon 20"));
    }
}
