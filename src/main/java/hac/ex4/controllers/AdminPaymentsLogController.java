package hac.ex4.controllers;

import hac.ex4.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the admin payments log.
 */
@Controller
@RequestMapping("/admin/paymentslog")
public class AdminPaymentsLogController {
    /**
     * Service to access the payments' database.
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * Get request for the payments log page, contains the admin nav bar and the list of payments made to the website.
     * @param model Model to add payments data to the view.
     * @return View of the payments log page.
     */
    @GetMapping("")
    public String paymentslog (Model model){
        model.addAttribute("payments", paymentService.getAllOrderByDate());
        return "paymentslog";
    }
}
