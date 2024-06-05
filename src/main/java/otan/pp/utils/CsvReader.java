package otan.pp.utils;

import otan.pp.priklad.Operator;
import otan.pp.priklad.Priklad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    public List<Priklad> loadCsv(String fileName){
        try {
            File csvFile = new File(fileName);
            Scanner scanner = new Scanner(csvFile);

            List<Priklad> priklady = new ArrayList<>();
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                // 0 = a, 1 = operator, 2 = b, 3 = =, 4 = vysledek
                String[] values = data.split(" ");
                priklady.add(new Priklad(Integer.parseInt(values[0]), Operator.fromString(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[4])));
            }

            scanner.close();
            return priklady;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
