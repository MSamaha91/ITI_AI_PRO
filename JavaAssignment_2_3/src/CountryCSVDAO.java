import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CountryCSVDAO {
    private static final int codeIDX = 0;
    private static final int nameIDX = 1;
    private static final int continentIDX = 2;

    public Country createCountry(String[] metadata)
    {
        Country countryObj = new Country(metadata[codeIDX].trim(), metadata[nameIDX].trim(), metadata[continentIDX].trim());
        return countryObj;
    }

    public List<Country> readCountriesFromCSV(String fileName) {
        List<Country> countryObjects = new ArrayList<>();
        // access the data CSV file
        File countriesInfoFile = new File(fileName);

        // read all data lines in the CSV file, and save them in a list
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(countriesInfoFile.toPath());
        } catch (Exception ex) {
            System.out.println("An issue has been happened during reading countries info: " + ex);
        }

        // extract all countries info, and save them in a list of Strings
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
            String line = lines.get(lineIdx);
            // split line fields using ',' delimiter and save those fields in a list of Strings
            String[] fields = line.split(",");
            // create Country object using country info stored in fields array
            Country countryObject = createCountry(fields);
            countryObjects.add(countryObject);
        }

        return countryObjects;
    }
}
