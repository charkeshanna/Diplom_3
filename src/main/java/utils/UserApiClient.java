package utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {


     //Регистрирую нового пользователя через API
    @Step("Создание пользователя через API: {user.email}")
    public static String registerUser(User user) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(EnvData.BASE_URL + "/api/auth/register")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getString("accessToken");
    }

    //Удаляю пользователя через API
    @Step("Удаление пользователя через API")
    public static void deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", accessToken)
                    .when()
                    .delete(EnvData.BASE_URL + "/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

}
