package com.health.healer.repository;

import com.health.healer.models.Recipe;
import com.health.healer.models.RecipeView;

import java.sql.Connection;
import java.util.List;

public interface RecipeRepository {
    List<RecipeView> findRecipeByCardId(int cardId, Connection connection);
}
