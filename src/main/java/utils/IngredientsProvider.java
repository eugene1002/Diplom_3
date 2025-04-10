package utils;

import controllers.OrderController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IngredientsProvider {

    private static List<Map<String, Object>> ingredientsCache;

    /**
     * Получение и кеширование всех ингредиентов с сервера
     */
    public static List<Map<String, Object>> getAllIngredients() {
        if (ingredientsCache == null) {
            Response response = OrderController.getIngredients();
            JsonPath json = response.jsonPath();
            ingredientsCache = json.getList("data");
        }
        return ingredientsCache;
    }

    /**
     * Получить список всех id ингредиентов
     */
    public static List<String> getAllIngredientIds() {
        return getAllIngredients().stream()
                .map(ingredient -> ingredient.get("_id").toString())
                .collect(Collectors.toList());
    }

    /**
     * Получить id ингредиента по типу (например, bun, sauce, main)
     */
    public static String getFirstIngredientIdByType(String type) {
        return getAllIngredients().stream()
                .filter(ingredient -> type.equals(ingredient.get("type")))
                .map(ingredient -> ingredient.get("_id").toString())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не найден ингредиент типа: " + type));
    }
}
