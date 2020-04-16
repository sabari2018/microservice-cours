package com.microservicecours.dao;

import com.microservicecours.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
