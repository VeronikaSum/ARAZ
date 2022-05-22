package controller;

import facade.impl.CategoryFacadeImpl;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
@Getter
@Setter
public class DetailedCategoryController implements Serializable {
    private static final long serialVersionUID = 8709909428562555018L;
    @Inject
    CategoryFacadeImpl categoryFacade;

    private Category category;
    private String name;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer categoryId = Integer.parseInt(requestParameters.get("categoryId"));
        this.category = categoryFacade.getCategoryById(categoryId.longValue());
    }

    @Transactional
    public String updateCategory() {
        try {
            category.setName(name);

            categoryFacade.updateCategory(category);

        }  catch (OptimisticLockException e) {
            e.printStackTrace();
            return "category.xhtml?faces-redirect=true&categoryId=" + category.getId() + "&error=optimistic-lock-exception";
        }

        return "category.xhtml?categoryId=" + category.getId()  + "&faces-redirect=true";
    }


}