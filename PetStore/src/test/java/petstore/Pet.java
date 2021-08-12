package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pet {
    //swagger padrão de estruturação de API. Nesse caso o petstore.swagger.io

    String uri = "https://petstore.swagger.io/v2/pet/";   //base url
    public String lerJson(String json) throws IOException {

        return new String(Files.readAllBytes(Paths.get("src/test/resources/data/" + json))); //vai ler json e transformo em texto
    }
    @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("pet.json");
    }

}
