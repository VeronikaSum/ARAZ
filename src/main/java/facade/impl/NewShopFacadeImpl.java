package facade.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Shop;

@ApplicationScoped
public class NewShopFacadeImpl{
    @Inject
    private EntityManager em;

    public void addShop(Shop shop) {
        this.em.persist(shop);
    }

    public List<Shop> getAllShops() {
        return this.em.createNamedQuery("Shop.findALl", Shop.class).getResultList();
    }

    public Shop getShopById(Long id) {
        TypedQuery<Shop> namedQuery = this.em.createNamedQuery("Shop.findById", Shop.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }
}