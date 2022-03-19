package mybatis.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.CategoryMapper;
import mybatis.model.Category;

@Model
@Getter
@Setter
public class MyBatisCategoryController implements Serializable {
    private static final long serialVersionUID = -8485790918752453661L;

    @Inject
    private CategoryMapper categoryMapper;

    private Long categoryToDeleteId;
    private Category categoryToAdd = new Category();
    private List<Category> availableCategories;


    @PostConstruct
    void init() {
        this.availableCategories = categoryMapper.selectAll();
    }

    @Transactional
    public String deleteCategory() {
        categoryMapper.deleteByPrimaryKey(categoryToDeleteId);
        return "/myBatis/araz?faces-redirect=true";
    }

    @Transactional
    public String addNewCategory() {
        categoryMapper.insert(categoryToAdd);
        return "/myBatis/araz?faces-redirect=true";
    }
}