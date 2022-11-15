package hac.ex4.controllers;

import hac.ex4.repo.Product;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Controller to the admin product management.
 */
@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {
    /**
     * Service to access the products' database.
     */
    @Autowired
    private ProductService productService;

    /**
     * Get request to the main page of the product management:
     * Contains admin's nav-bar to navigate to other admin operations,
     * a form to add new products to the database,
     * and a list of products that are in the database.
     * Each product can be deleted from the database,
     * or chosen from the list to be updated.
     * @param product Default product, needed for technical reasons when this is called directly.
     * @param result Validation result for the product.
     * @param model Model to add the list of products from the database to.
     * @return View of the product management page.
     */
    @GetMapping("")
    public String products(@Valid Product product, BindingResult result, Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    /**
     * Post request to add a product to the database.
     * If product is valid saves it to the database,
     * otherwise passes the errors back to product management page.
     * @param product Product to add to the database, received from the form in products management page
     * @param result Validation result for the product.
     * @param redirectAttributes Adds the product and validation results to the redirect if validation failed.
     * @return Redirection to the product management page.
     */
    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors())
            productService.save(product);
        else{
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.Product", result);
            redirectAttributes.addFlashAttribute("product", product);
            redirectAttributes.addFlashAttribute("keepInputs", true);
        }

        return "redirect:/admin/products";
    }

    /**
     * Post request to remove product from database by id, if exists.
     * @param id To find product by.
     * @return Redirection to the product management page.
     */
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteIfExists(id);

        return "redirect:/admin/products";
    }

    /**
     * Post request to update a product by id, if exists.
     * If new product is valid and the id exists updates it to the database,
     * otherwise passes the errors back to product management page.
     * @param id Of the to-update product.
     * @param product New values for the updated product.
     * @param result Validation Result for the product.
     * @param redirectAttributes Adds the product and validation results to the redirect if validation failed.
     * @return Redirection to the product management page.
     */
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (productService.exists(id)) {
            if(!result.hasErrors())
                productService.update(product, id);

            else{
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.Product", result);
                redirectAttributes.addFlashAttribute("product", product);
                redirectAttributes.addFlashAttribute("keepInputs", true);
            }
        }
        else
            redirectAttributes.addFlashAttribute("error", "Error: trying to update a non-existent object");

        return "redirect:/admin/products";
    }
}
