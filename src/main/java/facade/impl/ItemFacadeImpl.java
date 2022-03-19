package facade.impl;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import model.Category;
import model.Item;
import model.Shop;

@ApplicationScoped
public class ItemFacadeImpl {
    @Inject
    private EntityManager em;
    @Inject
    private CategoryFacadeImpl categoryFacade;
    @Inject
    private ShopFacadeImpl shopFacade;

    public void addItem(Item item, Long categoryId, List<Long> shopIds) {
        Item newItem = buildItem(item, categoryId, shopIds);
        this.em.persist(newItem);
    }

    public Item buildItem(Item item, Long categoryId, List<Long> shopIds) {
        Category selectedCategory = categoryFacade.getCategoryById(categoryId);
        item.setCategory(selectedCategory);
        List<Shop> selectedShops = new ArrayList<>();
        for (Long id : shopIds) {
            selectedShops.add(shopFacade.getShopById(id));
        }
        if (!selectedShops.isEmpty()) {
            item.setShops(selectedShops);
        }
        return item;
    }

    public List<Item> getAllItems() {
        return this.em.createNamedQuery("Item.findALl", Item.class).getResultList();
    }
}