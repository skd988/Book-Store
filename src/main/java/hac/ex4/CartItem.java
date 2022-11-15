package hac.ex4;

public class CartItem {
    /**
     * Id of the item.
     */
    private long id;

    /**
     * Quantity of the item, how many items of the same product in the cart.
     */
    private int quantity;

    /**
     * Constructor - initiates members with received values.
     * @param id id to set by.
     * @param quantity quantity to set by.
     */
    public CartItem(long id, int quantity){
        this.id = id;
        this.quantity = quantity > 0? quantity : 1;
    }

    /**
     * Get id of cart item.
     * @return id of cart item.
     */
    public long getId(){
        return id;
    }

    /**
     * Get quantity of cart item.
     * @return quantity of cart item.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Set new quantity to cart.
     * @param quantity New quantity to set.
     */
    public void setQuantity(int quantity){
        if(quantity > 0)
            this.quantity = quantity;
    }

    /**
     * Increase quantity.
     */
    public void increase(){
        ++quantity;
    }

    /**
     * Decrease quantity.
     */
    public void decrease(){
        --quantity;
    }

    /**
     * Presents the cart item object.
     * @return A string presenting the cart item details.
     */
    @Override
    public String toString() {
        return "CartItem{id=" + id + ", quantity=" + quantity + "}";
    }
}
