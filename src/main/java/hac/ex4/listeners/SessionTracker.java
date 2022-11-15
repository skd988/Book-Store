package hac.ex4.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tracks number of currently active sessions and presents to the user when changes occur.
 */
@WebListener
public class SessionTracker implements HttpSessionListener {
    private final AtomicInteger activeSessions;

    /**
     * Constructor - initiates number of active sessions to 0.
     */
    public SessionTracker(){
        super();
        activeSessions = new AtomicInteger();
        printActiveSessions();
    }

    /**
     * Increments sessions counter and prints the number of active sessions.
     * @param event Event of the session creation.
     */
    public void sessionCreated(final HttpSessionEvent event){
        activeSessions.incrementAndGet();
        printActiveSessions();
    }

    /**
     * Decrements sessions counter and prints the number of active sessions.
     * @param event Event of the session destruction.
     */
    public void sessionDestroyed(final HttpSessionEvent event){
        if(activeSessions.get() > 0){
            activeSessions.decrementAndGet();
            printActiveSessions();
        }
    }

    /**
     * Prints the current number of sessions active.
     */
    private void printActiveSessions(){
        System.out.println("Sessions active: " + activeSessions.get());
    }

}