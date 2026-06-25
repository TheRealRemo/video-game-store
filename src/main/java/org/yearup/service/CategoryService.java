package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    //provided field to build constructor to access repository
    private final CategoryRepository categoryRepository;

    // Constructor injection allows Spring to provide the repository dependency.
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Retrieve all categories from the database.
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Find a category by its ID. Return null if no category exists.
    public Category getById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category create(Category category) {

        // Ensure JPA treats this as a new entity and generates an ID.
        category.setCategoryId(null);

        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category) {
        // Retrieve the existing category before applying updates.
        Category newCategory = getById(categoryId);

        // Return null when the category does not exist.
        if (newCategory == null) return null;

        // Copy values from the request body onto the existing entity.
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());

        // Save the modified entity and persist the changes.
        return categoryRepository.save(newCategory);
    }

    public void delete(int categoryId) {
        // Remove the category by its ID.
        categoryRepository.deleteById(categoryId);
    }

}

