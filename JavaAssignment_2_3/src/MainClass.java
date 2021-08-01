import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args) throws IOException {
        MainClass main = new MainClass();
        CountryCSVDAO countryDAO = new CountryCSVDAO();
        CityCSVDAO cityDAO = new CityCSVDAO();
        //List<Country> countries = countryDAO.readCountriesFromCSV("E:\\ITI\\AI pro\\Java\\countries.csv");
        List<Country> countries = countryDAO.readCountriesFromCSV("src/resources/countries.csv");
        //List<City> cities = cityDAO.readCitiesFromCSV("E:\\ITI\\AI pro\\Java\\cities_full.csv");
        List<City> cities = cityDAO.readCitiesFromCSV("src/resources/cities_full.csv");
        
        System.out.println("=============== Highest Population city by Country ====================");
        Map<String, Optional<City>> cityByCountry = main.getHighestPopulationByCountry(cities);
        cityByCountry.forEach((countryCode, city) ->
                System.out.println("Country Code : " + countryCode + "\n" + "Highest population city in this country : " + city.get()));
        System.out.println("=======================================================================");

        System.out.println("=============== Highest Population city by Capital ====================");
        Optional<City> cityByCapital = main.getHighestPopulationCapital(cities);
        if (cityByCapital.isPresent())
            System.out.println(cityByCapital);
        else
            System.out.println("Something went wrong!!");


        //Optional<City> cityCapital = main.getHighestPopulationByContinent(countries, cities);
        //System.out.println(cityCapital);
    }

    public Map<String, Optional<City>> getHighestPopulationByCountry(List<City> cities) {
        Map<String, Optional<City>> cityCountryPopulation = cities.stream()
                .collect(Collectors.groupingBy(City::getCountryCode, Collectors.maxBy(Comparator.comparingInt(City::getPopulation))));
        return cityCountryPopulation;
    }


    /*
    public Optional<City> getHighestPopulationByContinent(List<Country> allCountries, List<City> allCities) {
        Optional<City> highestCities = allCities.stream().filter(city -> allCountries.stream()
                                                                            .anyMatch(country -> country.getCode() == city.getCountryCode()))
                                                                            .max(Comparator.comparingInt(City::getPopulation));
        return highestCities;
    }*/

    public Optional<City> getHighestPopulationCapital(List<City> allcities) {
        Optional<City> cityCountryPopulation = allcities.stream()
                .filter(City::isCapital)
                .max(Comparator.comparingInt(City::getPopulation));
        return cityCountryPopulation;
    }
}
