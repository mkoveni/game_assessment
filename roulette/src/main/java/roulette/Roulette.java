package roulette;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import roulette.casino.Bet;
import roulette.casino.BetInputProcessor;
import roulette.casino.BetResult;
import roulette.casino.Casino;
import roulette.casino.CasinoStore;
import roulette.event.RoundChangedEvent;
import roulette.event.RoundChangedListener;

/**
 * A simple roulette simulation game.
 * 
 * @author Rivalani Simon Hlengani
 */

public class Roulette implements Runnable, RoundChangedListener {

    private static RoundChangedEvent event;

    private static ScheduledExecutorService scheduledExecutorService;

    private Casino casino;

    private Random random;

    public Roulette(Casino casino) {
        this.casino = casino;
        casino.start();
        random = new Random();
    }

    void begin() {
        Scanner in = new Scanner(System.in);
        BetInputProcessor processor = new BetInputProcessor();
        String input = null;

        casino.setBetNumber(random.nextInt(37));

        do {
            System.out.println("");
            input = in.nextLine();

            Bet bet = processor.process(input);

            if (bet != null) {
                casino.placeBet(bet);
            }

        } while (!input.equalsIgnoreCase(""));

        Roulette.scheduledExecutorService.shutdown();

    }

    @Override
    public void run() {
        event.update(this.random.nextInt(37));
        casino.setBetNumber(random.nextInt(37));
    }

    @Override
    public void update(int round) {

        Map<Integer, List<BetResult>> results = casino.getResults();

        for (Integer number : results.keySet()) {

            System.out.println("Number: " + number);
            System.out.println("---");

            System.out.printf("%s %10s %10s %10s\n", "Player", "Bet", "Outcome", "Winnings");

            results.get(number).stream().forEach(r -> {
                System.out.printf(String.format("%s %10s %10s %10s\n", r.getBet().getName(), r.getBet().getValue(),
                        r.getStatus(), r.getWinnings()));
            });

        }

        casino.nextRound();

    }

    public static void main(String[] args) {

        Roulette roulette = new Roulette(new Casino(new CasinoStore()));

        event = new RoundChangedEvent();
        event.addListener(roulette);
        scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(roulette, 0, 30, TimeUnit.SECONDS);

        System.out.println("=====================================================");
        System.out.println("Welcome to the Roulette");
        System.out.println("Place your BET in the format {Name} {Bet} {Amount}");
        System.out.println("Press enter without any input to QUIT");
        System.out.println("=====================================================");

        roulette.begin();

    }

}
