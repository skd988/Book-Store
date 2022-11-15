package hac.ex4.repo;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity of product.
 * Adds a table to the sql.
 */
@Entity
public class Product {
    /**
     * Default image, if no image is provided.
     */
    private static final String DEFAULT_IMAGE = "images/no-cover.jpg";

    /**
     * Id of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of the product.
     */
    @Pattern(regexp = "^(?!\\s*$).+", message = "Required")
    @Nullable
    private String name;

    /**
     * Image url of the product.
     */
    @Nullable
    private String image;

    /**
     * Quantity of the product.
     */
    @PositiveOrZero(message = "Must be a non-negative integer")
    @NotNull(message = "Must be a non-negative integer")
    private Integer quantity;

    /**
     * Price of the product.
     */
    @Positive(message = "Must be a positive number")
    @NotNull(message = "Must be a positive number")
    private Double price;

    /**
     * Discount of the product's price.
     */
    @PositiveOrZero(message = "Must be non-negative number")
    @NotNull(message = "Must be a non-negative number")
    @Max(value = 100, message = "Must be less than or equal to 100")
    private Double discount;

    /**
     * Constructor - initiates members to null, Spring initiates id by generated value.
     */
    public Product() {
        name = null;
        image = null;
    }

    /**
     * Constructor - initiates members according to received values, Spring initiates id by generated value.
     * @param name Name of the product.
     * @param image Image url of the product.
     * @param quantity Quantity of the product.
     * @param price Price of the product.
     * @param discount Discount of the product's price.
     */
    public Product(String name, String image, int quantity, double price, double discount){
        this.name = name.trim();
        this.image = image.trim();
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Sets id of product.
     * @param id New id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get id of product.
     * @return id of product.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets name of product.
     * @param name New name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get name of product.
     * @return Name of product.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets image of product.
     * @param image New image url to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get image of product.
     * @return Image of product.
     */
    public String getImage() {
        return image == null || image.isBlank()? DEFAULT_IMAGE : image;
    }

    /**
     * Sets quantity of product.
     * @param quantity New quantity to set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Get quantity of product.
     * @return Quantity of product.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets price of product.
     * @param price New price to set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Get price of product.
     * @return Price of product.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets discount of product.
     * @param discount New discount to set.
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Get discount of product.
     * @return Discount of product.
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * Get the discounted price of product.
     * @return Discounted price of product.
     */
    public Double getDiscountedPrice() {
        return price - price * discount / 100;
    }

    /**
     * Presents the product object.
     * @return A string presenting the product details.
     */
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", image=" + image + ", quantity=" + quantity + ", price=" +
                price + ", discount=" + discount + '}';
    }
}
