package hac.ex4;

import hac.ex4.beans.SearchResults;
import hac.ex4.beans.ShoppingCart;
import hac.ex4.listeners.SessionTracker;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Configuration of the app.
 */
@Configuration
public class BeanConfiguration {
    /**
     * Initiates new shopping cart, in session scope.
     * @return New shopping cart.
     */
    @Bean
    @SessionScope
    public ShoppingCart shoppingCartInit(){
        return new ShoppingCart();
    }

    /**
     * Initiates new search results object, in session scope.
     * @return New search results object
     */
    @Bean
    @SessionScope
    public SearchResults searchResultsInit(){
        return new SearchResults();
    }

    /**
     * Sets SessionTracker as a listener to the sessions, to count and present how many active sessions are there.
     * @return listener of the sessions.
     */
    @Bean
    public ServletListenerRegistrationBean<SessionTracker> sessionTracker(){
        ServletListenerRegistrationBean<SessionTracker> listener = new ServletListenerRegistrationBean<>();
        listener.setListener(new SessionTracker());
        return listener;
    }
}
