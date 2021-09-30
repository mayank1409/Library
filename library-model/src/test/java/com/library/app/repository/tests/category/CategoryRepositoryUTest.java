package com.library.app.repository.tests.category;

import com.library.app.model.Category;
import com.library.app.repository.tests.category.repository.utils.TestDBOperationExecutorService;
import org.junit.Test;

import java.util.List;

import static com.library.app.repository.constants.DBOperations.*;
import static org.junit.Assert.*;

public class CategoryRepositoryUTest {

    private static final TestDBOperationExecutorService dbExecutor = new TestDBOperationExecutorService<Category, Long>(Category.class);;

    @Test
    public void testCretaeCategory() {
        Category category = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.health(), Long.class);
        assertNotNull(category);
    }

    @Test
    public void testUpdateCategory() {
        Category category = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.sports(), Long.class);
        category.setName("Politics");
        Category updatedCategory = (Category) dbExecutor.execute(MERGE, category, Long.class);
        assertEquals("Politics", updatedCategory.getName());
    }

    @Test
    public void testGetCategory() {
        Category category = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.business(), Long.class);
        Category retrivedCategory = (Category) dbExecutor.execute(GET, Category.class, category.getId());
        assertNotNull(retrivedCategory);
    }

    @Test
    public void testListCategory() {
        Category category1 = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.technology(), Long.class);
        Category category2 = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.engineering(), Long.class);
        List<Category> categories = (List<Category>) dbExecutor.execute(LIST, Category.class, Long.class);
        assertNotNull(categories);
        assertTrue(categories.size() > 0);
    }

    @Test
    public void testDeleteCategory() {
        Category category = (Category) dbExecutor.execute(PERSIST, CategoryDataProvoder.education(), Long.class);
        dbExecutor.execute(DELETE, Category.class, category.getId());
        Category retrivedCategory = (Category) dbExecutor.execute(GET, Category.class, category.getId());
        assertNull(retrivedCategory);
    }
}
