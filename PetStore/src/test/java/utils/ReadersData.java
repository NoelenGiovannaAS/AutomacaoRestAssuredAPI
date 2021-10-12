package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class ReadersData {
    String pathJson = "src/test/resources/data/";
    String pathCSV = "src/test/resources/data/csv/";
    public String lerJson(String json) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathJson + json))); //vai ler json e transformo em texto
    }

    public List<String[]> lerCSV(String csv) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(pathCSV + csv)); //lê texto
        CSVReader csvreader = new CSVReaderBuilder(reader).withSkipLines(1).build(); //abre como um csv
        List<String[]> users = csvreader.readAll(); //le todos os dados do cvs
        return users;
    }

}
