package utils;

import models.CreateOrder;
import models.CreateUser;
import models.LoginUser;
import net.datafaker.Faker;

import java.util.List;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    public static CreateUser getValidUser() {
        return new CreateUser(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                faker.name().firstName()
        );
    }

    public static LoginUser getLoginFrom(CreateUser user) {
        return new LoginUser(user.getEmail(), user.getPassword());
    }

    public static CreateUser getUserWithoutEmail() {
        return new CreateUser(
                "",
                faker.internet().password(8, 16),
                faker.name().firstName()
        );
    }

    public static CreateUser getUserWithoutPassword() {
        return new CreateUser(
                faker.internet().emailAddress(),
                "",
                faker.name().firstName()
        );
    }

    public static CreateUser getUserWithoutName() {
        return new CreateUser(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                ""
        );
    }

    /**
     * Валидный заказ с булкой и соусом
     */
    public static CreateOrder validOrderWithBunAndSauce() {
        String bun = IngredientsProvider.getFirstIngredientIdByType("bun");
        String sauce = IngredientsProvider.getFirstIngredientIdByType("sauce");
        return new CreateOrder(List.of(bun, sauce));

    }

    /**
     * Валидный заказ с любыми двумя ингредиентами
     */
    public static CreateOrder validOrderWithAnyIngredients() {
        String id1 = IngredientsProvider.getAllIngredientIds().get(0);
        String id2 = IngredientsProvider.getAllIngredientIds().get(1);
        return new CreateOrder(List.of(id1, id2));
    }

    /**
     * Невалидный заказ — без ингредиентов
     */
    public static CreateOrder invalidOrderWithoutIngredients() {
        return new CreateOrder(List.of());
    }

    /**
     * Невалидный заказ — с несуществующим id
     */
    public static CreateOrder invalidOrderWithWrongIngredients() {
        return new CreateOrder(List.of("wrong-id-1", "wrong-id-2"));
    }
}
