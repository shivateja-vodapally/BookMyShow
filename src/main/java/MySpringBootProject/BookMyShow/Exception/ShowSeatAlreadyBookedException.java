package MySpringBootProject.BookMyShow.Exception;

public class ShowSeatAlreadyBookedException extends Exception{
    public ShowSeatAlreadyBookedException() {
        super();
    }

    public ShowSeatAlreadyBookedException(String message) {
        super(message);
    }
}
