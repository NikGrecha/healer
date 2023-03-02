package com.health.healer.repository;

import com.health.healer.models.Recipe;

import java.sql.Connection;
import java.util.List;

public interface RecipeRepository {
    List<Recipe> findByCardId(int cardId, Connection connection);
}
