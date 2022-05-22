package facade.impl;

import interceptor.Logged;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import model.Shop;

//Specialization happens at development time as well as at runtime. If you declare that one bean specializes another, it extends the other bean class, and at runtime the specialized bean completely replaces the other bean.
@Specializes //Specialization has a function similar to that of alternatives, in that it allows you to substitute one bean for another.
@ApplicationScoped
@Logged
public class ShopFacadeImpl extends NewShopFacadeImpl {

    public void addShop(Shop shop) {
        super.addShop(shop);
    }

    public List<Shop> getAllShops() {
        return super.getAllShops();
    }

    public Shop getShopById(Long id) {
        return super.getShopById(id);
    }
}