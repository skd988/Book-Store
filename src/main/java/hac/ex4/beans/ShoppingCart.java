package hac.ex4.beans;

import hac.ex4.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Shopping cart component.
 * Contains and manages items added to a cart by each user.
 */
@Component
public class ShoppingCart {
    /**
     * List of items in the cart.
     */
    private final List<CartItem> items;

    /**
     * Constructor - initiates empty cart.
     */
    public ShoppingCart(){
        items = new ArrayList<CartItem>();
    }

    /**
     * Get items of the cart.
     * @return List of the items in the cart.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Adds item to the cart according to id.
     * If item is already in the cart, increments the quantity of the item.
     * @param id To add to the cart.
     */
    public void addToCart(long id) {
        Optional<CartItem> res = find(id);
        if(res.isPresent())
            res.get().increase();
        else
            items.add(new CartItem(id, 1));
    }

    /**
     * Finds item in the cart by id.
     * @param id to find item by.
     * @return Optional of cartItem (if was not found optional contains null).
     */
    public Optional<CartItem> find(long id){
        return items.stream().filter(item -> item.getId() == id).findAny();
    }

    /**
     * Decreases an item's quantity by id.
     * If item reaches quantity of 0, removes it from the cart.
     * @param id to find item by.
     */
    public void decreaseFromCart(long id) {
        Optional<CartItem> res = find(id);
        if(res.isPresent()){
            CartItem item = res.get();
            item.decrease();
            if(item.getQuantity() <= 0)
                items.remove(item);
        }
    }

    /**
     * Delete item from the cart.
     * @param id id to delete.
     */
    public void delete(long id){
        Optional<CartItem> res = find(id);
        res.ifPresent(items::remove);
    }

    /**
     * Removes all items from the cart.
     */
    public void emptyCart() {
        items.removeAll(items);
    }

    /**
     * Checks if cart is empty.
     * @return Whether cart is empty.
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * Get number of items in the cart (sum of quantities of each item).
     * @return The number of items in the cart.
     */
    public int getNumberOfItems(){
        int num = 0;
        for(CartItem item : items)
            num += item.getQuantity();
        return num;
    }

    /**
     * Presents the current cart items.
     * @return A string presenting the list of cart items.
     */
    @Override
    public String toString() {
        return "ShoppingCart{" + items + "}";
    }
}
