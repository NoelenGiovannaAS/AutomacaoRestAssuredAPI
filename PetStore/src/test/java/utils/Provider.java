package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Provider {

    public java.util.Iterator<Object[]> provider(String caracterSeparador, String arquivo) throws IOException { //iterator - ordena objetos
        List<Object[]> testCases = new ArrayList<>(); //guarda todos
        String[] testCase = null; //guarda uma linha de cada vez para testar.
        String linha;
        BufferedReader bufferedReader = new BufferedReader( new FileReader(arquivo));
        while((linha = bufferedReader.readLine()) != null){
            testCase = linha.split(caracterSeparador);
            testCases.add(testCase);
        }
        return testCases.iterator();
    }
}
