package hac.ex4.controllers;

import hac.ex4.beans.SearchResults;
import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Payment;
import hac.ex4.repo.Product;
import hac.ex4.services.PaymentService;
import hac.ex4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for customer pages.
 */
@Controller
@RequestMapping("/")
public class CustomerController {
    /**
     * Number of discounted item to present in the landing page.
     */
    final int NUM_OF_DISCOUNT_ITEMS = 5;

    /**
     * Shopping cart of the user, in session scope.
     */
    @Resource(name = "shoppingCartInit")
    private ShoppingCart userShoppingCart;

    /**
     * Search results of the user, in session scope.
     */
    @Resource(name = "searchResultsInit")
    private SearchResults searchResults;

    /**
     * Service to access the products' database.
     */
    @Autowired
    private ProductService productService;

    /**
     * Service to access the payments' database.
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * Get request to the main store page, presents the customer nav-bar,
     * allows searching products by name,
     * presents to the user top discounted products,
     * and allows adding products to cart.
     * @param model Model to add data to the view.
     * @return View of the main store page.
     */
    @GetMapping("")
    public String welcomePage(Model model, Principal principal) {
        if(principal != null)
            model.addAttribute("userName", principal.getName());
        model.addAttribute("isCartDisplayMode", false);
        model.addAttribute("cartNumberOfItems", userShoppingCart.getNumberOfItems());
        model.addAttribute("products", productService.findTopDiscounts(NUM_OF_DISCOUNT_ITEMS));
        model.addAttribute("numberOfItems", userShoppingCart.getNumberOfItems());
        model.addAttribute("searchResults", searchResults.getResults());
        return "welcomePage";
    }

    /**
     * Get request to search products database by name
     * @param searchInp Name to search products by.
     * @return Redirection to main store page.
     */
    @GetMapping("search")
    public String performSearch(@RequestParam String searchInp) {
        searchResults.search(searchInp);
        System.out.println("after search");
        return "redirect:/";
    }

    /**
     * Get request to reset search results.
     * @return Redirection to the main store page.
     */
    @GetMapping("resetSearch")
    public String resetSearch(){
        searchResults.reset();
        return "redirect:/";
    }

    /**
     * Get request of shopping cart page, presents the items in the cart,
     * allows emptying the cart, changing quantity of items, removing items, and checking out.
     * @param model Model to add data to the view.
     * @return View of the shopping cart page.
     */
    @GetMapping("cart")
    public String shoppingCart(Model model) {
        model.addAttribute("productService", productService);
        model.addAttribute("cart", userShoppingCart.getItems());
        model.addAttribute("isCartDisplayMode", true);
        model.addAttribute("numberOfItems", userShoppingCart.getNumberOfItems());
        model.addAttribute("toPay", productService.calculatePayment(userShoppingCart));
        return "shoppingCart";
    }

    /**
     * Post request to add item to cart.
     * @param id id to add to cart.
     * @return Redirection to main store page.
     */
    @PostMapping("addtocart/{id}")
    public String addToCart(@PathVariable long id) {
        userShoppingCart.addToCart(id);
        return "redirect:/";
    }

    /**
     * Post request to increase item quantity in cart.
     * @param id id to add to cart.
     * @return Redirection to cart page.
     */
    @PostMapping("increase/{id}")
    public String increase(@PathVariable long id) {
        userShoppingCart.addToCart(id);
        return "redirect:/cart";
    }

    /**
     * Post request to decrease an item's quantity from the cart.
     * @param id id to decrease the item's quantity by to cart.
     * @return Redirection to cart page.
     */
    @PostMapping("decrease/{id}")
    public String decrease(@PathVariable long id) {
        userShoppingCart.decreaseFromCart(id);
        return "redirect:/cart";
    }

    /**
     * Post request to delete an item from the cart.
     * @param id id to delete from cart.
     * @return Redirection to cart page.
     */
    @PostMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        userShoppingCart.delete(id);
        return "redirect:/cart";
    }

    /**
     * Post request to empty the cart.
     * @return Redirection to the cart page.
     */
    @PostMapping("empty")
    public String empty() {
        userShoppingCart.emptyCart();
        return "redirect:/cart";
    }

    /**
     * Get request to perform purchase of the shopping cart's content.
     * If succeeds make appropriate changes in the database and adds new payment to the payment database,
     * empties the cart and redirects to main store page.
     * otherwise redirects back to cart page with error message.
     * @param redirectAttributes To pass success or fail messages.
     * @param principal To access user nam.e
     * @return Redirects to cart page if purchase failed, to main store page otherwise
     */
    @GetMapping("pay")
    public String pay(RedirectAttributes redirectAttributes, Principal principal) {
        try{
            double toPay = productService.purchaseCart(userShoppingCart);
            if(toPay > 0)
                paymentService.save(new Payment(toPay, principal.getName()));

            userShoppingCart.emptyCart();
            redirectAttributes.addFlashAttribute("payment_success", true);
        }
        catch(Exception exception){
            redirectAttributes.addFlashAttribute("payment_failed", true);
            return ("redirect:/cart");
        }

        return ("redirect:/");
    }
}
