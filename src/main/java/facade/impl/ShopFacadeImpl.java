package facade.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Shop;

//Specialization happens at development time as well as at runtime. If you declare that one bean specializes another, it extends the other bean class, and at runtime the specialized bean completely replaces the other bean.
@Specializes //Specialization has a function similar to that of alternatives, in that it allows you to substitute one bean for another.
@ApplicationScoped
public class ShopFacadeImpl extends NewShopFacadeImpl{
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