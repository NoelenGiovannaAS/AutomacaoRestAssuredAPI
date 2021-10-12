package petstore;

import org.apache.commons.lang3.compare.ObjectToStringComparator;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Provider;
import utils.ReadersData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";
    ReadersData lt = new ReadersData();

    @DataProvider //anotação para executar uma linha de cada vez. Prover dados para os testes
    public Iterator<Object[]> provider() throws IOException {
        Provider provider = new Provider();
        return provider.provider(",","/test/resources/data/csv/users.csv");
    }

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

    @Test(dataProvider = "provider")
    public void criarVariosUsuario(
            String id, String username, String firstname, String lastName, String email,
            String phone, String password, String userStatus
    ) throws IOException {
        String jsonBody = new JSONObject() //criar json na hora
        .put("id", id)
        .put("username", username)
        .put("firstName", firstname)
        .put("lastName",lastName)
        .put("email", email)
        .put("password", password)
        .put("phone", phone)
        .put("userStatus", userStatus)
                .toString();

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
