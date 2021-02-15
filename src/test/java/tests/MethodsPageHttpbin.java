package tests;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class MethodsPageHttpbin extends TestBase {

    @Test
    @DisplayName("Тест метода Get проверка ответа в поле headers.Host")
    public void testHttpMethodsGet () {
        given()
                .contentType(ContentType.JSON)
            .when()
                .get(baseURI + "/get")
            .then()
                .assertThat()
                .log().body()
                .body("headers.Host", is("httpbin.org"));
    }

    @Test
    @DisplayName("Проверка возвращения правильного статус кода")
    public void testStatusCodesGet () {

        final int codes = 500;
        given()
                .contentType(ContentType.JSON)
            .when()
                .get(baseURI + "/status/" + codes)
            .then()
                .log().body()
                .statusCode(codes);
    }

    @Test
    @DisplayName("Проверка редиректа на указанный URL и статус код")
    public void testRedirectToGet () {

        final String url = "https://github.com/allure-framework";
        final int status_code = 100;
        given()
                .contentType(ContentType.JSON)
                .header("url", url)
                .and()
                .header("status_code", status_code)
            .when()
                .get(baseURI + "/redirect-to")
            .then()
                .log().body()
                .statusCode(302); //тест упадёт так как код ответа 404 а по документации должен быть 302
    }
}
