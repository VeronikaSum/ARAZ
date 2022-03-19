package controller;

import facade.impl.CategoryFacadeImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import model.Category;

@ViewScoped
@Named
public class CategoryController implements Serializable {
    private static final long serialVersionUID = 7494079547177391842L;

    @Inject
    private CategoryFacadeImpl categoryFacade;

    @Setter @Getter
    private Category category = new Category();

    @Transactional
    public String createNewCategory() {
        if (!categoryFacade.getAllCategories().contains(category.getName())) {
           categoryFacade.addCategory(category);
           category = new Category();
        }
        return "index?faces-redirect=true";
    }
    public List<Category> getAvailableCategories() {
        return categoryFacade.getAllCategories();
    }

}