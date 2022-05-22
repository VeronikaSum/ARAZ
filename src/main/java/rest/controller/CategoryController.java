package rest.controller;

import facade.impl.CategoryFacadeImpl;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Category;
import model.Item;
import rest.contracts.CategoryDto;

@ApplicationScoped
@Path("/category")
public class CategoryController {

    @Inject
    private CategoryFacadeImpl categoryFacade;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Category category = categoryFacade.getCategoryById(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setItemsNames(category.getItems().stream().map(Item::getName).collect(Collectors.toList()));

        return Response.ok(categoryDto).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName());
        categoryFacade.addCategory(category);
        return Response.ok().build();
    }


    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    //to avoid OptimisticLokException error handling methods should use @Transactional(REQUIRES_NEW)
    public Response update(
            @PathParam("id") final Long categoryId,
            CategoryDto categoryDto) {
        try {
            Category existingCategory = categoryFacade.getCategoryById(categoryId);
            if (existingCategory == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCategory.setName(categoryDto.getName());
            Thread.sleep(2000);
            categoryFacade.updateCategory(existingCategory);
            return Response.ok().build();
        } catch (OptimisticLockException | InterruptedException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}