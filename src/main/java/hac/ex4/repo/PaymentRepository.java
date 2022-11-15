package hac.ex4.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Payment repository to access the payment database.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * Order payment database by date time
     * @return List of payments in the database ordered by date.
     */
    List<Payment> findByOrderByDateTimeAsc();

}


