package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {
    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "https://httpbin.org";
    }
}