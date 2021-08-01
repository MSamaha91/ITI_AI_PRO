import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        long startTime = System.nanoTime();


        CityCSVDAO cityDAO = new CityCSVDAO();
        // read file contains cities info and store them in a list of city objects
        //List<City> cities = cityDAO.readCitiesFromCSV("E:\\ITI\\AI pro\\Java\\cities.csv");
        List<City> cities = cityDAO.readCitiesFromCSV("E:\\ITI\\AI pro\\Java\\cities_full.csv");



        // sort cities objects based on population
        Collections.sort(cities, Collections.reverseOrder());
        Map<String, List<City>> citiesCountryMap = new HashMap<>();

        for(City city: cities) {
            if(!citiesCountryMap.containsKey(city.getCountryCode())) {
                citiesCountryMap.put(city.getCountryCode(), new ArrayList<>());
            }
            citiesCountryMap.get(city.getCountryCode()).add(city);
        }

        citiesCountryMap.forEach((country, citiesList) ->
                System.out.println("Country : " + country + "\n" + "cities :\n" + citiesList));

        /*
        for(Map.Entry<String, List<City>> country: citiesCountryMap.entrySet()){
            System.out.println("===============================");
            System.out.println(country.getKey()+" : ");
            for(City city: country.getValue()) {
                System.out.println("\t" + city);
            }
        }
        System.out.println("===============================");
        */

        long estimatedTime = System.nanoTime() - startTime;
        double seconds = (double)estimatedTime / 1_000_000_000.0;
        System.out.println("Elapsed time = " + seconds);
    }
}
