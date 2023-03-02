package com.health.healer.service;

import com.health.healer.repository.RecipeRepositoryImpl;
import com.health.healer.repository.VisitRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepositoryImpl recipeRepositoryImpl;
}
