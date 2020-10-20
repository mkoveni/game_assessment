package roulette.exceptions;

public class UnregisteredPlayerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnregisteredPlayerException(String message) {
        super(message);
    }
}
