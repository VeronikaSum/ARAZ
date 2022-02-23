package controller;

import facade.impl.ItemFacadeImpl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import model.Item;

@ViewScoped
@Named
@Getter
@Setter
public class MainPageController implements Serializable {
    @Inject
    private ItemFacadeImpl itemFacade;

    private List<Item> items;

    @PostConstruct
    void init() {
        this.items = itemFacade.getAllItems();
    }
}