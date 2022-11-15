package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product repository to access the product database.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds discounted items ordered by highest discount to lowest.
     * @return List of products with discount, ordered by discount in descent.
     */
    @Query("select p from Product p where p.discount > 0 order by p.discount DESC")
    List<Product> findTopDiscounts();


    /**
     * Find products by name substring.
     * @param name Name to search by.
     * @return List of products matching that name.
     */
    List<Product> findByNameContains(String name);

    /**
     * Decreases quantity of product by id, if the new quantity isn't 0.
     * @param quantity Quantity to reduce product's by.
     * @param id id to find product.
     * @return Number of products updated, 1 if update was successful (at most 1 matches id), 0 otherwise.
     */
    @Transactional
    @Modifying
    @Query("update Product p set p.quantity = p.quantity - ?1 where p.id = ?2 and p.quantity >= ?1")
    int decreaseQuantityById(Integer quantity, long id);
}
