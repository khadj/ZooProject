package project.exceptions;

public class IslandException extends RuntimeException {
    public IslandException(String message) {super(message);}
    public IslandException(Throwable cause){
        super(cause);
    }
}
