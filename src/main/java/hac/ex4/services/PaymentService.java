package hac.ex4.services;

import hac.ex4.repo.Payment;
import hac.ex4.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access to payment database.
 */
@Service
public class PaymentService {
    /**
     * Repository of payment database.
     */
    @Autowired
    PaymentRepository repository;

    /**
     * Save a new payment to the database.
     * @param payment New payment to add to the database.
     */
    public void save(Payment payment){
        repository.save(payment);
    }

    /**
     * Get all payments ordered by dates of creation.
     * @return List of payments ordered by dates of creation.
     */
    public List<Payment> getAllOrderByDate(){
        return repository.findByOrderByDateTimeAsc();
    }
}
