package controller;

import facade.impl.CategoryFacadeImpl;
import facade.impl.ItemFacadeImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import model.Item;

@ViewScoped
@Named
@Getter
@Setter
public class ItemController implements Serializable {
    private static final long serialVersionUID = 5550441667169227679L;

    @Inject
    private ItemFacadeImpl itemFacade;
    @Inject
    private CategoryFacadeImpl categoryFacade;

    private Item item = new Item();
    private Long categoryId;
    private List<Long> shopIds;

    @Transactional
    public String createItem() {
        itemFacade.addItem(item, categoryId, shopIds);
        item = new Item();
        return "index?faces-redirect=true";
    }
}