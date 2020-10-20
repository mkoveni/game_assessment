package roulette.casino;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import roulette.exceptions.InvalidBetException;
import roulette.exceptions.UnregisteredPlayerException;

public class Casino {

    private int betNumber;

    final int EVEN_WIN_MULTIPLE = 2;
    final int ODD_WIN_MULTIPLE = 2;
    final int EXACT_WIN_MULTIPLE = 36;

    private CasinoStore casinoStore;

    private Map<Integer, List<BetResult>> betResults;

    public Casino(CasinoStore casinoStore) {
        this.casinoStore = casinoStore;
        betResults = new Hashtable<>();
    }

    public void start() {
        casinoStore.init();
    }

    public void setBetNumber(int number) {
        betNumber = number;
    }

    public void placeBet(Bet bet) {

        if (bet == null) {
            throw new InvalidBetException("The placed bet in not valid");
        }

        if (!verifyPlayer(bet.getName())) {
            throw new UnregisteredPlayerException(String.format("Player %s is not registered to play.", bet.getName()));
        }

        BetResult results = null;

        switch (bet.getValue().toUpperCase()) {
            case "EVEN":
                results = processEvenWinnings(bet);
                break;
            case "ODD":
                results = processOddWinnings(bet);
                break;
            default:
                results = processDefaultWinnings(bet);
                break;
        }

        addToResult(results);

    }

    public Map<Integer, List<BetResult>> getResults() {
        return betResults;
    }

    private void addToResult(BetResult betResult) {
        if (betResults.containsKey(betNumber)) {
            betResults.get(betNumber).add(betResult);
        } else {
            ArrayList<BetResult> results = new ArrayList<>();
            results.add(betResult);

            betResults.put(betNumber, results);
        }
    }

    public BetResult processOddWinnings(Bet bet) {
        BetResult betResult = null;

        if (betNumber % 2 != 0) {
            betResult = new BetResult(bet, bet.getAmount() * ODD_WIN_MULTIPLE, "WIN");
        } else {
            betResult = new BetResult(bet, 0, "LOSE");
        }

        return betResult;
    }

    public BetResult processEvenWinnings(Bet bet) {
        BetResult betResult = null;

        if (betNumber % 2 == 0) {
            betResult = new BetResult(bet, bet.getAmount() * EVEN_WIN_MULTIPLE, "WIN");
        } else {
            betResult = new BetResult(bet, 0, "LOSE");
        }

        return betResult;
    }

    public BetResult processDefaultWinnings(Bet bet) {
        BetResult betResult = null;

        if (betNumber > 0 && betNumber == Integer.parseInt(bet.getValue())) {
            betResult = new BetResult(bet, bet.getAmount() * EXACT_WIN_MULTIPLE, "WIN");
        } else {
            betResult = new BetResult(bet, 0, "LOSE");
        }

        return betResult;
    }

    public void nextRound() {
        betResults.clear();
    }

    private boolean verifyPlayer(String playerName) {
        Player player = casinoStore.findPlayerByName(playerName).orElse(null);
        return player != null;
    }

}
