package facade.impl;

import controller.ProneNumberGenerator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import model.Shop;

@Alternative
@ApplicationScoped
public class AlternativeShopFacade extends ShopFacadeImpl {
    @Inject
    private EntityManager em;
    @Inject
    private ProneNumberGenerator proneNumberGenerator;

    public void addShop(Shop shop) {
        shop.setNumber(proneNumberGenerator.generatePhoneNumber());
        em.persist(shop);
    }
}