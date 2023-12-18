package com.ecom.app.bean;

import com.ecom.app.model.entity.ItemCart;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class CartBeanImpl extends GenericBeanImpl<ItemCart> implements CartBeanI {

     @PersistenceContext
    private EntityManager em;
    private List<ItemCart> cartItems = new ArrayList<>();

    @Override
public List<ItemCart> listCart(Class<ItemCart> entityClass) {
    // Use EntityManager to retrieve managed entities
    return em.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass)
        .getResultList();
}


    @Override
    public void addOrUpdateCartItems(List<ItemCart> newCartItems) {
        for (ItemCart itemCart : newCartItems) {
            // Merge the detached entity to reattach it to the persistence context
            ItemCart mergedItemCart = em.merge(itemCart);
    
            // Now persist the merged entity
            em.persist(mergedItemCart);
        }
    }

    @Override
    public void addOrUpdate(ItemCart itemCart) {
        
        if (itemCart.getQuantity() <= 0) {
            itemCart.setQuantity(1);
        }
        getDao().addOrUpdate(itemCart);
    }

    @Override
    public void addMoreQuantity(Long itemId) {
        ItemCart item = findById(ItemCart.class, itemId);
        if (item != null) {
            Integer currentQuantity = item.getQuantity();
            int newQuantity = (currentQuantity != null) ? currentQuantity + 1 : 1;
            item.setQuantity(newQuantity);
        }
    }
    @Override
    public void reduceQuantity(Long id) {
        // Retrieve the ItemCart entity by id
        ItemCart itemCart = em.find(ItemCart.class, id);

        if (itemCart != null) {
            // Check if the quantity is greater than 1 before reducing
            if (itemCart.getQuantity() > 1) {
                // Reduce the quantity by 1
                itemCart.setQuantity(itemCart.getQuantity() - 1);

                // Merge the changes to the database
                em.merge(itemCart);
            } else {
                // If the quantity is already 1, you may choose to remove the item or handle it differently
                // For this example, let's remove the item from the cart
                em.remove(itemCart);
            }
        }
    }

    @Override
    public void clearCart() {
        // Clear the cart items
        cartItems.clear();
    }
}
