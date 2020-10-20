package roulette;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roulette.casino.CasinoStore;
import roulette.casino.Player;

public class CasinoStoreTest {

    CasinoStore casinoStore;

    @BeforeEach
    void before() {
        casinoStore = new CasinoStore();
        casinoStore.setPlayers(List.of(new Player("Simon"), new Player("Almina")));
    }

    @Test
    void shouldReturnNullWhenPlayerIsNotFound() {
        Optional<Player> kate = casinoStore.findPlayerByName("kate");

        assertNull(kate.orElse(null));
    }

    @Test
    void shouldFindPlayer() {
        Optional<Player> simon = casinoStore.findPlayerByName("simon");

        assertNotNull(simon.orElse(null));
    }

}
