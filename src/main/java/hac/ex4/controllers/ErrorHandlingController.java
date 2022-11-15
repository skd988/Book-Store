package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Controller to handle error requests.
 */
@Controller
public class ErrorHandlingController implements ErrorController {

    /**
     * Error image to present in the case of 404 error code.
     */
    private static final String ERROR_404_IMG = "images/error404.jpg";

    /**
     * Error image to present in the case of 403 error code.
     */
    private static final String ERROR_403_IMG = "images/error403.png";

    /**
     * Shopping cart of the user, in session scope.
     * To get number of items in the nav-bar.
     */
    @Resource(name = "shoppingCartInit")
    private ShoppingCart userShoppingCart;

    /**
     * Request for error page, presents the user with an error page with the customer nav bar available,
     * with details of the error if it was caused by 404 or 403.
     * @param model Model to add error details to
     * @param request Request that failed (to get the status code).
     * @return View of the error page.
     */
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request, Principal principal) {
        if(principal != null)
            model.addAttribute("userName", principal.getName());

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "The page requested does not exist. Error code: " + statusCode);
                model.addAttribute("errorImg", ERROR_404_IMG);
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMessage", "Access Denied. You do not have permissions. Error code: " + statusCode);
                model.addAttribute("errorImg", ERROR_403_IMG);
            }
            else {
                System.out.println(statusCode);
            }
            model.addAttribute("numberOfItems", userShoppingCart.getNumberOfItems());
        }

        return "/error";
    }
}
