package it.asansonne.storybe.service.it;

import static it.cybsec.app.util.DataBuilder.makeTestCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.cybsec.app.IntegrationTest;
import it.cybsec.app.containers.keycloack.ContainersBuilder;
import it.cybsec.app.model.Category;
import it.cybsec.app.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("CategoryService IT Test")
class CategoryServiceImplIT extends ContainersBuilder implements IntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("Find all categories ok")
    void findAllCategoriesOk() {
        Category category = categoryService.createCategory(makeTestCategory());
        List<Category> categories = categoryService.findAllCategories();
        assertEquals(1, categories.size());
        assertEquals(category.getName(), categories.get(0).getName());
    }

    @Test
    @DisplayName("Find all categories not found")
    void findAllCategoriesNotFound() {
        assertThrows(EntityNotFoundException.class,
                () -> categoryService.findAllCategories());

    }

    @Test
    @DisplayName("Find category by name ok")
    void findCategoryByUuid() {
        Category category = categoryService.createCategory(makeTestCategory());
        Category foundCategory = categoryService.findCategoryByUuid(category.getUuid()).orElseThrow(EntityNotFoundException::new);
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test
    @DisplayName("Delete category")
    void deleteCategory() {
        Category category = categoryService.createCategory(makeTestCategory());
        UUID uuid = categoryService.findCategoryByUuid(category.getUuid()).orElseThrow().getUuid();
        categoryService.deleteCategory(uuid);
        assertThrows(EntityNotFoundException.class,
                () -> categoryService.findCategoryByUuid(uuid));
    }

    @Test
    @DisplayName("Update category")
    void updateCategory() {
        Category oldCategory = categoryService.createCategory(makeTestCategory());
        Category newCategory = categoryService.updateCategory(makeTestCategory());
        assertNotEquals(oldCategory.getName(), newCategory.getName());
    }

    @Test
    @DisplayName("Find category by name containing ignore case oK")
    void findCategoryByNameContainingIgnoreCaseOK() {
        Category category = categoryService.createCategory(makeTestCategory());
        List<Category> foundCategory = categoryService.findCategoryByNameContainingIgnoreCase("TeSt CATEgory");
        assertEquals(category.getName(), foundCategory.get(0).getName());
    }
}