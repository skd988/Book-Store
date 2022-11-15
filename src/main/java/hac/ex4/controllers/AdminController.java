package hac.ex4.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the landing admin page.
 */
import java.security.Principal;

//@GetMapping("/user/{id}")
//public String getUser(@PathVariable("id") String id) {

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping("/admin")
public class AdminController{
    /**
     * Get request for the landing admin page, contains nav-bar to navigate to the admin operations.
     * @return View of the landing admin page.
     */
    @GetMapping("")
    public String admin(Principal principal) {
        return "admin";
    }


}
