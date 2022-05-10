package facade.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import model.Category;
import model.Item;

@ApplicationScoped
public class CategoryFacadeImpl implements facade.impl.Category {
    @Inject
    private EntityManager em;

    public void addCategory(Category category) {
        this.em.persist(category);
    }

    public List<Category> getAllCategories() {
        return this.em.createNamedQuery("Category.findALl", Category.class).getResultList();
    }

    public Category getCategoryById(Long id) {
        TypedQuery<Category> namedQuery = this.em.createNamedQuery("Category.getById", Category.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    public void updateCategory(Category categoryToEdit) {
        Category categoryById = getCategoryById(categoryToEdit.getId());
        categoryToEdit.setVersion(categoryById.getVersion());
        this.em.merge(categoryToEdit);
    }
}