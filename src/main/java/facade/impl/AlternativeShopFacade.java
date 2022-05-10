package facade.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import model.Shop;

@Alternative //For example, you might want to create a full version of a bean and also a simpler version that you use only for certain kinds of testing.
@ApplicationScoped
public class AlternativeShopFacade {
    @Inject
    private EntityManager em;

    public void addShop(Shop shop) {
        this.em.persist(shop);
    }

}