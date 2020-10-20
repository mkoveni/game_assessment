package roulette;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import roulette.casino.Player;

public class PlayerTest {

    @Test
    void shouldInstantiateWithPlayerName() {
        Player player = new Player("simon");

        assertEquals("simon", player.getName());
    }
}
