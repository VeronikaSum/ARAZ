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
public class CategoryController implements Serializable {
    private static final long serialVersionUID = 7494079547177391842L;

    @Inject
    private CategoryFacadeImpl categoryFacade;

    @Inject
    private ProneNumberGenerator proneNumberGenerator;

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
    //to avoid OptimisticLokException error handling methods should use @Transactional(REQUIRES_NEW)
    public String updateCategory() {
        String redirect = "categories";
        try {
            Category categoryById = categoryFacade.getCategoryById(categoryToEdit.getId());
            categoryById.setName(categoryToEdit.getName());
            Thread.sleep(5000);
            categoryFacade.updateCategory(categoryById);
        } catch (OptimisticLockException | InterruptedException e) {
            e.printStackTrace();
            return "categories?faces-redirect=true&error=optimistic-lock-exception";
        }
        return redirect;
    }

}