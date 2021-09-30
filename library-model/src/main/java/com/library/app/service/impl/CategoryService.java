package com.library.app.service.impl;

import com.library.app.model.Category;
import com.library.app.repository.constants.DBOperations;
import com.library.app.repository.utils.DBOperationExecutorService;
import com.library.app.service.GenericService;

import java.util.List;

public class CategoryService implements GenericService<Category, Long> {

    private DBOperationExecutorService dbExecutor;

    public CategoryService(DBOperationExecutorService dbExecutor) {
        this.dbExecutor = new DBOperationExecutorService<Category, Long>(Category.class);
    }

    @Override
    public Category save(Category category) {
        Category savedCategory = (Category) dbExecutor.execute(DBOperations.PERSIST, category, Long.class);
        return savedCategory;
    }

    @Override
    public Category update(Category category) {
        Category updatedCategory = (Category) dbExecutor.execute(DBOperations.MERGE, category, Long.class);
        return updatedCategory;
    }

    @Override
    public Category get(Long id) {
        Category category = (Category) dbExecutor.execute(DBOperations.GET, Category.class, id);
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = (List<Category>) dbExecutor.execute(DBOperations.LIST, Category.class, Long.class);
        return categories;
    }

    @Override
    public void delete(Long id) {
        dbExecutor.execute(DBOperations.DELETE, Category.class, id);
    }
}
