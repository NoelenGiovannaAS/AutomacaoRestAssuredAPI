package petstore;

import org.testng.annotations.Test;
import utils.Leitores;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";
    Leitores lt = new Leitores();

    @Test
    public void criarUmUsuario() throws IOException {
        String jsonBody = lt.lerJson("user1.json");
        String userID =
                given()
                        .contentType("application/json").log().all().body(jsonBody)
                .when()
                        .post(uri)
                .then()
                        .log().all()
                        .statusCode(200)
                .extract()
                        .path("message");

        System.out.println("O userID é " + userID );


    }

}
