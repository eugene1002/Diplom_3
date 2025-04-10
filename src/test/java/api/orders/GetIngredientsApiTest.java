package api.orders;

import api.BaseApiTest;
import controllers.OrderController;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("[API] Получение ингредиентов")
public class GetIngredientsApiTest extends BaseApiTest {

    @Test
    @DisplayName("Получение данных об ингредиентах и проверка их структуры")
    public void getIngredientsTest() {
        Response response = OrderController.getIngredients();

        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("data", notNullValue());

        // Парсинг JSON
        JsonPath json = response.jsonPath();
        List<Map<String, Object>> ingredients = json.getList("data");

        // Проверка каждого ингредиента
        for (Map<String, Object> item : ingredients) {
            assertThat(item, allOf(
                    hasKey("_id"),
                    hasKey("name"),
                    hasKey("type"),
                    hasKey("proteins"),
                    hasKey("fat"),
                    hasKey("carbohydrates"),
                    hasKey("calories"),
                    hasKey("price"),
                    hasKey("image"),
                    hasKey("image_mobile"),
                    hasKey("image_large"),
                    hasKey("__v")
            ));

            assertThat(item.get("_id").toString(), not(emptyString()));
            assertThat(item.get("name").toString(), not(emptyString()));
            assertThat(item.get("type").toString(), not(emptyString()));
            assertThat(item.get("image").toString(), startsWith("http"));
            assertThat(item.get("image_mobile").toString(), startsWith("http"));
            assertThat(item.get("image_large").toString(), startsWith("http"));
        }
    }
}
