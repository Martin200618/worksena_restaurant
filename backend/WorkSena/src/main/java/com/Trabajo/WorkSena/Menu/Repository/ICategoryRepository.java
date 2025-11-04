package com.Trabajo.WorkSena.Menu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Trabajo.WorkSena.Menu.Entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findByIsActiveTrue();
    boolean existsByName(String name);
}
