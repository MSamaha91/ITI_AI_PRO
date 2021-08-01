import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CityCSVDAO {
    // City(String name, String countryCode, String continent, String population)
    private static final int nameIDX = 1;
    private static final int countryCodeIDX = 6;
    private static final int capitalIDX = 8;
    private static final int populationIDX = 9;

    public City createCity(String[] metadata)
    {
        City cityObj = new City(metadata[nameIDX].trim(), metadata[countryCodeIDX].trim(),
                                metadata[capitalIDX].trim(), metadata[populationIDX].trim());
        return cityObj;
    }

    public List<City> readCitiesFromCSV(String fileName) {
        List<City> cityObjects = new ArrayList<>();
        // access the data CSV file
        File citiesInfoFile = new File(fileName);

        // read all data lines in the CSV file, and save them in a list
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(citiesInfoFile.toPath());
        } catch (Exception ex) {
            System.out.println("An issue has been happened during reading cities info: " + ex);
        }

        // extract all cities info, and save them in a list of Strings
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
            String line = lines.get(lineIdx);
            // split line fields using ',' delimiter and save those fields in a list of Strings
            String[] fields = line.split(",");
            // create City object using city info stored in fields array
            City cityObject = createCity(fields);
            cityObjects.add(cityObject);
        }

        return cityObjects;
    }
}
