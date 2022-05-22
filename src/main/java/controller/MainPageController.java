package controller;

import facade.impl.ItemFacadeImpl;
import interceptor.Logged;
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
    private static final long serialVersionUID = 211464307920397540L;

    @Inject
    private ItemFacadeImpl itemFacade;

    private List<Item> items;

    @PostConstruct
    void init() {
        this.items = itemFacade.getAllItems();
    }
}