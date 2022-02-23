package facade.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import model.Item;

@ApplicationScoped
public class ItemFacadeImpl {
    @Inject
    private EntityManager em;

    public void addItem(String name, int quantity, double price) {
        Item newItem = new Item(name, quantity, price);
        em.persist(newItem);
    }

    public List<Item> getAllItems() {
        return em.createNamedQuery("Item.findALl", Item.class).getResultList();
    }
}