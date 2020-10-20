package roulette.exceptions;

public class InvalidBetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidBetException(String message) {
        super(message);
    }

}
