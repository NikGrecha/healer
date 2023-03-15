package com.health.healer.repository;

import com.health.healer.models.Recipe;
import com.health.healer.models.RecipeView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeRepositoryImpl extends JDBCCustomRepositoryImpl<Recipe, Integer> implements RecipeRepository {
    @Override
    public List<RecipeView> findRecipeByCardId(int cardId, Connection connection) {
        List<RecipeView> recipeList = new ArrayList<>();
        String query = """
                SELECT * FROM recipe_view
                WHERE card_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                RecipeView recipe = new RecipeView();

                recipe.setId(resultSet.getInt("id"));
                recipe.setVisitId(resultSet.getInt("visit_id"));
                recipe.setDrugName(resultSet.getString("drug_name"));
                recipe.setLength(resultSet.getInt("length"));
                recipe.setFrequency(resultSet.getString("frequency"));
                recipe.setDopInfo(resultSet.getString("dop_info"));
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}
