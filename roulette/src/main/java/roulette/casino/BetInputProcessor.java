package roulette.casino;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetInputProcessor {

    final String INPUT_EXPRESSION = "^([a-zA-Z]+)\\s+(EVEN|ODD|[0-9]+)\\s+([\\d]+([\\.][\\d]{1,2})?)";

    public Bet process(String input) {

        Bet bet = null;

        Pattern pattern = Pattern.compile(INPUT_EXPRESSION);

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            bet = new Bet(matcher.group(1), Double.valueOf(matcher.group(3)), matcher.group(2));
        }
        return bet;
    }
}
