package controller;

import facade.impl.ShopFacadeImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import model.Shop;

@ViewScoped
@Named
public class ShopController implements Serializable {
    private static final long serialVersionUID = 6230421169636259169L;

    @Inject
    private ShopFacadeImpl shopFacade;

    @Setter @Getter
    private Shop shop = new Shop();

    @Transactional
    public void createNewShop() {
        shopFacade.addShop(shop);
        shop = new Shop();
    }

    public List<Shop> getAllShops() {
        return shopFacade.getAllShops();
    }
}