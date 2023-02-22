package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pl.bdygasinski.model.Client;
import pl.bdygasinski.model.submodel.Address;

import java.time.LocalDate;
import java.time.Month;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientRestTest {
    private static final String URL = "http://localhost:8080/Cinema-1.0-SNAPSHOT/api/front/v1/clients";

    static private Client client;


    @BeforeAll
    static void init() {
        client = new Client("john123", "password123", "John", "Doe",
                new Address("England", "London", "River", 3),
                LocalDate.of(1999, Month.AUGUST, 9), "111222333");

    }

    @Test
    @Order(1)
    void getAll_shouldReturnEmptyListWithStatusCode200() {
        RestAssured.when().get(URL)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    @Order(2)
    void getById_shouldReturnExceptionMessageWithStatusCode404() {
        RestAssured.when()
                .get(URL + "/1")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(3)
    void deleteById_shouldReturnExceptionMessageWithStatusCode404() {
        RestAssured.when()
                .delete(URL + "/1")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(4)
    void update_shouldReturnExceptionMessageWithStatusCode404() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{}")
                .put(URL + "/1")
                .then()
                .statusCode(404);
    }
}
