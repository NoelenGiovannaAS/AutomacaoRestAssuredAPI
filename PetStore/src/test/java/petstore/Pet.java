package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
    //swagger padrão de estruturação de API. Nesse caso o petstore.swagger.io

    String uri = "https://petstore.swagger.io/v2/pet/";   //base url

    public String lerJson(String json) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/data/" + json))); //vai ler json e transformo em texto
    }

    // Incluir - Create - Post
    @Test
    public void _01_incluirPet() throws IOException {
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
                .body("name", is("NeneJr")) //verificar no corpo se tem o nome indicado
                .body("status", is("available"))
                .body("category.name", is("Dog"))
                .body("tags.name", contains("sta")) //tem vários [, lista, no body.
                .body(allOf()).extract()
        ;
        //Na maioria dos casos, o 200 significa que a comunicação deu certo, apenas, q o envio e o recebimento deu certo.
        //Necessário testar o corpo do json

    }
    @Test
    public void _02_consultarPet() {
        String petId = "99887766554400";//qual animal que eu vou procurar?
        String token =
                given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri  + petId)
        .then()
                .statusCode(200)
                .log().all()
                .body("name", is("NeneJr"))
                .body("category.name",is("Dog"))
                .body("status",is("available"))
        .extract()
            .path("category.name")
        ;
        System.out.println("O token é" + token);

    }
}