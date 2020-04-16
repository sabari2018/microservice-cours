package com.microservicecours.web.controller;

import com.microservicecours.dao.CategoryDao;
import com.microservicecours.model.Category;
import com.microservicecours.web.exception.AddCategoryException;
import com.microservicecours.web.exception.CategoryNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Gestion des Categories de cours" )
@RestController
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @ApiOperation(value = "Recuperer la liste de categories")
    @GetMapping(value="/categories")
    public List<Category> getCategories(){

        List<Category> categories = categoryDao.findAll();

        if (categories.isEmpty()) throw new CategoryNotFoundException("Aucune categorie de cours present dans la base");

        return categories;
    }

    @ApiOperation(value = "Recuperer une categorie selon son ID")
    @GetMapping(value = "/categories/{id}")
    public Optional<Category> getCategory(@PathVariable int id){

        Optional<Category> category = categoryDao.findById(id);
        if (category.isEmpty()) throw new CategoryNotFoundException("La categorie avec l'ID " + id + " n'existe pas");

        return category;

    }

    @ApiOperation(value = "Ajouter une categorie")
    @PostMapping(value = "/categories")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        Category newCategory = categoryDao.save(category);

        if (newCategory == null) throw new AddCategoryException("Impossible d'ajouter cette Category");

        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Editer une categorie")
    @PutMapping(value = "categories")
    public void updateCategory(@RequestBody Category category){
        categoryDao.save(category);
    }
}
