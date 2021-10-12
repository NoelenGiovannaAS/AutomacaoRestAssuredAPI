package datadriven;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserDD{
    String uri = "https://petstore.swagger.io/v2/user";

    @Test
    public void criarUsuarios(){
        String jsonBody = "";
        String userID =
                given()
                        .contentType("application/json").log().all().body(jsonBody)
                .when()
                        .post(uri)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is("200"))
                .extract()
                        .path("message");

        System.out.println("O userID é " + userID );
    }

}
