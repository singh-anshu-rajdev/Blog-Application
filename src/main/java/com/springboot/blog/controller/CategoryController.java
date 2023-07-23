package com.springboot.blog.controller;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Build add category RestApi
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("/createCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    //Build get category RestApi
    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDto> geyCategoryById(@PathVariable("id") long id){
        return new ResponseEntity<>(categoryService.getCategory(id),HttpStatus.OK);
    }

    //Build get All categories RESTApi
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    //Build updated Category RestApi
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/UpdateById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategoryById(@RequestBody CategoryDto categoryDto,@PathVariable("id") long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto,categoryId));
    }

    //Build delete category RESTAPI
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted Sucesfully",HttpStatus.OK);
    }

}
