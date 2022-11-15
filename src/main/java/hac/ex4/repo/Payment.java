package hac.ex4.repo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * Entity of payment.
 * Adds a table to the sql.
 */
@Entity
public class Payment {

    /**
     * Id of the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Creation date of the payment.
     */
    @CreationTimestamp
    private Date dateTime;

    /**
     * User name who purchased.
     */
    private String userName;

    /**
     * Amount paid.
     */
    @Positive
    private double amount;

    /**
     * Constructor - default. Spring initiates id by generated value and dateTime by creation time.
     */
    public Payment() {

    }

    /**
     * Constructor - initiates amount to pay, Spring initiates id by generated value and dateTime by creation time.
     * @param amount Payment amount.
     */
    public Payment(double amount, String userName) {
        this.amount = amount;
        this.userName = userName;
    }

    /**
     * Sets id of payment.
     * @param id New id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get id of payment.
     * @return id of payment.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets date of payment.
     * @param dateTime New date to set.
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Get date of payment.
     * @return date of payment.
     */
    public Date getDateTime() {
        return this.dateTime;
    }

    /**
     * Sets amount of payment.
     * @param amount New amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get amount of payment.
     * @return amount of payment.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get user name of payment.
     * @return user name of payment.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets user name of payment.
     * @param user name New amount to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Presents the payment object.
     * @return A string presenting the payment details.
     */
    @Override
    public String toString() {
        return "Payment{" + "id=" + this.id + ", datetime=" + this.dateTime + ", amount=" + this.amount + '}';
    }

}

