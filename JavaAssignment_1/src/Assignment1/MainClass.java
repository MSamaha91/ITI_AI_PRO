package Assignment1;
import java.util.*;

public class MainClass {


    public static void main(String args[]) {
        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readPyramidsFromCSV("E:\\ITI\\AI pro\\Java\\pyramids.csv");

        // print all the pyramids read from CSV file
        int i = 1;
        for(Pyramid p: pyramids)
        {
            System.out.println("#" + (i++) + " " + p);
        }
    }

}
