package facade.impl;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class CategoryWithLongNameCreation implements Category {

    @Inject
    @Delegate
    @Any
    Category categories;

    @Override
    public void addCategory(model.Category categoryToCreate) {
        categories.addCategory(categoryToCreate);

        if (categoryToCreate.getName().length() > 7) {
            System.out.println("Category with very long name has been created: " + categoryToCreate.getName());
        }
    }

}