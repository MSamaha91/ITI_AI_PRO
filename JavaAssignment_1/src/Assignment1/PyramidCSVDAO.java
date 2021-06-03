package Assignment1;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class PyramidCSVDAO {

    public Pyramid createPyramid(String[] metadata)
    {
        Pyramid pyramidObj = new Pyramid(metadata[0].trim(), metadata[2].trim(), metadata[4].trim(), metadata[7].trim());
        return pyramidObj;
    }

    public List<Pyramid> readPyramidsFromCSV(String fileName) {
        List<Pyramid> pyramidObjects = new ArrayList<>();

        // access the data CSV file
        File pyramidsInfoFile = new File(fileName);

        // read all data lines in the CSV file, and save them in a list
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(pyramidsInfoFile.toPath());
        } catch (Exception ex) {
            System.out.println("An issue has been happened during reading pyramids info: " + ex);
        }

        // extract all pyramids info, and save them in a list of Strings
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
            String line = lines.get(lineIdx);
            // split line fields using ',' delimiter and save those fields in a list of Strings
            String[] fields = line.split(",");
            // create Pyramid object using pyramid info stored in fields array
            Pyramid pyramidObject = createPyramid(fields);
            pyramidObjects.add(pyramidObject);
        }

        return pyramidObjects;
    }


}
