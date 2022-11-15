package hac.ex4.services;

import hac.ex4.CartItem;
import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service to provide access to product database.
 */
@Service
public class ProductService {
    /**
     * Message if the sql purchase fails.
     */
    private final String PURCHASE_FAILED_MESSAGE = "Purchase failed: Cart contains non-existent items";

    /**
     * Repository of product database.
     */
    @Autowired
    private ProductRepository repository;

    /**
     * Make purchase of items in received cart, updates the database accordingly.
     * Rollbacks if fails.
     * @param cart Cart contains the items to purchase.
     * @return The amount to pay for the purchase.
     * @throws SQLException If purchase fails because some items do not exist or quantity is too low.
     */
    @Transactional(rollbackFor = {SQLException.class})
    public double purchaseCart(ShoppingCart cart) throws SQLException {
        for (CartItem item : cart.getItems()){
            Optional<Product> product = findById(item.getId());
            if(product.isEmpty() || repository.decreaseQuantityById(item.getQuantity(), item.getId()) == 0)
                throw new SQLException(PURCHASE_FAILED_MESSAGE);
        }

        return calculatePayment(cart);
    }

    /**
     * Calculates the payment that should be made for a specific cart
     * @param cart Cart to purchase
     * @return The payment that should be made for that cart.
     */
    public double calculatePayment(ShoppingCart cart) {
        double toPay = 0;
        for (CartItem item : cart.getItems()){
            Optional<Product> product = findById(item.getId());
            if(product.isPresent())
                toPay += product.get().getDiscountedPrice() * item.getQuantity();;
        }

        return toPay;

    }
    /**
     * Save a new product to the database.
     * @param product New product to add to the database.
     */
    public void save(Product product){
        repository.save(product);
    }

    /**
     * Delete product by id from database (if it exists).
     * @param id id to find product by.
     */
    public void deleteIfExists(long id){
        if(exists(id))
            repository.deleteById(id);
    }

    /**
     * Updates product by new product and id, if exists.
     * @param product New product details.
     * @param id id of the product to update.
     * @return Whether update was successful (if id is in the database).
     */
    public boolean update(Product product, long id){
        if(!exists(id))
            return false;

        product.setId(id);
        save(product);
        return true;
    }

    /**
     * Returns whether a product exists in the database.
     * @param id id to find product by.
     * @return Whether product exists.
     */
    public boolean exists(long id){
        return repository.existsById(id);
    }

    /**
     * Find product by id.
     * @param id To find product by.
     * @return Optional of product contains the product, if exists (otherwise null).
     */
    public Optional<Product> findById(long id){
        return repository.findById(id);
    }

    /**
     * Find products by name substring.
     * @param name Name to find by.
     * @return List of products matching that name.
     */
    public List<Product> findByNameContains(String name){
        return repository.findByNameContains(name);
    }

    /**
     * Get all products in the database.
     * @return List of products in the database.
     */
    public List<Product> getAll(){
        return repository.findAll();
    }

    /**
     * Find the top discounted items in the database, limited by received value.
     * @param limit Limit of the number of products in the list.
     * @return List of the top discounted products, number is limited by value.
     */
    public List<Product> findTopDiscounts(int limit){
        return repository.findTopDiscounts().stream().limit(limit).collect(Collectors.toList());
    }
}
