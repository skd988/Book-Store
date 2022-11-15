package hac.ex4.beans;

import hac.ex4.repo.Product;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Search results component.
 * Contains and manages searches by name to the products' database.
 */
@Component
public class SearchResults {
    /**
     * Service to access the products' database.
     */
    @Autowired
    private ProductService productService;

    /**
     * Current results received in the last search.
     * If is null then no search has been made or the search was reset.
     */
    private List<Product> results;

    /**
     * Constructor - initiates results to null (no search has been made).
     */
    public SearchResults(){
        reset();
    }

    /**
     * Performs search by name in the database.
     * Saves the results in local list.
     * @param input Name to search by.
     * @return The results of the search.
     */
    public List<Product> search(String input){
        results = productService.findByNameContains(input);
        System.out.println("results are " + toString());
        return results;
    }

    /**
     * Get results of the last search.
     * @return The results of the last search.
     */
    public List<Product> getResults(){
        return results;
    }

    /**
     * Resets the search as if no search was made.
     */
    public void reset(){
        results = null;
    }

    /**
     * Presents the current results.
     * @return A string presenting the list of search results.
     */
    @Override
    public String toString() {
        return "SearchResults{" + results + "}";
    }
}
