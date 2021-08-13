package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {
    //swagger padrão de estruturação de API. Nesse caso o petstore.swagger.io

    String uri = "https://petstore.swagger.io/v2/pet/";   //base url

    public String lerJson(String json) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/data/" + json))); //vai ler json e transformo em texto
    }

    // Incluir - Create - Post
    @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("pet.json");


        //Sintaxe Gherkin
        given()
            .contentType("application/json") //tipo do conteúdo do Json, comum em API REST - antigas era "text/xml"
            .log().all() // log da preparação do envio
            .body(jsonBody) //qual o corpo da mensgaem, o que eu quero mandar
        .when()
            .post(uri)
        .then()
            .log().all() //para registrar a volta, resposta da requisição
            .statusCode(200) //verificar se a transação deu ok
        ;
    }
}
