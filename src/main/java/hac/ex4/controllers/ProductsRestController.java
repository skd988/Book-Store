package hac.ex4.controllers;

import hac.ex4.repo.Product;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Rest controller to access the products' database.
 */
@Controller
@RestController
@RequestMapping("admin/products")
public class ProductsRestController {
    /**
     * Service to access the products' database.
     */
    @Autowired
    private ProductService productService;

    /**
     * Get request to get a product by id.
     * @param id To find product by.
     * @return JSON of the product.
     * @throws ResponseStatusException If id doesn't exist in the database.
     */
    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable long id) throws ResponseStatusException{
        Optional<Product> product = productService.findById(id);
        if(product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");

        return product.get();
    }
}
