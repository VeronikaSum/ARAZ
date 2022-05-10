package controller;

import facade.impl.CategoryFacadeImpl;
import interceptor.Logged;
import java.io.Serializable;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import model.Category;

@ViewScoped
@Named
@Getter @Setter
@Logged
public class CategoryController implements Serializable {
    private static final long serialVersionUID = 7494079547177391842L;

    @Inject
    private CategoryFacadeImpl categoryFacade;

    private Category category = new Category();
    private Category categoryToEdit = new Category();

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

    @Transactional
    public String updateCategory() {
        String redirect = "index?faces-redirect=true";
        try {
            categoryFacade.updateCategory(categoryToEdit);
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return redirect + "&error=optimistic-lock-exception";
        }
        return redirect;
    }

}