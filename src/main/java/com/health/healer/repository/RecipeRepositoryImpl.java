package com.health.healer.repository;

import com.health.healer.models.Recipe;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public class RecipeRepositoryImpl extends JDBCCustomRepositoryImpl<Recipe, Integer> implements RecipeRepository {
    @Override
    public List<Recipe> findByCardId(int cardId, Connection connection) {
        return null;
    }
}
