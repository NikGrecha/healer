package com.health.healer.service;

import com.health.healer.models.RecipeView;
import com.health.healer.repository.RecipeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepositoryImpl recipeRepositoryImpl;

    public List<RecipeView> findRecipeByCardId (int cardId, Connection connection){
        return recipeRepositoryImpl.findRecipeByCardId(cardId, connection);
    }
}
