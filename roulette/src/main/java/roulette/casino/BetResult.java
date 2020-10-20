package roulette.casino;

public class BetResult {

    private double winnings;

    private String status;

    private Bet bet;

    public BetResult(Bet bet, double winnings, String status) {
        setbet(bet);

        setWinnings(winnings);

        setStatus(status);
    }

    public double getWinnings() {
        return winnings;
    }

    public void setWinnings(double winnings) {
        this.winnings = winnings;
    }

    public Bet getBet() {
        return bet;
    }

    public void setbet(Bet bet) {
        if (bet == null) {
            throw new IllegalArgumentException("Bet cannot be null.");
        }

        this.bet = bet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[winnings=%s, status=%s]", winnings, status);
    }
}
