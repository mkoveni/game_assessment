package roulette;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import roulette.casino.Bet;
import roulette.casino.BetResult;
import roulette.casino.Casino;
import roulette.casino.CasinoStore;
import roulette.casino.Player;
import roulette.exceptions.InvalidBetException;
import roulette.exceptions.UnregisteredPlayerException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CasinoTest {

    @InjectMocks
    Casino casino;

    @Mock
    CasinoStore store;

    final int EVEN_WIN_MULTIPLE = 2;
    final int ODD_WIN_MULTIPLE = 2;
    final int EXACT_WIN_MULTIPLE = 36;

    @Test
    void shouldThrowInvalidExceptionGivenNullBet() {
        assertThrows(InvalidBetException.class, () -> {
            casino.placeBet(null);
        });
    }

    @Test
    void shouldThrowUnregisteredPlayerException() {
        assertThrows(UnregisteredPlayerException.class, () -> {
            casino.placeBet(new Bet("Simon", 20, "EVEN"));
        });
    }

    @Test
    void shouldPlaceBet() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.placeBet(new Bet("Simon", 20, "EVEN"));
        assertTrue(!casino.getResults().isEmpty());
    }

    @Test
    void shouldWinOnEven() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(20);

        casino.placeBet(new Bet("Simon", 20, "EVEN"));

        BetResult result = casino.getResults().get(20).get(0);

        assertEquals(20 * EVEN_WIN_MULTIPLE, result.getWinnings());
    }

    @Test
    void shouldWinOnOdd() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(21);

        casino.placeBet(new Bet("Simon", 500, "ODD"));

        BetResult result = casino.getResults().get(21).get(0);

        assertEquals(500 * ODD_WIN_MULTIPLE, result.getWinnings());
    }

    @Test
    void shouldWinOnNumber() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(20);

        casino.placeBet(new Bet("Simon", 20, "20"));

        BetResult result = casino.getResults().get(20).get(0);

        assertEquals(20 * EXACT_WIN_MULTIPLE, result.getWinnings());
    }

    @Test
    void shouldLoseOnNumber() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(20);

        casino.placeBet(new Bet("Simon", 700, "2"));

        BetResult result = casino.getResults().get(20).get(0);

        assertEquals("LOSE", result.getStatus());
    }

    @Test
    void shouldLoseOnEven() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(21);

        casino.placeBet(new Bet("Simon", 200, "EVEN"));

        BetResult result = casino.getResults().get(21).get(0);

        assertEquals("LOSE", result.getStatus());
    }

    @Test
    void shouldLoseOnOdd() {
        when(store.findPlayerByName(anyString())).thenReturn(Optional.of(new Player("Simon")));

        casino.setBetNumber(20);

        casino.placeBet(new Bet("Simon", 20, "ODD"));

        BetResult result = casino.getResults().get(20).get(0);

        assertEquals("LOSE", result.getStatus());
    }
}
